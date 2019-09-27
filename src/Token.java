

public class Token {
	private TokenType type;
	private String value;
	public Token(TokenType currentState, String value ) {
		this.type = currentState;
		this.value = value;
		
	}
	
	public TokenType getType() {
		return type;
	}
	
	public String getValue() {
		return value;
	}
	@Override
	public String toString() {
		return "(" + type.toString() + ", " + value  + ")";
	}
}
