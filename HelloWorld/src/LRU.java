import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class LRU{
	int s; //size
	Deque<Integer> q = new LinkedList<Integer>();
	HashSet<Integer> set = new HashSet<Integer>();
	PrintHelper ph;
	public LRU(int x) {
		s = x; 
		ph = new PrintHelper();
	}
	
	public void refer(int x) {
		
		if(!set.contains(x)) {
			if(set.size() == s) {
				q.removeLast();
			}
		}
		else q.remove(x);
		q.addFirst(x);
		set.add(x);
	}
	
	public void print() {
		
		Iterator<Integer> i = q.iterator();
		
		while(i.hasNext()) {
			ph.p((int)i.next());
		}
		ph.p_break();
	}
}