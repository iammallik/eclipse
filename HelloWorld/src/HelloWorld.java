import java.util.Scanner;


public class HelloWorld {
	
	static PrintHelper ph = new PrintHelper();

	public static void main(String[] args) {
		lru_ops();
	}
	
	
	static void readFromUser(PrintHelper ph, BTree bt) {
		ph.pl("Hello Mallik!, lets build BST, add nodes! Press `Y` for continue...\n");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while(scanner.nextLine() == "Y") {
			ph.pl("mallik <>");
			int x = Integer.parseInt(scanner.nextLine());
			bt.add(x);
			ph.pl("Press `Y` for continue...");
		}
	}
	
	static void bst_ops() {
		BTree bt = new BTree();
//		readFromUser(ph, bt);
		bt.populateNodes(10);
		ph.pl("bt count: "+bt.count());
		bt.print();
		bt.buildAdjList();
	}
	
	static void lru_ops() {
		LRU lru = new LRU(4);
		lru.refer(1);
		lru.refer(2);
		lru.refer(3);
		lru.print();
		lru.refer(2);
		lru.print();
		lru.refer(5);
		lru.refer(7);
		lru.print();
		
	}
}
