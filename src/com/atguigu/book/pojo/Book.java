package com.atguigu.book.pojo;

import java.math.BigDecimal;

/**
 * @author Tom 2022/2/7 16:34
 */
public class Book {

    private int id;
    private String name;
    private BigDecimal price;
    private String author;
    private int sales;
    private int stock;
    private String photo = "static/img/default.jpg";

    public Book() {
    }

    public Book(int id, String name, BigDecimal price, String author, int sales, int stock, String photo) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
        this.sales = sales;
        this.stock = stock;
        if(photo != null && !"".equals(photo)) {
            this.photo = photo;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        if(photo != null && !"".equals(photo)) {
            this.photo = photo;
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", sales=" + sales +
                ", stock=" + stock +
                ", photo='" + photo + '\'' +
                '}';
    }
}
