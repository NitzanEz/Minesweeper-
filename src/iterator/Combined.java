package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Combined<E> implements Iterable<E> {
	private Iterable<E> first;
	private Iterable<E> second;
	public Combined(Iterable<E> first, Iterable<E> second) {
		this.first=first;
		this.second=second;
	}
	 private class MyIterator implements Iterator<E>   {
		private Iterator<E> iterator1 = first.iterator(); 
		private Iterator<E> iterator2 = second.iterator();
		int flag=1;
			@Override
			public boolean hasNext(){
				return iterator1.hasNext() ||  iterator2.hasNext();
			}
			@Override
			public E next(){
				if (!(hasNext())) {
					throw new NoSuchElementException();
				}
				if (flag==1 && iterator1.hasNext()==false) {
					flag=2;
				}
				if (flag==2 &&  iterator2.hasNext()==false) {
					flag=1;
				}
				if (flag==1) {
					flag=2;
					return iterator1.next();
				}
				flag=1;
				return iterator2.next();
			
		 
	 }
	 }

	
	@Override
	public Iterator<E> iterator() {
		
		return new MyIterator();
	}
	}

