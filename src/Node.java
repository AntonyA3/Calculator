
public class Node {
	Token value;
	Node left;
	Node right;
	
	public Node(Token value) {
		this.value = value;
	}
	public Node getLeft() {
		return left;
	}
	
	public Node getRight() {
		return right;
	}
	
	public void setLeft(Node left) {
		this.left = left;
	}
	
	public Token getValue() {
		return value;
	}
	
	public void setValue(Token value) {
		this.value = value;
	}
	
	public void setRight(Node right) {
		this.right = right;
	}
	
	public boolean insert(Token obj) {
		System.out.println(obj);
		if (getLeft() == null) {
			setLeft(new Node(obj));
			return true;
		}
		
		if (getLeft().getValue().getType().equals(TokenType.Operator)) {
			if (getLeft().insert(obj)) {
			return true;
			}
		};
		
	
		
		
		if (getRight() == null) {
			setRight(new Node(obj));
			return true;
		}
		if (getRight().getValue().getType().equals(TokenType.Operator)) {
			if(getRight().insert(obj)) {
				return true;
			}
		}
			
		return false;
		
	}
	
	public int getSize() {
		
		int size = 1;
		System.out.println();
		System.out.println(getValue());
		System.out.println(getLeft());
		System.out.println(getRight());
		if (getLeft() != null) {
			size += getLeft().getSize();
			
		}
		
		if (getRight() != null) {
			size += getRight().getSize();
		}
		
		
		return size;
	}
	
	public double calculate() {
		if (value.getType().equals(TokenType.Operand)) {
			return Double.valueOf(value.getValue());
		}
		else {
			switch (value.getValue()) {
			case "*":
				System.out.println(getLeft());
				System.out.println(getRight());
				return getLeft().calculate() * getRight().calculate();
			case "-":
				return getLeft().calculate() - getRight().calculate();
			case "+":
				return getLeft().calculate() + getRight().calculate();
			case "/":
				return getLeft().calculate() / getRight().calculate();
			default:
		}
		return 0;
		
		}
	}
}
