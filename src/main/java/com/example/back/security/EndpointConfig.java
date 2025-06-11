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
            new SecuredEndpoint("/api/v1/user/list",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/products",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/category/{id}",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/address",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/address",HttpMethod.POST),
            new SecuredEndpoint("/api/v1/address/{id}",HttpMethod.DELETE),
            new SecuredEndpoint("/api/v1/address",HttpMethod.PATCH),
            new SecuredEndpoint("/api/v1/bill/getAll",HttpMethod.GET),
            // admin
            new SecuredEndpoint("/api/v1/bill/stats", HttpMethod.GET),
            new SecuredEndpoint("/api/v1/bill/recent", HttpMethod.GET),
            new SecuredEndpoint("/api/v1/products/tops", HttpMethod.GET),
            new SecuredEndpoint("/api/v1/reviews/recent", HttpMethod.GET),
             new SecuredEndpoint("/api/v1/chart/revenue/daily", HttpMethod.GET),
            new SecuredEndpoint("/api/v1/chart/revenue/monthly", HttpMethod.GET),
            new SecuredEndpoint("/api/v1/chart/revenue/quarterly", HttpMethod.GET),
            new SecuredEndpoint("/api/v1/chart/catalog/revenue", HttpMethod.GET),
            new SecuredEndpoint("/api/v1/chart/catalog/products", HttpMethod.GET),
            new SecuredEndpoint("/api/v1/products/search",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/category/getAll",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/catalog/getAll",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/catalog/active",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/catalog/create",HttpMethod.POST),
            new SecuredEndpoint("/api/v1/catalog/update/{id}",HttpMethod.PUT),
            new SecuredEndpoint("/api/v1/catalog/delete/{id}",HttpMethod.DELETE),
            new SecuredEndpoint("/api/v1/category/detail",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/category/update/{id}",HttpMethod.PUT),
            new SecuredEndpoint("/api/v1/category/create",HttpMethod.POST),
            new SecuredEndpoint("/api/v1/category/delete/{id}",HttpMethod.DELETE),
            new SecuredEndpoint("/api/v1/sale/getAll",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/sale/update/{id}",HttpMethod.PUT),
            new SecuredEndpoint("/api/v1/sale/create",HttpMethod.POST),
            new SecuredEndpoint("/api/v1/sale/delete/{id}",HttpMethod.DELETE),
            // report
            new SecuredEndpoint("/api/v1/inventory",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/revenue",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/revenue/detail",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/inventory/export",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/revenue/export",HttpMethod.GET),
            new SecuredEndpoint("/api/v1/summary",HttpMethod.GET),
            // open ai
             new SecuredEndpoint("api/v1/chatbot/training/retrain",HttpMethod.POST),
            new SecuredEndpoint("api/v1/openai/ask",HttpMethod.POST)



    );

    public static final List<SecuredEndpoint> USER_ENDPOINTS = List.of(
            new SecuredEndpoint("/api/v1/cart", HttpMethod.GET),
            new SecuredEndpoint("/api/v1/cart", HttpMethod.POST),
            new SecuredEndpoint("/api/v1/cart/{productSizeId}",HttpMethod.DELETE),
            new SecuredEndpoint("/api/v1/cart/update",HttpMethod.PATCH)
    );

    public static final List<SecuredEndpoint> ADMIN_ENDPOINTS = List.of(
            new SecuredEndpoint("/api/v1/admin/demo", HttpMethod.GET)



    );
}
