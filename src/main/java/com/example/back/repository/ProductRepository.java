package com.example.back.repository;

import com.example.back.entity.Category;
import com.example.back.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    @EntityGraph(attributePaths = {"images", "productSizes","productSizes.size",  "sale"})
    @Query("SELECT distinct p FROM Product p ORDER BY p.createdDate DESC")
    List<Product> findProductNew(Pageable pageable);

    @EntityGraph(attributePaths = {"images", "productSizes","productSizes.size", "sale"})
    @Query("SELECT distinct p FROM Product p  JOIN p.sale s WHERE s.id=1")
    List<Product> findProductSale(Pageable pageable);

    Optional<Product> findByProductId(Integer id);

    @EntityGraph(attributePaths = {"images", "productSizes","productSizes.size",  "sale"})
    @Query("SELECT distinct p FROM Product p ORDER BY p.createdDate DESC")
    List<Product> findAllProduct(Pageable pageable);

    @EntityGraph(attributePaths = {"images", "productSizes","productSizes.size",  "sale"})
    @Query("SELECT distinct p FROM Product p WHERE p.category = :category ")
    List<Product> findByCategory(@Param("category") Category category, Pageable pageable);

    // admin
    @Query("""
        SELECT p FROM Product p
        JOIN p.reviews r
        GROUP BY p.id
        ORDER BY AVG(r.rating) DESC
    """)
    List<Product> findTopRatedProducts(Pageable pageable);
    // product
    @Query(value = """
    SELECT GROUP_CONCAT(DISTINCT img.image) AS images,
           p.name AS name,
           p.price AS price,
           p.quantity AS quantity,
           c.name AS category,
           p.sold_count AS soldCount,
           GROUP_CONCAT(DISTINCT s.size_name) AS sizes
    FROM product p
    JOIN category c ON p.category_id = c.category_id
    JOIN image img ON img.product_id = p.product_id
    JOIN product_size ps ON ps.product_id = p.product_id
    JOIN size s ON s.size_id = ps.size_id
    WHERE (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
      AND (:categoryId IS NULL OR p.category_id = :categoryId)
      AND (:minPrice IS NULL OR p.price >= :minPrice)
      AND (:maxPrice IS NULL OR p.price <= :maxPrice)
    GROUP BY p.product_id
    LIMIT :limit OFFSET :offset
""", nativeQuery = true)
    List<Object[]> searchProducts(
            @Param("name") String name,
            @Param("categoryId") Integer categoryId,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("limit") int limit,
            @Param("offset") int offset
    );

    @Query(value = """
    SELECT COUNT(DISTINCT p.product_id)
    FROM product p
    WHERE (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
      AND (:categoryId IS NULL OR p.category_id = :categoryId)
      AND (:minPrice IS NULL OR p.price >= :minPrice)
      AND (:maxPrice IS NULL OR p.price <= :maxPrice)
""", nativeQuery = true)
    int countFilteredProducts(
            @Param("name") String name,
            @Param("categoryId") Integer categoryId,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice
    );
    // giảm giá
    List<Product> findByProductIdInAndIsDeletedFalse(List<Integer> ids);
    List<Product> findByCategoryCategoryIdInAndIsDeletedFalse(List<Integer> categoryIds);
    List<Product> findBySaleIdAndIsDeletedFalse(Integer saleId);


}
