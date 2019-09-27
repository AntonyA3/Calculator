import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.InflaterInputStream;



public class LexicalAnalyser {
	
	private TokenType state;
	private String tokenBuffer;
	private LinkedList<Token> tokens;
	
	
	
	public LexicalAnalyser() {
		this.tokens = new LinkedList<Token>();
	}
	
	public String getDigit(int i, String input) {
		int j = i;
		
		for ( ; j < input.length();) {
			if(!Character.isDigit(input.charAt(j))) {
				return input.substring(i, j);
			}
			j++;
		}
		
		return input.substring(i, j);
		
		
	}
	
	public int getPrecidence(String operator) {
		int result = 0;
		switch(operator) {
		
		case "*":
		case "/":
			result = 1;
			break;
		case "+":
		case "-":
			result = 2;	
			break;
		}
		
		return result;
		
		
	}

	public void toAbstractSyntaxTree(LinkedList<Token> input){
		System.out.println(input);
		Tree output = new Tree();
		for (int i = input.size() - 1; i >= 0 ; i--) {
			output.insert(input.get(i));
		}
		System.out.println(output.calculate());
		
		
		
	}
	
	public LinkedList<Token> infixToPostfix(LinkedList<Token> tokens) {
		LinkedList<Token> output = new LinkedList<>();
		LinkedList<Token> opStack = new LinkedList<>();
		for (int i = 0; i < tokens.size(); i++) {
			Token t = tokens.get(i);
			if (t.getType() == TokenType.Operand) {
				output.add(tokens.get(i));
			}
			
			else if (t.getType() == TokenType.Operator) {
				if (t.getValue().equals("(")){
					opStack.push(t);
				
				}
				else if(t.getValue().equals(")")) {
					
					while (!opStack.peek().getValue().equals("(")) {
						output.add(opStack.pop());
					}
					opStack.pop();
						
						
				}
				else if (opStack.isEmpty()) {
					opStack.push(t);
				}
				
				else if(t.getValue().matches("[[+][-][*][/]]")) {	
					while(getPrecidence(opStack.peek().getValue()) >= getPrecidence(t.getValue())) {
						output.add(opStack.pop());
					}
					opStack.push(t);
				}				
			}

		}
		//take all from stack
		while (!opStack.isEmpty()) {
			output.add(opStack.pop());
		}
		return output;
		
	}

	public void evaluate(String input) {
		for (int i = 0; i < input.length();) {
			switch(input.charAt(i)) {
				case '+':
				case '-':
				case '*':
				case '/':
				case '(':
				case ')':
					tokens.add(new Token(TokenType.Operator, String.valueOf(input.charAt(i))));
					i++;
					break;
				default:
					if(Character.isDigit(input.charAt(i))) {
						String digit = getDigit(i, input);
						tokens.add(new Token(TokenType.Operand, digit));
						i += digit.length();
					}
					
					
					
			}
		}
		LinkedList<Token> postfixResult = this.infixToPostfix(tokens);
		toAbstractSyntaxTree(postfixResult);
		

	}

	


	
	
	
	
}
