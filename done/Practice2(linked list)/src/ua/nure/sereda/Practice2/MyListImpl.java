package ua.nure.sereda.Practice2;
import java.util.Iterator;

public class MyListImpl implements MyList, ListIterable{

    private class Node{
        private Object obj;
        private Node Next;
        private Node Prev;
    }

    private Node First = null;
    private Node Last = null;
    private int size = 0;

    @Override
    public void add(Object e) {
        Node newNode = new Node();
        newNode.obj = e;
        newNode.Next = null;
        if(First != null){
            newNode.Prev = Last;
            Last.Next = newNode;
            Last = newNode;
        } else {
            newNode.Prev = null;
            First = Last = newNode;
        }
        size++;
    }

    @Override
    public void clear() {
        First = Last = null;
        size = 0;
    }

    @Override
    public boolean remove(Object o) {
       Node curr = First;
        if(curr.obj.equals(o)){
            First = First.Next;
            First.Prev = null;
            size--;
            return true;
        } else {
            while (curr != null) {
                if(curr != Last && curr.obj.equals(o)) {
                    curr.Prev.Next = curr.Next;
                    curr.Next.Prev = curr.Prev;
                    size--;
                    return true;
                } else if(curr == Last && Last.obj.equals(o)) {
                    Last = Last.Prev ;
                    Last.Next = null;
                    size--;
                    return true;
                }
                curr = curr.Next;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        Node curr = First;
        for(int i = 0; i < size; i++, curr = curr.Next){
            arr[i] = curr.obj;
        }
        return arr;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean contains(Object o) {
        Node curr = First;
        while (curr != null) {
            if(curr.obj.equals(o)){
                return true;
            }
            curr = curr.Next;
        }
        return false;
    }

    @Override
    public boolean containsAll(MyList c) {
        Object[] arr = c.toArray();
        for (Object o: arr) {
            if(!contains(o)){
                return false;
            }
        }
        return true;
    }

    public String toString(){
        Node curr = First;
        StringBuilder res = new StringBuilder();
        res.append("{");
        while (curr != null){
            res.append("[");
            res.append(curr.obj);
            res.append("]");
            if(curr.Next != null){
                res.append(", ");
            }
            curr = curr.Next;
        }
        res.append("}");
        return res.toString();
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        Node curr = null;
        boolean triggerNext = false;
        boolean triggerPrev = false;
        boolean endList = false;

        public boolean hasNext() { // returns true if the iteration has more elements
            return curr != Last;
        }

        public Object next() { // returns the next element in the iteration
            triggerNext = true;
            if(!hasNext()) {
                throw new IndexOutOfBoundsException("End of list");
            } else if (curr == null) {
                curr = First;
                return curr.obj;
            } else {
                curr = curr.Next;
                if(curr == Last) {
                    endList = true;
                }
                return curr.obj;
            }
        }

        public void remove() { // removes from the underlying collection the last element returned by this iterator
            if(!triggerNext){
                throw new IllegalStateException();
            }
            else if(curr == First){
                First = First.Next;
                First.Prev = null;
                curr = First;
            } else if(curr == Last){
                Last = Last.Prev;
                Last.Next = null;
                curr = Last;
            } else {
                curr.Prev.Next = curr.Next;
                curr.Next.Prev = curr.Prev;
            }
            size--;
            triggerNext = false;
        }
    }

    public ListIterator listIterator() {
        return new ListIteratorImpl();
    }

    private class ListIteratorImpl extends IteratorImpl implements ListIterator {

        @Override
        public boolean hasPrevious() {
            return curr != First;
        }

        @Override
        public Object previous() {
            triggerPrev = true;
            if(!hasPrevious()) {
                throw new IndexOutOfBoundsException("List not started");
            } else if(curr == Last && endList){
                endList = false;
                return curr.obj;
            } else {
                curr = curr.Prev;
                return curr.obj;
            }
        }

        @Override
        public void set(Object e) {
            if(!triggerNext && !triggerPrev){
                throw new IllegalStateException();
            } else {
                curr.obj = e;
            }
            triggerNext = triggerPrev = false;
        }

        @Override
        public void remove(){
            if(!triggerNext && !triggerPrev){
                throw new IllegalStateException();
            } else if(curr == First){
                First = First.Next;
                First.Prev = null;
                curr = First;
            } else if(curr == Last){
                Last = Last.Prev ;
                Last.Next = null;
                curr = Last;
            } else {
                curr.Prev.Next = curr.Next;
                curr.Next.Prev = curr.Prev;
            }
            size--;
            triggerNext = triggerPrev = false;
        }
    }
}
