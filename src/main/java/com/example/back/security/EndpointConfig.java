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

            //
            new SecuredEndpoint("/api/v1/catalog", HttpMethod.GET),
            new SecuredEndpoint("/api/v1/catalog", HttpMethod.POST),
            new SecuredEndpoint("/api/v1/catalog/**", HttpMethod.PUT),
            new SecuredEndpoint("/api/v1/catalog/**", HttpMethod.DELETE),
            new SecuredEndpoint("/api/v1/category", HttpMethod.GET),
            new SecuredEndpoint("/api/v1/category", HttpMethod.POST),
            new SecuredEndpoint("/api/v1/category/**", HttpMethod.PUT),
            new SecuredEndpoint("/api/v1/category/**", HttpMethod.DELETE),
            new SecuredEndpoint("/api/v1/bills", HttpMethod.GET),
            new SecuredEndpoint("/api/v1/bills", HttpMethod.POST),
            new SecuredEndpoint("/api/v1/bills/**", HttpMethod.PUT),
            new SecuredEndpoint("/api/v1/reviews", HttpMethod.GET),
            new SecuredEndpoint("/api/v1/reviews", HttpMethod.POST),
            new SecuredEndpoint("/api/v1/users", HttpMethod.GET),
            new SecuredEndpoint("/api/v1/user/me", HttpMethod.GET),
            new SecuredEndpoint("/api/v1/users/**", HttpMethod.GET),
            new SecuredEndpoint("/api/v1/users/{id}", HttpMethod.PUT),
            new SecuredEndpoint("/api/v1/users/{id}/status", HttpMethod.PUT),
            new SecuredEndpoint("/api/v1/users/{id}/role", HttpMethod.PUT),
            new SecuredEndpoint("/api/v1/user/change-password", HttpMethod.POST),
            new SecuredEndpoint("/api/v1/user/profile", HttpMethod.PATCH),
            new SecuredEndpoint("/api/v1/reviews/{reviewId}/reply", HttpMethod.PUT)

    );

    public static final List<SecuredEndpoint> USER_ENDPOINTS = List.of(
            new SecuredEndpoint("/api/v1/cart", HttpMethod.GET),
            new SecuredEndpoint("/api/v1/cart", HttpMethod.POST),
            new SecuredEndpoint("/api/v1/user/**",HttpMethod.GET)
    );

    public static final List<SecuredEndpoint> ADMIN_ENDPOINTS = List.of(
            new SecuredEndpoint("/api/v1/admin/demo", HttpMethod.GET)
    );
}
