/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamvnt.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class CartObj implements Serializable{
    private Map<String,Integer> items; // <SKU, quantity>
    public  Map<String,Integer> getItems() { // ko dc set -> ko dc cho 1 lan nhieu hang
        return items;
    }
    // add one item to cart
    public void addBookToCart(String book) {
        //1. chekking existed (items) ngan de hang
        if (this.items==null) {
            this.items=new HashMap<>();
        }// items has exsited
        //2. check existed book
        int quantity =1;
        if (this.items.containsKey(book)) {
            quantity=this.items.get(book)+1;
        }
        //3. update items
        this.items.put(book, quantity);
    }
    // remove al quantity of item out of cart    
    public void removeBookFromCart(String book) {
        //1. check existed items
        if (this.items==null) {
            return;
        }
        //2. check existed items
        if (this.items.containsKey(book)) {
            this.items.remove(book); //bo het hang
            if (this.items.isEmpty()) {
                this.items=null;
            }
        }
    }
    
//    private Map<ProductDTO,Integer> items; // <productObj, quantity>
//    public  Map<ProductDTO,Integer> getItems() { // ko dc set -> ko dc cho 1 lan nhieu hang
//        return items;
//    }
//    // add one item to cart
//    public void addBookToCart(ProductDTO book) {
//        //1. chekking existed (items) ngan de hang
//        if (this.items==null) {
//            this.items=new HashMap<>();
//        }// items has exsited
//        //2. check existed book
//        int quantity =1;        
////        if (this.items.containsKey(book)) {
////            quantity=this.items.get(book)+1;
////        }
//        for (ProductDTO item : items.keySet()) {
//            if (item.equals(book)) {
//                quantity = this.items.get(item)+1;// update quantity
//                items.remove(item);// remove old (book,quantity)
//                System.out.println(book + " name: "+book.getName()+" = "+item+" name: "+item.getName());                
//            }
//        }
//        //3. update items
//        this.items.put(book, quantity);
//        System.out.println(book+","+quantity);
//    }
//    // remove al quantity of item out of cart    
//    public void removeBookFromCart(ProductDTO book) {
//        //1. check existed items
//        if (this.items==null) {
//            return;
//        }
//        //2. check existed items
//        if (this.items.containsKey(book)) {
//            this.items.remove(book); //bo het hang
//            if (this.items.isEmpty()) {
//                this.items=null;
//            }
//        }
//    }
//    // method: get 1 product(book) with bookName is given
//    public ProductDTO getBook(String bookName) {
//        for (ProductDTO book : items.keySet()) {
//            if (book.getName().equals(bookName)) {
//                return book;
//            }
//        }
//        return null;
//    }
}
