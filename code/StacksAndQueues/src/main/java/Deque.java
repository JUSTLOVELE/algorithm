import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class DequeIterator<Item> implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {

            if(!hasNext()) {
                throw new NoSuchElementException();
            }

            Node<Item> node = current;
            current = node.nextNode;

            return node.item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node<Item> {
        //值
        private Item item;
        //下一个节点
        private Node<Item> nextNode;
        //上一个节点
        private Node<Item> preNode;
    }

    private int N = 0;

    private Node<Item> first;

    private Node<Item> lastNode;

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return N<=0;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // add the item to the front
    public void addFirst(Item item) {

        validateAddOperation(item);

        Node<Item> oldFisrtNode = first;
        first = new Node<Item>();
        first.item = item;

        if(oldFisrtNode == null) {
            //第一次初始化
            lastNode = first;
        }else{
            first.nextNode = oldFisrtNode;
            oldFisrtNode.preNode = first;
        }

        N++;
    }

    // add the item to the back
    public void addLast(Item item) {

        validateAddOperation(item);
        Node<Item> oldLastNode = lastNode;
        lastNode = new Node<Item>();
        lastNode.item = item;

        if(oldLastNode == null) {
            //第一次初始化
            first = lastNode;
        }else {
            lastNode.preNode = oldLastNode;
            oldLastNode.nextNode = lastNode;
        }

        N++;
    }

    private void validateAddOperation(Item item) {

        if(item == null) {
            throw new IllegalArgumentException();
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {

        validateRemoveOperation();
        Item item = first.item;
        Node<Item> n = first;
        first = first.nextNode;
        n = null;
        N--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {

        validateRemoveOperation();
        Item item = lastNode.item;
        Node<Item> n = lastNode;
        lastNode = lastNode.preNode;
        n = null;
        N--;
        return item;
    }

    public void validateRemoveOperation() {

        if(N <= 0) {
            throw new NoSuchElementException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        Deque<String> deque = new Deque<String>();
        deque.addFirst("aaaa");
        deque.addFirst("cccc");
        deque.addFirst("dddd");
        deque.addFirst("eeee");
        deque.addLast("fffff");
        deque.addLast("hhhhh");
        deque.addLast("mmmmm");

        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());

        //DequeIterator<String> iterator = deque.iterator();


    }
}
