package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoArrays implements Iterable <Integer>{
	private int [] a1;
	private int [] a2;
 
 public TwoArrays(int[] a1, int[] a2) {
	 this.a1=a1;
	 this.a2=a2;
 }
 private class MyIterator implements Iterator<Integer>   {
		private int i1=0;
		private int i2=0;
		private int flag=1;
		
		@Override
		public boolean hasNext(){
			return i1+i2<a1.length+ a2.length;
		}

		@Override
		public Integer next(){
			if (!(hasNext())) {
				throw new NoSuchElementException();
			}
			if (flag==1 && i1==a1.length) {
				flag=2;
			}
			if (flag==2 && i2==a2.length) {
				flag=1;
			}
			if (flag==1) {
				flag=2;
				return a1[i1++];
			}
			flag=1;
			return a2[i2++];
		
			}
		
	
			
		


}
@Override
public Iterator<Integer> iterator() {
	// TODO Auto-generated method stub
	return new MyIterator();
}

}



