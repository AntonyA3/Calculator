
public class Tree  {
	
	private Node root;
	
	public Tree() {
		this.root = null;
	}
	public void insert(Token obj) {
		if (root == null) {
			this.root = new Node(obj);
		}else {
			root.insert(obj);
		}
		
		
	}
	

	
	public double calculate() {
		return root.calculate();
	}
	
	public int size() {
		return root.getSize();
	}
	
	
	
}
