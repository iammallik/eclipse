
public class MbinaryTree{
	
	class TreeNode{
		
		public TreeNode(int x) { val = x; }
		
		int val;
		TreeNode left;
		TreeNode right;
	}
	
	public MbinaryTree(int x) { 
		root = new TreeNode(x);
	}
	
	TreeNode root;
	
	public void add(int x) {
		TreeNode temp = root;
		
		while(temp != null) {
			if(x <= temp.val) {
				if(temp.left == null) {
					TreeNode t = new TreeNode(x);
					temp.left = t;
					return;
				}
				temp = temp.left;
			}
			
			else{
				if(temp.right == null) {
					TreeNode t = new TreeNode(x);
					temp.right = t;
					return;
				}
				temp = temp.right;
			}
		}
		
	}
	
	public void p_inorder() {
		
	}
	
	void po(TreeNode t) {
		if(t == null) return;
		if(t.left != null) po(t.left);
		p(t.val+" ");
		if(t.right != null) po(t.right);	
	}
	
}