package org.VersionedList;

import java.util.*;

public class VersionedList<T> implements Iterable<T> {
    private T[] data;

    private void ensureCapacityInternal(int minCapacity) {

        if (data == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        ensureExplicitCapacity(minCapacity);
    }


    public int size() {
        return data.length;
    }

    public boolean isEmpty() {
        return data.length == 0;
    }


    public boolean contains(Object o) {
        return Arrays.stream(data).findAny() != null;
    }

    @Override
    public Iterator<T> iterator() {
        return new VersionedIterator();
    }

    public boolean add(T t) {
        return false;
    }


    public boolean remove(Object o) {
        return false;
    }

    class VersionedIterator implements Iterator<T>{

        private int  index;
        private T[] array;

        VersionedIterator(){
            index = 0;

        }
        @Override
        public boolean hasNext() {
            return index < array.length;
        }

        @Override
        public T next() {
            return null;
        }

    }

}
