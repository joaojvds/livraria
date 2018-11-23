package model;

import java.util.ArrayList;
import java.util.Iterator;

public class BookList implements Iterable<Books>{

    private ArrayList<Books> books;

    public BookList() {
        books = new ArrayList<>();
    }

    public BookList(ArrayList<Books> books) {
        this(); //chama o construtor da propria classe
        addBook(books);
    }

    public void addBook(Books book) {
        books.add(book);
    }

    public void addBook(ArrayList<Books> books) {
        for (Books book : books) {
            addBook(book);
        }
    }

    public ArrayList<Books> getBooks() {
        return this.books;
    }

    public int size() {
        return this.books.size();
    }

    public Books get(int index) {
        return this.books.get(index);
    }

    //pega o ultimo livro adicionado
    public Books getLast() {
        return this.books.get(size()-1);
    }

    //coloca o interador de books para usar no for
    @Override
    public Iterator<Books> iterator() {
        return books.iterator();
    }
}
