/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamvnt.product;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class ProductDTO implements Serializable, Comparable<ProductDTO>{
    private String SKU;
    private String name;
    private String description;
    private double price;
    public ProductDTO() {
    }
    public ProductDTO(String SKU, String name, String description, double price) {
        this.SKU = SKU;
        this.name = name;
        this.description = description;
        this.price = price;
    }
    
    public ProductDTO(String SKU, String name, double price) {
        this.SKU = SKU;
        this.name = name;
        this.price = price;
    }
    /**
     * @return the SKU
     */
    public String getSKU() {
        return SKU;
    }

    /**
     * @param SKU the SKU to set
     */
    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(ProductDTO t) {
        return this.SKU.compareTo(t.getSKU());
    }

    @Override
    public boolean equals(Object obj) {
        return this.compareTo((ProductDTO)obj)==0;
    }
    
          
}
