package com.arka.arkajuanprenss.infrastructure.adapter.in.web.dto;


import java.util.List;

public class CartDTO {
    private Long id;
    private Long customerId;
    private List<Long> productIds;

    // ===== Constructores =====
    public CartDTO() {}

    public CartDTO(Long id, Long customerId, List<Long> productIds) {
        this.id = id;
        this.customerId = customerId;
        this.productIds = productIds;
    }

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public List<Long> getProductIds() { return productIds; }
    public void setProductIds(List<Long> productIds) { this.productIds = productIds; }
}
