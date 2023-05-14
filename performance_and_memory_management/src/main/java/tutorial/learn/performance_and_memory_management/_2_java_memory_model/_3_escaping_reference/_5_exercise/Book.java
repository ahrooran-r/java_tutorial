package tutorial.learn.performance_and_memory_management._2_java_memory_model._3_escaping_reference._5_exercise;

import lombok.Getter;

public class Book {

    @Getter
    private int id;

    @Getter
    private String title;

    @Getter
    private String author;

    private Price price;

    public Book(int id, String title, String author, Double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = new Price(price);
    }

    public String toString() {
        return title + " by " + author;
    }

    public Price getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = new Price(price);
    }
}
