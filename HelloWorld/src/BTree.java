import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BTree {
	
	TreeNode root;
	int count;
	PrintHelper ph;
	
	public BTree() {
		ph = new PrintHelper();
		count = 0;
	}
	
	public void add(int x) {
		if(root == null) {
			root = new TreeNode(x);
			count += 1;
			return;
		}
		padd(root, x);
		count = count+1;
	}
	
	public void populateNodes(int n) {
		if(n <= 0) return;
		Random rand = new Random();
		final int limit = n*20;
		
		for(int i=0; i<n; i++) {
			int node_val = rand.nextInt(limit);
			ph.p("poulating "+node_val+", ");
			add(node_val);
		}
		ph.pl(n+" nodes are populated.");
	}
	
	public void print() {
		ph.pl("-------< Binary Tree nodes in InOrder >---------");
		pprint(root);
		ph.pl("  **   ]");
	}
	
	public int count() {
		return this.count;
	}
	
	public void buildAdjList() {
		if(root == null ) {
			ph.pl("BST is empty, cannot build Adjacency list for it!");
			return;
		}
		
		Map<TreeNode, List<TreeNode>> map = new HashMap();
		
		pbuildAdjList(root, map, null );
		
		for(TreeNode key: map.keySet()) {
			List<TreeNode> l  = map.get(key);
			ph.p("key is:"+key.val+" -");
			for(TreeNode n: l) {
				ph.p(n.val);
			}
			ph.pl(" **** ");
		}
	}
	
	
	///////////////////////< Private Methods>////////////////////////////////
	
	private void padd(TreeNode node, int x) {
		if(node == null) return;
		if(node.val <= x) {
			if(node.right == null) {
				node.right = new TreeNode(x);
				return;
			}
			else padd(node.right, x);
		}
		else{
			if(node.left == null) {
				node.left = new TreeNode(x);
				return;
			}
			padd(node.left, x);
		}
	}
	
	private void pprint(TreeNode root) {
		if(root == null ) return;
		pprint(root.left);
		ph.p(root.val);
		pprint(root.right);
	}
	
	private void pbuildAdjList(TreeNode node, Map<TreeNode, List<TreeNode>> map, TreeNode parent) {
		
		if(!map.containsKey(node)) map.put(node, new ArrayList<>());
		
		if(parent != null) {
			if(!map.containsKey(parent)) map.put(parent, new ArrayList<>());
			map.get(node).add(parent);
			map.get(parent).add(node);
		} 
		if(node.left != null) pbuildAdjList(node.left, map, node);
		if(node.right != null) pbuildAdjList(node.right, map, node);	
	}
}
