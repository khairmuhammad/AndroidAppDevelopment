package com.example.android.custom_listview_adapter;

public class Book {

    private int imgID;
    private String book_name;
    private String author;
    private String year;

    public Book(int imgID, String book_name, String author, String year) {
        this.imgID = imgID;
        this.book_name = book_name;
        this.author = author;
        this.year = year;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
