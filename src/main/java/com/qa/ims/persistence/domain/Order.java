package com.qa.ims.persistence.domain;

public class Order {
    private Long orderId;
    private Long customerId;
    private Long productId;
    private String datePlaced;
    private Long orderTotal;

    public Order(Long customerId, Long productId, String datePlaced, Long orderTotal) {
        this.customerId = customerId;
        this.productId = productId;
        this.datePlaced = datePlaced;
        this.orderTotal = orderTotal;
    }

    public Order(Long orderId, Long customerId, Long productId, String datePlaced, Long orderTotal) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.datePlaced = datePlaced;
        this.orderTotal = orderTotal;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(String datePlaced) {
        this.datePlaced = datePlaced;
    }

    public Long getOrderTotal() { return orderTotal; }

    public void setOrderTotal(Long orderTotal) { this.orderTotal = orderTotal; }

    @Override
    public String toString() {
        return "order id: " + orderId + " customer ID: " + customerId + " product ID: " + productId + " date of order: " + datePlaced + " total of order:" + orderTotal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
        result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        result = prime * result + ((datePlaced == null) ? 0 : datePlaced.hashCode());
        result = prime * result + ((orderTotal == null) ? 0 : orderTotal.hashCode());

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
        Order other = (Order) obj;
        if (getCustomerId() == null) {
            if (other.getCustomerId() != null)
                return false;
        } else if (!getCustomerId().equals(other.getCustomerId()))
            return false;
        if (orderId == null) {
            if (other.orderId != null)
                return false;
        } else if (!orderId.equals(other.orderId))
            return false;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
            return false;
        if (datePlaced == null) {
            if(other.datePlaced != null)
                return false;
        } else if (!datePlaced.equals(other.datePlaced))
            return false;
        if (orderTotal == null) {
            if(other.orderTotal != null)
                return false;
        } else if (!orderTotal.equals(other.orderTotal))
            return false;
        return true;
    }
}