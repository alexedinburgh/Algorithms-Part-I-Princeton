

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Deque<Item> implements Iterable<Item>{
    private List<Item> list;
    
    public Deque(){
        list = new LinkedList<Item>();
        
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public int size(){
        return list.size();
    }
    public void addFirst(Item item){
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        list.add(0, item);
    }
    public void addLast(Item item){
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        list.add(item);
    }
    public Item removeFirst(){
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return (Item) list.remove(0);
    }
    public Item removeLast(){
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return (Item) list.remove(list.size() - 1);
    }
    private class DequeIterator<E> implements Iterator<E> {
        public boolean hasNext() {
            return size() > 0;
        }
        public E next() {
            if (isEmpty()) {
                throw new java.util.NoSuchElementException();
            }
            return (E) removeFirst();
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
    public Iterator<Item> iterator(){
        return new DequeIterator<Item>();
    }
}
    