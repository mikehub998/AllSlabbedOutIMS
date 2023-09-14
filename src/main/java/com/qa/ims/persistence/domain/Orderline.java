package com.qa.ims.persistence.domain;

public class OrderProduct {
    private Long orderProductId;
    private Long productId;
    private float amount;
    private double orderProductTotal;

    public OrderProduct(Long productId, float amount, double orderProductTotal) {
        this.productId = productId;
        this.amount = amount;
        this.orderProductTotal = orderProductTotal;
    }

    public OrderProduct(Long orderProductId, Long productId, float amount, double orderProductTotal) {
        this.orderProductId = orderProductId;
        this.productId = productId;
        this.amount = amount;
        this.orderProductTotal = orderProductTotal;
    }

    public Long getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(Long orderProductId) {
        this.orderProductId = orderProductId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public double getOrderProductTotal() { return orderProductTotal; }

    public void setOrderProductTotal(double orderProductTotal) { this.orderProductTotal = orderProductTotal; }

    @Override
    public String toString() {
        return "order product id: " + orderProductId + " product ID: " + productId + " amount of order: " + amount + " total of order and product:" + orderProductTotal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orderProductId == null) ? 0 : orderProductId.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderProduct other = (OrderProduct) obj;
                if (orderProductId == null) {
            if (other.orderProductId != null)
                return false;
        } else if (!orderProductId.equals(other.orderProductId))
            return false;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
            return false;
        return true;
    }
}