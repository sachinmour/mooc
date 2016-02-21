import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    public Deque() {
        first = last = null;
        N = 0;
    }                       // construct an empty deque
    public boolean isEmpty() {
        return N == 0;
    }                      // is the deque empty?
    public int size() {
        return N;
    }                     // return the number of items on the deque
    public void addFirst(Item item) {
        if(item == null) throw new NullPointerException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.previous = null;
        if(isEmpty()){
            last = first;
            first.next = null;
        }
        else{
            first.next = oldfirst;
            oldfirst.previous = first;
        }
        N++;
    }         // add the item to the front
    public void addLast(Item item) {
        if(item == null) throw new NullPointerException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()){
            first = last;
            last.previous = null;
        }
        else{
            last.previous = oldlast;
            oldlast.next = last;
        }
        N++;
    }           // add the item to the end
    public Item removeFirst() {
        if(isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        N--;
        if(isEmpty()){
            first = last = null;
        }
        else{
            first.previous = null;
        }
        return item;
    }               // remove and return the item from the front
    public Item removeLast() {
        if(isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.previous;
        N--;
        if(isEmpty()){
            first = last = null;
        }
        else{
            last.next = null;
        }
        return item;
    }                // remove and return the item from the end
    public Iterator<Item> iterator() {
        return new ListIterator();
    }        // return an iterator over items in order from front to end

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

//    public static void main(String[] args)   // unit testing
}
