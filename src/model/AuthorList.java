package model;

import java.util.ArrayList;
import java.util.Iterator;

public class AuthorList implements Iterable<Author> {

    private ArrayList<Author> author;

    public AuthorList() {
        author = new ArrayList<>();
    }

    public AuthorList(ArrayList<Author> author) {
        this(); //chama o construtor da propria classe
        addAuthor(author);
    }

    public void addAuthor(Author author) {
        this.author.add(author);
    }

    public void addAuthor(ArrayList<Author> author) {
        for (Author a : author) {
            addAuthor(a);
        }
    }

    public ArrayList<Author> getAuthor() {
        return this.author;
    }

    public int size() {
        return this.author.size();
    }

    public Author get(int index) {
        return this.author.get(index);
    }

    //pega o ultimo livro adicionado
    public Author getLast() {
        return this.author.get(size()-1);
    }

    //coloca o interador de author para usar no for
    @Override
    public Iterator<Author> iterator() {
        return author.iterator();
    }
}
