package com.example.hackupc;

import java.util.UUID;

public class Producto {
    private String id;
    private String name;
    private String price;
    private String url;
    private String type;

    public Producto(String name, String price, String type, String url) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.url = url;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
