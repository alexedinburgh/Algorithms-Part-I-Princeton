package dierzhou;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>{
    private List<Item> list;
    
    public RandomizedQueue(){
        list = new ArrayList<Item>();
    }
    
    public boolean isEmpty(){
        return list.isEmpty();
    }
    
    public int size(){
        return list.size();
    }
    
    public void enqueue(Item item){
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        list.add(item);
    }
    
    public Item dequeue(){
        return (Item) list.remove(randomIndex());
    }
    
    public int randomIndex() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return StdRandom.uniform(size());        
    }
    
    public Item sample(){
        return (Item) list.get(randomIndex());
    }
    
    private class RandomizedQueueIterator<E> implements Iterator<E> {
        public boolean hasNext() {
            return size() > 0;
        }
        public E next() {
            if (isEmpty()) {
                throw new java.util.NoSuchElementException();
            }
            return (E) dequeue();
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
    public Iterator<Item> iterator(){
        return new RandomizedQueueIterator<Item>();
    }
}
        