package com.qa.ims.persistence.domain;

public class Order {
    private Long orderId;
    private Long orderlineId;
    private Long customerId;
    private String datePlaced;
    private Double orderTotal;

    public Order(Long orderlineId, Long customerId, String datePlaced, Double orderTotal) {
        this.orderlineId = orderlineId;
        this.customerId = customerId;
        this.datePlaced = datePlaced;
        this.orderTotal = orderTotal;
    }

    public Order(Long orderId, Long orderlineId, Long customerId, String datePlaced, Double orderTotal) {
        this.orderId = orderId;
        this.orderlineId = orderlineId;
        this.customerId = customerId;
        this.datePlaced = datePlaced;
        this.orderTotal = orderTotal;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderlineId() { return orderlineId; }

    public void setOrderlineId(Long orderlineId) { this.orderlineId = orderlineId; }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    public String getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(String datePlaced) {
        this.datePlaced = datePlaced;
    }

    public Double getOrderTotal() { return orderTotal; }

    public void setOrderTotal(Double orderTotal) { this.orderTotal = orderTotal; }

    @Override
    public String toString() {
        return "order id: " + orderId + " orderline ID: " + orderlineId + " customer ID: " + customerId  + " date of order: " + datePlaced + " total of order:" + orderTotal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
        result = prime * result + ((orderlineId == null) ? 0 : orderlineId.hashCode());
        result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
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
        if (orderlineId == null) {
            if (other.orderlineId != null)
                return false;
        } else if (!orderlineId.equals(other.orderlineId))
            return false;
        if (orderId == null) {
            if (other.orderId != null)
                return false;
        } else if (!orderId.equals(other.orderId))
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