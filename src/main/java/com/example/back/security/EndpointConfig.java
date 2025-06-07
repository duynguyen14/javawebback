package com.example.back.security;

import org.springframework.http.HttpMethod;

import java.util.List;

public class EndpointConfig {
    public static final List<SecuredEndpoint> PUBLIC_ENDPOINTS = List.of(
            new SecuredEndpoint("/api/v1/user/register", HttpMethod.POST),
            new SecuredEndpoint("/api/v1/user/login", HttpMethod.POST),
            new SecuredEndpoint("/api/v1/user/demo", HttpMethod.GET),
            new SecuredEndpoint("/api/v1/products/home",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/product/{id}",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/product/{id}/related",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/user/list",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/products",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/category/{id}",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/address",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/address",HttpMethod.POST),
            new SecuredEndpoint("/api/v1/address/{id}",HttpMethod.DELETE),
            new SecuredEndpoint("/api/v1/address",HttpMethod.PATCH)


    );

    public static final List<SecuredEndpoint> USER_ENDPOINTS = List.of(
            new SecuredEndpoint("/api/v1/cart", HttpMethod.GET),
            new SecuredEndpoint("/api/v1/cart", HttpMethod.POST),
            new SecuredEndpoint("/api/v1/cart/{productSizeId}",HttpMethod.DELETE),
            new SecuredEndpoint("/api/v1/cart/update",HttpMethod.PATCH),
            new SecuredEndpoint("/api/v1/user/profile",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/user/profile",HttpMethod.PATCH),
            new SecuredEndpoint("/api/v1/user/change-password",HttpMethod.POST),
            new SecuredEndpoint("/api/v1/bill/getAll",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/bill/create",HttpMethod.POST),
            new SecuredEndpoint("/api/v1/bill/detail/{id}",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/bill/detail/{id}",HttpMethod.PATCH),
            new SecuredEndpoint("/api/v1/favorite/getAll",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/favorite/{id}",HttpMethod.POST),
            new SecuredEndpoint("/api/v1/favorite/{id}",HttpMethod.DELETE),
            new SecuredEndpoint("/api/v1/reviews/check/{id}",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/reviews/create/{id}",HttpMethod.POST)








            );

    public static final List<SecuredEndpoint> ADMIN_ENDPOINTS = List.of(
            new SecuredEndpoint("/api/v1/admin/demo", HttpMethod.GET)

    );
}
