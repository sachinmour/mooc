import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] randomQueue;
    private int N = 0;

    public RandomizedQueue() {
        randomQueue = (Item[]) new Object[1];
    }
    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for(int i = 0;i < N;i++){
            temp[i] = randomQueue[i];
        }
        randomQueue = temp;
    }

    public void enqueue(Item item) {
        if(item == null) throw new NullPointerException();
        if(N == randomQueue.length) resize(2*randomQueue.length);
        randomQueue[N++] = item;
    }                               // add the item

    public Item dequeue(){
        if(isEmpty()) throw new NoSuchElementException();
        int offset = StdRandom.uniform(N);
        Item item = randomQueue[offset];
        if(offset != N-1) randomQueue[offset] = randomQueue[N-1];
        randomQueue[N-1] = null;
        N--;
        if(N > 0 && N == randomQueue.length/4) resize(randomQueue.length/2);
        return item;
    }
    public Item sample(){
        if(isEmpty()) throw new NoSuchElementException();
        int offset = StdRandom.uniform(N);
        return randomQueue[offset];
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private Item[] copyArray = (Item[]) new Object[randomQueue.length];
        private int i = N;

        public ArrayIterator(){
            for(int i = 0;i < randomQueue.length;i++){
                copyArray[i] = randomQueue[i];
            }
        }

        public boolean hasNext(){
            return i > 0;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

        public Item next(){
            if (!hasNext()) throw new NoSuchElementException();
            int offset = StdRandom.uniform(i);
            Item item = copyArray[offset];
            if(offset != i-1){
                copyArray[offset] = copyArray[i-1];
            }
            copyArray[i-1] = null;
            i--;
            return item;
        }
    }
}