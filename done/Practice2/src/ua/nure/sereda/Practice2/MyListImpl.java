package ua.nure.sereda.Practice2;

import java.util.Iterator;

public class MyListImpl implements MyList, ListIterable {

	private int size;
	private Object[] list;
	
	MyListImpl(){
		size = 0;
		list = new Object[5];
	}
	
	@Override
	public void add(Object e) {
		if(size == list.length){
			int newSize = size + 5;
			Object[] newList = new Object[newSize];
			System.arraycopy(list, 0, newList, 0, size);
			list = newList;
		}
		list[size++] = e;
	}

	@Override
	public void clear() {
		list = new Object[5];
		size = 0;
	}

	@Override
	public boolean remove(Object o) {
		for(int i = 0; i < size; i++){
			if(o.equals(list[i])){
                System.arraycopy(list, i + 1, list, i, size - i);
			    --size;
			    return true;
			}
		}
		return false;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		System.arraycopy(list, 0, array, 0, size);
		return array;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(Object o) {
		for(Object obj: list){
			if(o.equals(obj)){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(MyList c) {
		Object [] inter = c.toArray();
		for (Object anInter : inter) {
			if (!contains(anInter)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("[");
		for(int i = 0; i < size; i++){
			res.append(list[i]);
			if(i < size-1){
				res.append(", ");
			}
		}
		res.append("]");
		return res.toString();
	}

	@Override
	public Iterator<Object> iterator() {
		return new IteratorImpl();
	}
	
	private class IteratorImpl implements Iterator<Object> {
		int index = 0;
		int lastElemReturned = -1;
		
		public boolean hasNext() { // returns true if the iteration has more elements
			return index < size;
		}
		
		
		public Object next() { // returns the next element in the iteration
			if(!hasNext()) {
				throw new IndexOutOfBoundsException("End of list");} 
				lastElemReturned = index++;
				return list[lastElemReturned];
		}
		
		
		public void remove() { // removes from the underlying collection the last element returned by this iterator
			if(lastElemReturned < 0) {
				throw new IllegalStateException();
			}
            System.arraycopy(list, lastElemReturned + 1, list, lastElemReturned, size - lastElemReturned);
			--size;
			--index;
			lastElemReturned = -1;
		}
	}

	@Override
	public ListIterator listIterator() {
		return new ListIteratorImpl();
	}

	private class ListIteratorImpl extends IteratorImpl implements ListIterator {
		private boolean triggerPrev = false;
		
		@Override
		public boolean hasPrevious() {
			return index > 0;
		}

		@Override
		public Object previous() {
			triggerPrev = true;
			if(!hasPrevious()) {
				throw new IndexOutOfBoundsException("List not started");
			} 
			lastElemReturned = --index;
			return list[lastElemReturned];
		}

		@Override
		public void set(Object e) {
			if(lastElemReturned < 0) {
				throw new IllegalStateException();
			}
			list[lastElemReturned] = e;	
			lastElemReturned = -1;
		}
		
		public void remove(){
			if(lastElemReturned < 0) {
				throw new IllegalStateException();
			}
            System.arraycopy(list, lastElemReturned + 1, list, lastElemReturned, size - lastElemReturned);
			--size;
			if(!triggerPrev){
			--index;
			}
			triggerPrev = false;
			lastElemReturned = -1;
		}
	}
}
