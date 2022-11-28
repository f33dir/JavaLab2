package org.VersionedList;

import java.util.*;
import java.util.function.Consumer;

public class VersionedList<T> implements Iterable<T> {

    private ArrayList<Cell<T>> data = new ArrayList<>();
    public void add(T input){
        data.add(new Cell(input));
    }
    public T get(int index){
        return data.get(index).Get();
    }

    public void remove(int index){
        data.remove(index);
    }
    @Override
    public Iterator<T> iterator(){
        return new VersionedIterator();
    }
    class VersionedIterator implements Iterator<T>
    {
        private int index = 0;
        private Cell<T>[] savedArr;

        VersionedIterator(){
            savedArr = new Cell[data.size()];
            savedArr = data.toArray(savedArr);
        }
        @Override
        public boolean hasNext() {
            return index < savedArr.length;
        }

        @Override
        public T next() {
            T output = savedArr[index].Get();
            index++;
            return output;
        }

    }
    class Cell<T>{
        private T content;
        public T Get(){
            return content;
        };
        public boolean Set(T input){
            content = input;
            return true;
        }
        public Cell(T input){
            content = input;
        }
    }
}
