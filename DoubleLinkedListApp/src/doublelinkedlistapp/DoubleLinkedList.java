package doublelinkedlistapp;

import java.util.ArrayList;
import java.lang.reflect.Array;
import java.util.Iterator;

/**
 *
 * @author Emre
 */
public class DoubleLinkedList<E> {

    private Node first, last;
    private int size = 0;

    public DoubleLinkedList() {
        first = last = null;

    }

    public DoubleLinkedList(E data) {
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

        Node traveller = first;
        for (int i = 0; i < position; i++) {
            traveller = traveller.next;
        }
        Node node = new Node(data);
        if (first != traveller) {
            if (traveller != null) { // araya ekleme
                node.prev = traveller.prev;
                traveller.prev.next = node;
                node.next = traveller;
                traveller.prev = node;
            } else { // sona ekleme
                last.next = node;
                node.prev = last;
                last = node;
            }
        } else {
            node.next = first;
            if (first != null) {
                first.prev = node;
            } else {
                last = node;
            }
            first = node;
        }
        ++size;
    }

    public E remove(int position) throws IndexOutOfBoundsException {
        if (size <= position || position < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node traveller = first;
        for (int i = 0; i < position; i++) {
            traveller = traveller.next;
        }
        if (first != traveller) { // aradaki veya sondaki düğümü bağlı listeden çıkar
            if (traveller.next != null) {
                traveller.prev.next = traveller.next;
                traveller.next.prev = traveller.prev;
            } else {
                last = traveller.next;
                last.next = null;
            }
        } else {
            // ilk düğümü bağlı listeden çıkar
            first = traveller.next;
            first.prev = null;
        }
        traveller.prev = null;
        traveller.prev = null;
        --size;

        return traveller.data;
    }

    public E update(int position, E newValue) {
        if (size < position || position < 0) {
            throw new IndexOutOfBoundsException();
        }
        int i = 0;
        E temp = null;
        for (Node traveller = first; traveller != null; traveller = traveller.next) {
            if (position == i++) {
                temp = traveller.data;
                traveller.data = newValue;
            }
        }
        return temp;
    }

    public void update(E oldValue, E newValue) {

        int i = 0;
        for (Node traveller = first; traveller != null; traveller = traveller.next) {
            if (oldValue == traveller.data) {
                traveller.data = newValue;
            }
        }
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
        first = last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int search(E element) {
        int position = 0;

        for (Node traveller = first; traveller != null; traveller = traveller.next) {
            ++position;
            if (traveller.data.equals(element)) {
                return position;
            }
        }
        return -1;
    }

    public int count() {
        return size;
    }

    public Iterator getEnumator() {
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
        public Node next, prev;

        Node(E data) {
            next = prev = null;
            this.data = data;
        }
    }
}
