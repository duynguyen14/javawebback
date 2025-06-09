package com.example.back.repository;

import com.example.back.entity.ProductSize;
import com.example.back.entity.ShoppingCart;
import com.example.back.entity.ShoppingCartDetail;
import com.example.back.entity.ShoppingCartDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartDetailRepository extends JpaRepository<ShoppingCartDetail, ShoppingCartDetailId> {

    Optional<ShoppingCartDetail> findByProductSizeAndShoppingCart(ProductSize productSize, ShoppingCart shoppingCart);

    List<ShoppingCartDetail> findByShoppingCart(ShoppingCart shoppingCart);
}
