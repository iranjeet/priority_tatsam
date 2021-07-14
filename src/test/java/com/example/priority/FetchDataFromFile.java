package com.example.priority;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class FetchDataFromFile {
    public static String getTotalSales(List<Product> products) {
        return String.valueOf(products.stream().mapToDouble(Product::getTotalPrice).sum());
    }

    public static Map<String, String> monthWiseSale(List<Product> products) {
        Map<String, String> res = new HashMap<>();
        Map<String, List<Product>> monthWiseDetails = products.stream().collect(Collectors.groupingBy(Product::getDate));
        for (Map.Entry<String, List<Product>> entry : monthWiseDetails.entrySet()) {
            res.put(
                    entry.getKey(),
                    String.valueOf(entry.getValue().stream().mapToDouble(Product::getTotalPrice).sum())
            );
        }
        return res;
    }
    private static Map<String ,String> mostPopularItem(List<Product> products) {
        Map<String, String> res=new HashMap<>();
        Map<String, List<Product>> monthWiseDetails = products.stream().collect(Collectors.groupingBy(Product::getDate));
        for (Map.Entry<String, List<Product>> entry : monthWiseDetails.entrySet()) {
            res.put(
                    entry.getKey(),
                    String.valueOf(entry.getValue().stream().mapToDouble(Product::getTotalPrice).sum())
            );
        }
        return res;
    }

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ranje\\Downloads\\newTest\\src\\test\\java\\com\\example\\priority\\mobix.txt"));
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                Product product = new Product();
                String tmp[] = line.split(",");
                products.add(new Product(tmp[0], tmp[1], Double.valueOf(tmp[2]), Long.valueOf(tmp[3]), Double.valueOf(tmp[4])));
            }
            Object obj = products.stream().collect(Collectors.groupingBy(Product::getSku));
            Object t = products.stream().collect(Collectors.groupingBy(Product::getDate, Collectors.groupingBy(Product::getSku)));
            System.out.println("Total Sale of Store : " + getTotalSales(products));
            System.out.println("Month wise sales : " + monthWiseSale(products));
            System.out.println("Most Popular Item (most quantity sold ) : "+ mostPopularItem(products));
            System.out.println("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

class Product {
    private String date;
    private String sku;
    private Double price;
    private Long quantity;
    private Double totalPrice;

    public Product() {
    }

    public Product(String date, String sku, Double price, Long quantity, Double totalPrice) {
        this.date = date;
        this.sku = sku;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

