/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whejp.reto5.repositories;

import com.whejp.reto5.models.Product;
import java.util.List;

/**
 *
 * @author juanrueda
 */
public interface CustomRepository {
    
    void addProduct(Product p);
    void deleteProduct(Product p);
    Product getProductById(int id);
    List<Product> listAll();
    
}
