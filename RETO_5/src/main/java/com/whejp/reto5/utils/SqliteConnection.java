/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whejp.reto5.utils;

import com.whejp.reto5.models.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author juanrueda
 */
public final class SqliteConnection {

    // Configuracion de la conexion a la base de datos private String url = "";
    public Connection con;
    private static SqliteConnection instance;

    //Constructor sin parmetros
    private SqliteConnection() {
        var url = "jdbc:sqlite:/Users/juanrueda/Downloads/carlostruji85_reto5db.db";
        try {
            // Realizar la conexion
            con = DriverManager.getConnection(url);
            if (con != null) {
                DatabaseMetaData meta = con.getMetaData();
                System.out.println("Base de datos conectada " + meta.getDriverName());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static SqliteConnection getInstance() {
        if (instance == null) {
            instance = new SqliteConnection();
        }
        return instance;
    }

    public boolean Insert(Product p) {
        final String sql = "INSERT INTO productos(nombre,cantidad,categoria,precio)"
                + " VALUES(?,?,?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, p.getName());
            pstmt.setInt(2, p.getQuantity());
            pstmt.setString(3, p.getCategory());
            pstmt.setDouble(4, p.getPrice());
            return pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean Remove(int id) {
        final String sql = "DELETE FROM productos WHERE id = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            return pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean Update(Product p) {
        final String sql = "UPDATE productos"
                + " SET nombre = ?, cantidad = ?,categoria = ?,precio= ?"
                + " WHERE id = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(5, p.getId());
            pstmt.setString(1, p.getName());
            pstmt.setInt(2, p.getQuantity());
            pstmt.setString(3, p.getCategory());
            pstmt.setDouble(4, p.getPrice());
            return pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Optional<Product> getItemById(int id) {
        final String sql = "SELECT * FROM productos where id = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            return Optional.of(new Product(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getInt(3), rs.getDouble(5)));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Optional.ofNullable(null);
        }
    }

    public List<Product> listAllProducts() {
        String sql = "SELECT * FROM productos";
        List<Product> products = new ArrayList<>();
        ResultSet rs;
        try {
            rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                products.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getInt(3), rs.getDouble(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

}
