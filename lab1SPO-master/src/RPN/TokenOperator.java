package RPN;


import Lexer.Token;

public class TokenOperator extends Token {
    private int priority;

    public TokenOperator(int priority,String type, String value){
        super(type,value);
        this.priority = priority;
    }

    int getPriority() {
        return priority;
    }
}
