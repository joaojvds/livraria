package model;

import java.util.ArrayList;

public class Books {

    //variaveis globais
    private String isbn;
    private String title;
    private double price;
    private Publisher publisher;
    private ArrayList<Author> author;

    public Books (String isbn, String title, double price) {
        setIsbn(isbn);
        setTitle(title);
        setPrice(price);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public ArrayList<Author> getAuthor() {
        return author;
    }

    public void addAuthor(Author author) {
        if (this.author == null) {
            this.author = new ArrayList<>(); // adiciona uma posição no vetor caso não tenha nenhum
        }
        this.author.add(author); // adiciona o objeto ao vetor
    }

    public void addAuthor(ArrayList author) {
        this.author = author; // adiciona o objeto ao vetor
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
