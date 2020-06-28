package com;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack<Item> implements Iterable<Item> {

    private class ReverseArrayIterator implements Iterator<Item> {

        private  int i;

        public ReverseArrayIterator() {
             i = n-1;
        }

        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public Item next() {

            if(!hasNext()) throw new NoSuchElementException();
            return a[i--];
        }
    }

    private Item[] a;

    private int n;

    public ResizingArrayStack() {
        a = (Item[]) new Object[2];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int capactiy) {
        assert capactiy >= n;

        Item[] copy = (Item[]) new Object[capactiy];

        for(int i=0; i<n; i++) {
            copy[i] = a[i];
        }

        a = copy;
    }

    public void push(Item item) {

        if (n == a.length) resize(2*a.length);
        a[n++] = item;
    }

    /**
     * Returns (but does not remove) the item most recently added to this stack.
     * @return
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return a[n-1];
    }

    /**
     * Removes and returns the item most recently added to this stack.
     * @return
     */
    public Item pop() {

        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = a[n-1];
        a[n-1] = null;
        n--;

        if(n > 0 && n == a.length/4) resize(a.length/2);
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }
}
