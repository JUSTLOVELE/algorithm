import edu.princeton.cs.algs4.StdRandom;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 *
 * https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php
 * 迭代器。每个迭代器必须以均匀的随机顺序返回项目。同一个随机队列的两个或多个迭代器的顺序必须相互独立；
 * 每个迭代器必须保持自己的随机顺序
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int N = 0;//标记数组的长度

    private Item[] items;

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // construct an empty randomized queue
    public RandomizedQueue() {

        items = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return N <= 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item){

        if(item == null) {
            throw new IllegalArgumentException();
        }

        if(N == items.length)
            resize(items.length * 2);

        items[N] = item;
        N++;

    }

    // remove and return a random item
    public Item dequeue(){

        if(isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(N);
        Item it = items[index];

        if(index != N-1) {
            items[index] = items[N-1];
        }

        items[N-1] = null;
        N--;

        if(N > 0 && N == items.length/4) {
            resize(items.length/2);
        }

        return it;
    }

    private void resize(int capacity) {

        assert capacity >= N;
        Item[] oldItems = items;
        items = (Item[]) new Object[capacity];

        for(int i=0; i<N; i++) {
            items[i] = oldItems[i];
        }

        oldItems = null;

    }

    // return a random item (but do not remove it)
    public Item sample(){

        if(isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(N);
        return items[index];
    }

    public static void main(String[] args) {

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        randomizedQueue.enqueue("aaa");
        randomizedQueue.enqueue("bbb");
        randomizedQueue.enqueue("ccc");
        randomizedQueue.enqueue("ddd");
        randomizedQueue.enqueue("eee");
        randomizedQueue.enqueue("fff");
        System.out.println(randomizedQueue.dequeue());
        System.out.println("========================");

        Iterator<String> iterator = randomizedQueue.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private class RandomizedQueueIterator implements  Iterator<Item> {

        private Item[] iteratorItems;

        private int index = 0;

        public RandomizedQueueIterator() {

            iteratorItems = (Item[]) new Object[N];

            for(int i=0; i<N; i++) {

                iteratorItems[i] = (Item) items[i];
            }

            StdRandom.shuffle(iteratorItems);
        }

        @Override
        public boolean hasNext() {

            return index<iteratorItems.length;
        }

        @Override
        public Item next() {

            if(!hasNext())
                throw new NoSuchElementException();

            Item item = iteratorItems[index];
            index++;

            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
