package com.example.back.service;

import com.example.back.dto.response.Product.HomeResponseDTO;
import com.example.back.dto.response.Product.ProductDetail;
import com.example.back.dto.response.Product.ProductHome;
import com.example.back.entity.Category;
import com.example.back.entity.Product;
import com.example.back.enums.ErrorCodes;
import com.example.back.exception.AppException;
import com.example.back.mapper.ProductMapper;
import com.example.back.repository.CategoryRepository;
import com.example.back.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProductService {


    ProductRepository productRepository;
    ProductMapper productMapper;
    CategoryRepository categoryRepository;
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
//    public List<ProductHome> searchProduct(String name){
//
//    }
}
