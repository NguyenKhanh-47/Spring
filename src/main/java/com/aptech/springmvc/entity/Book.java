package com.aptech.springmvc.entity;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.*;

@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="price")
    private Float price;

    @Column(name="author")
    private String author;

    @Column(name="releaseDate")
    private String releaseDate;

    @Column(name="image")
    private String image;

    @Column(name="category")
    private String category;

    @Transient
    public CommonsMultipartFile fileData;

    public Book() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public CommonsMultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
