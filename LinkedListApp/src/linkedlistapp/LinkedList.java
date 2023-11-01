package linkedlistapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Emre
 * @param <E>
 */
public class LinkedList<E> {

    private Node first;
    private int size = 0;

    public LinkedList() {
        first = null;
    }

    public LinkedList(E data) {
        this();
        add(data);
    }

    public void add(E data) {
        insert(data, size);
    }

    public void insert(E data, int position) throws IndexOutOfBoundsException {
        if (size < position || position < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node node = new Node(data);
        Node traveller = first;
        Node previousNode = null;
        for (int i = 0; i < position; i++, traveller = traveller.next) {
            previousNode = traveller;
        }
        if (first != traveller) {
            if (traveller != null && previousNode != null) {
                previousNode.next = node;
                node.next = traveller;
            } else if (previousNode != null) {
                previousNode.next = node;
            } else {
                node.next = first;
                first = node;
            }

            ++size;
        }
    }

    public E remove(int position) throws IndexOutOfBoundsException {
        if (size < position || position < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node traveller = first;
        Node previousNode = null;
        for (int i = 0; i < position; ++i, traveller = traveller.next) {
            previousNode = traveller;
        }
        if (first != traveller) {
            if (previousNode != null) {
                previousNode.next = traveller.next;
            } else {
                first = first.next;
            }
            traveller.next = null;
            --size;

        }
        return traveller.data;
    }

    public E get(int position) throws IndexOutOfBoundsException {
        if (size < position || position < 0) {
            throw new IndexOutOfBoundsException();
        }
        int i = 0;
        for (Node traveller = first; traveller != null; traveller = traveller.next) {
            if (position == i++) {
                return traveller.data;
            }
        }
        return null;
    }

    public void clear() {
        first = null;
        size = 0;
    }

    public int count() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int search(E element) {
        int position = -1;
        boolean isOk = false;
        for (Node traveller = first; traveller != null; traveller = traveller.next) {
            ++position;
            if (traveller.data.equals(element)) {
                isOk = true;
                break;
            }
        }
        return (isOk) ? position : -1;
    }

    public Iterator<E> getEnumator() {
        ArrayList arr = new ArrayList();

        for (Node traveller = first; traveller != null; traveller = traveller.next) {
            arr.add(traveller.data);

        }
        return arr.iterator();
    }

    public E[] toArray(Class<E> type) {
        int i = 0;
        E[] arr = (E[]) Array.newInstance(type, size);
        for (Node traveller = first; traveller != null; traveller = traveller.next) {
            arr[i++] = traveller.data;
        }
        return arr;
    }

    private class Node {

        public E data;
        public Node next;

        public Node(E data) {
            next = null;
            this.data = data;
        }
    }

}
