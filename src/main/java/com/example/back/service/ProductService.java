package com.example.back.service;

import com.example.back.dto.request.Products.ProductRequestDTO;
import com.example.back.dto.response.Product.*;
import com.example.back.entity.*;
import com.example.back.enums.ErrorCodes;
import com.example.back.exception.AppException;
import com.example.back.exception.ResourceNotFoundException;
import com.example.back.mapper.ProductMapper;
import com.example.back.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProductService {


    ProductRepository productRepository;
    ProductMapper productMapper;
    CategoryRepository categoryRepository;
    SizeRepository sizeRepository;
    ImageRepository imageRepository;
    ProductSizeRepository productSizeRepository;
    public HomeResponseDTO getProductHome(){

        List<Product> productsNew= productRepository.findProductNew(PageRequest.of(0,10));
        List<Product> productsSale =productRepository.findProductSale(PageRequest.of(0,10));

        return HomeResponseDTO.builder()
                .productsSale(productsSale.stream().map(productMapper::toProductHomeDTO).toList())
                .productsNew(productsNew.stream().map(productMapper::toProductHomeDTO).toList())
                .build();

    }

    public ProductDetail getProductDetail(Integer id){
        Product product =productRepository.findProductWithDetail(id).orElseThrow(()->new AppException(ErrorCodes.PRODUCT_NOT_FOUND));
        return productMapper.toProductDetail(product);
    }
    public List<ProductHome> getRelatedProduct(Integer id){
        Product product =productRepository.findByProductId(id).orElseThrow(()-> new AppException(ErrorCodes.PRODUCT_NOT_FOUND));
        Category category = product.getCategory();
        List<Product> products =productRepository.findByCategory(category, PageRequest.of(0,10));
        return products.stream().map(productMapper::toProductHomeDTO).toList();

    }

    public List<ProductHome> getAllProduct(int page){
        List<Product> products =productRepository.findAllProduct(PageRequest.of(page,12));

        return products.stream().map(productMapper::toProductHomeDTO).toList();
    }
    public List<ProductHome> getByCategoryId(Integer CategoryId, int page,String sort){
        Category category= categoryRepository.findByCategoryId(CategoryId).orElseThrow(()-> new AppException(ErrorCodes.CATEGORY_NOT_FOUND));
        Sort sortOrder;
        switch (sort){
            case "bestSold":
                sortOrder=Sort.by(Sort.Direction.DESC, "soldCount");
                break;
            case "price_desc":
                sortOrder =Sort.by(Sort.Direction.DESC,"price");
                break;
            case "price_asc":
                sortOrder= Sort.by(Sort.Direction.ASC,"price");
                break;
            default:
                sortOrder = Sort.by(Sort.Direction.DESC, "createdDate");
                break;
        }
        List<Product> products =productRepository.findByCategory(category,PageRequest.of(page,12,sortOrder));
        return products.stream().map(productMapper::toProductHomeDTO).toList();
    }
    //admin
    public List<TopProductDTO> getTopRatedProducts() {
        List<Product> topProducts = productRepository.findTopRatedProducts(PageRequest.of(0, 5));

        return topProducts.stream().map(product -> {
            double avgRating = product.getReviews().stream()
                    .mapToInt(Review::getRating)
                    .average()
                    .orElse(0.0);

            String imageUrl = product.getImages().stream()
                    .findFirst()
                    .map(Image::getImage)
                    .orElse(null);

            return TopProductDTO.builder()
                    .id(product.getProductId().longValue())
                    .name(product.getName())
                    .description(product.getDescription())
                    .image(imageUrl)
                    .price(product.getPrice())
                    .soldCount(product.getSoldCount())
                    .createdAt(product.getCreatedDate())
                    .category(product.getCategory() != null ? product.getCategory().getName() : null)
                    .averageRating(avgRating)
                    .build();
        }).collect(Collectors.toList());
    }
    // qlsp
    public Page<ProductInfoDTO> searchProducts(String name, Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        int limit = pageable.getPageSize();
        int offset = (int) pageable.getOffset();

        List<Object[]> results = productRepository.searchProducts(name, categoryId, minPrice, maxPrice, limit, offset);
        int total = productRepository.countFilteredProducts(name, categoryId, minPrice, maxPrice);

        List<ProductInfoDTO> dtoList = results.stream().map(row -> new ProductInfoDTO(
                Arrays.asList(((String) row[0]).split(",")),
                (String) row[1],
                (BigDecimal) row[2],
                ((Number) row[3]).intValue(),
                (String) row[4],
                ((Number) row[5]).intValue(),
                Arrays.asList(((String) row[6]).split(","))
        )).toList();

        return new PageImpl<>(dtoList, pageable, total);
    }


//    public List<ProductHome> searchProduct(String name){
//
//    }
}
