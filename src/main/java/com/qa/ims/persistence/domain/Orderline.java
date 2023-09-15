package com.qa.ims.persistence.domain;

public class Orderline {
    private Long orderlineId;
    private Long productId;
    private float amount;
    private double orderlineTotal;

    public Orderline(Long productId, float amount, double orderProductTotal) {
        this.productId = productId;
        this.amount = amount;
        this.orderlineTotal = orderlineTotal;
    }

    public Orderline(Long orderlineId, Long productId, float amount, double orderlineTotal) {
        this.orderlineId = orderlineId;
        this.productId = productId;
        this.amount = amount;
        this.orderlineTotal = orderlineTotal;
    }

    public Long getOrderlineId() {
        return orderlineId;
    }

    public void setOrderlineId(Long orderlineId) {
        this.orderlineId = orderlineId;
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

    public double getOrderlineTotal() { return orderlineTotal; }

    public void setOrderlineTotal(double orderlineTotal) { this.orderlineTotal = orderlineTotal; }

    @Override
    public String toString() {
        return "orderline id: " + orderlineId + " product ID: " + productId + " amount of order: " + amount + " total of orderline:" + orderlineTotal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orderlineId == null) ? 0 : orderlineId.hashCode());
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
        Orderline other = (Orderline) obj;
                if (orderlineId == null) {
            if (other.orderlineId != null)
                return false;
        } else if (!orderlineId.equals(other.orderlineId))
            return false;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
            return false;
        return true;
    }
}