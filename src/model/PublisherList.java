package model;

import java.util.ArrayList;
import java.util.Iterator;

public class PublisherList implements Iterable<Publisher> {

    private ArrayList<Publisher> publisher;

    public PublisherList() {
        publisher = new ArrayList<>();
    }

    public PublisherList(ArrayList<Publisher> publisher) {
        this(); //chama o construtor da propria classe
        addPublisher(publisher);
    }

    public void addPublisher(Publisher publisher) {
        this.publisher.add(publisher);
    }

    public void addPublisher(ArrayList<Publisher> publisher) {
        for (Publisher a : publisher) {
            addPublisher(a);
        }
    }

    public ArrayList<Publisher> getPublisher() {
        return this.publisher;
    }

    public int size() {
        return this.publisher.size();
    }

    public Publisher get(int index) {
        return this.publisher.get(index);
    }

    //pega o ultimo livro adicionado
    public Publisher getLast() {
        return this.publisher.get(size()-1);
    }

    //coloca o interador de author para usar no for
    @Override
    public Iterator<Publisher> iterator() {
        return publisher.iterator();
    }
}
