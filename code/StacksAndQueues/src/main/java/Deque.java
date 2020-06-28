import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        //值
        private Item item;
        //下一个节点
        private Node nextNode;
        //上一个节点
        private Node preNode;
    }

    private int N = 0;

    private Node first;

    private Node lastNode;

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

        Node oldFisrtNode = first;
        first = new Node();
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
        Node oldLastNode = lastNode;
        lastNode = new Node();
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
        Node n = first;
        first = first.nextNode;
        n = null;
        N--;

        if(N == 0) {
            lastNode = null;
        }else{
            first.preNode = null;
        }

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {

        validateRemoveOperation();
        Item item = lastNode.item;
        Node n = lastNode;
        lastNode = lastNode.preNode;
        n = null;
        N--;

        if(N == 0) {
            first = null;
        }else {
            lastNode.nextNode = null;
        }

        return item;
    }

    private void validateRemoveOperation() {

        if(N <= 0) {
            throw new NoSuchElementException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        Deque<String> deque = new Deque<String>();
        deque.addFirst("aaaa");
        System.out.println(deque.removeFirst());
        System.out.println(deque.isEmpty());



//        deque.addFirst("cccc");
//        deque.addFirst("dddd");
//        deque.addFirst("eeee");
//        deque.addLast("fffff");
//        deque.addLast("hhhhh");
//        deque.addLast("mmmmm");
//
//        Iterator<String> iterator = deque.iterator();
//
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }



//        System.out.println(deque.removeFirst());
//        System.out.println(deque.removeFirst());
//        System.out.println(deque.removeFirst());
//        System.out.println(deque.removeFirst());
//        System.out.println(deque.removeFirst());
//        System.out.println(deque.removeFirst());
//        System.out.println(deque.removeFirst());
//        System.out.println(deque.removeFirst());

        //


    }

    private class DequeIterator implements Iterator<Item> {

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

            Node node = current;
            current = node.nextNode;

            return node.item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
