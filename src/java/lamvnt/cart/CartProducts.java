/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamvnt.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lamvnt.product.ProductDTO;

/**
 *
 * @author DELL
 */
public class CartProducts implements Serializable{
    private Map<String,ProductDTO> products; // <SKU,DTO>
    public Map<String,ProductDTO> getProducts() {
        return this.products;
    }
    public void addProductToList(ProductDTO dto) {
        if (this.products==null) {
            this.products = new HashMap<>();
        }
        this.products.put(dto.getSKU(), dto);
    }
}
