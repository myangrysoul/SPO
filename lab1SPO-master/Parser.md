# lab1SPO
import java.util.ArrayList;

public class Parser {
	
	static private ArrayList<Token> tokens;
	static private Token currentToken;
	static int i = 0;
	public void match() {
		currentToken = tokens.get(i);
		i++;
	}
	
	public void parse(ArrayList<Token> tokens) {
		lang();
	}
	
	private void lang() {
		expr();
	}
	private void expr() {
		try {
			VAR();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			ASSIGN_OP();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			DIGIT();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void VAR() throws Exception {
		match();
		if(!currentToken.getType().equals("VAR")) {
			throw new Exception("VAR expected, but "+ currentToken.getType()+" found!!!");
		} else {
			System.out.println("vse OK!");
		}
	}
	
	private void ASSIGN_OP() throws Exception {
		match();
		if(!currentToken.getType().equals("ASSIGN_OP")) {
			throw new Exception("ASSIGN_OP expected, but "+ currentToken.getType()+" found!!!");
		}else {
			System.out.println("vse OK!");
		}
	}
	private void DIGIT() throws Exception {
		match();
		if(!currentToken.getType().equals("DIGIT")) {
			throw new Exception("DIGIT expected, but "+ currentToken.getType()+" found!!!");
		}else {
			System.out.println("vse OK!");
		}
	}
	public void main(String[] args) {

        Lexer lexer = new Lexer();

        String line = "super1=pop";
        
        Parser parser = new Parser();
        
        tokens = lexer.parse(line);
        for(Token token: tokens) {
        	System.out.println(token.getType()+" "+token.getValue());
        }
        
        parser.parse(tokens);

    }
	
}
