package com.qa.ims.persistence.domain;

public class Product {
    private Long productId;
    private String productName;
    private Long stockQuantity;
    private double price;

    public Product(String productName, Long stockQuantity, double price) {
        this.productName = productName;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }

    public Product(Long productId, String productName, Long stockQuantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Long stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "product id:" + productId + " product name" + productName + " stock quantity" + stockQuantity + " price" + price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((productName == null) ? 0 : productName.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        result = prime * result + ((stockQuantity == null) ? 0 : stockQuantity.hashCode());
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
        Product other = (Product) obj;
        if (getProductName() == null) {
            if (other.getProductName() != null)
                return false;
        } else if (!getProductName().equals(other.getProductName()))
            return false;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
            return false;
        if (stockQuantity == null) {
            if (other.stockQuantity != null)
                return false;
        } else if (!stockQuantity.equals(other.stockQuantity))
            return false;
        return true;
    }
}