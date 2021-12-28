package model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Book {

    private int id;
    private String title;
    private String description;
    private int pageCount;
    private String excerpt;
    private String publishDate;

    public Book(int id, String title, String description, int pageCount, String excerpt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pageCount = pageCount;
        this.excerpt = excerpt;
        this.publishDate = LocalDate.now().toString();
    }

}
