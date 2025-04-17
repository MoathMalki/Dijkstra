package application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class PriorityQueue1<T> {
    private ArrayList<T> heap;
    private Comparator<T> comparator;
    private HashMap<T, Integer> positionMap;

    public PriorityQueue1(Comparator<T> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<>();
        this.positionMap = new HashMap<>();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void add(T item) {
        heap.add(item);
        int index = heap.size() - 1;
        positionMap.put(item, index);
        bubbleUp(index);
    }

    public T poll() {
        if (heap.isEmpty()) {
            return null;
        }
        T minItem = heap.get(0);
        T lastItem = heap.remove(heap.size() - 1);
        positionMap.remove(minItem);
        if (!heap.isEmpty()) {
            heap.set(0, lastItem);
            positionMap.put(lastItem, 0);
            bubbleDown(0);
        }
        return minItem;
    }

    public void remove(T item) {
        Integer index = positionMap.get(item);
        if (index == null) {
            return;
        }
        int lastIndex = heap.size() - 1;
        T lastItem = heap.remove(lastIndex);
        positionMap.remove(item);
        if (index < lastIndex) {
            heap.set(index, lastItem);
            positionMap.put(lastItem, index);
            bubbleUp(index);
            bubbleDown(index);
        }
    }

    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (comparator.compare(heap.get(index), heap.get(parentIndex)) >= 0) {
               break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }
    private void bubbleDown(int index) {
        int lastIndex = heap.size() - 1;
        while (index < lastIndex) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            if (leftChildIndex > lastIndex) {
                break;
            }
            int smallerChildIndex = leftChildIndex;
            if (rightChildIndex <= lastIndex && comparator.compare(heap.get(rightChildIndex), heap.get(leftChildIndex)) < 0) {
                smallerChildIndex = rightChildIndex;
           }
            if (comparator.compare(heap.get(index), heap.get(smallerChildIndex)) <= 0) {
                break;
            }
           swap(index, smallerChildIndex);
            index = smallerChildIndex;
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
        positionMap.put(heap.get(i), i);
        positionMap.put(heap.get(j), j);
    }
}
