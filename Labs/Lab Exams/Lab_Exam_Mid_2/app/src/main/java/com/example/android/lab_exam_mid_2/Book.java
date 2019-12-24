package com.example.android.lab_exam_mid_2;

import java.io.Serializable;

public class Book implements Serializable {

    public String book_name;
    public String author;
    public String year;

    public Book(String book_name, String author, String year) {
        this.book_name = book_name;
        this.author = author;
        this.year = year;
    }


    public String getBook_name() {
        return book_name;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }
}
