/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whejp.reto5.repositories;

import com.whejp.reto5.models.Product;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author juanrueda
 */
public class ProductRepository implements CustomRepository{
    List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
    }
    
    @Override
    public void addProduct(Product p) {
        products.add(p);
    }

    @Override
    public void deleteProduct(Product p) {
      products.remove(p);
    }

    @Override
    public Product getProductById(int id) {
       return products.stream().filter(e -> e.getId() == id).findFirst().get();
    }

    @Override
    public List<Product> listAll() {
        return products;
    }
    
    public int getSequence (){
        System.out.println(products.size());
        return products.isEmpty() ? 1 : products.stream().max(Comparator.comparingInt(Product::getId)).get().getId()+1;
    }
    
}
