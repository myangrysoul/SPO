package Handler;

import Lexer.Token;

public class TokenOperator extends Token implements TranslationElement {
    private int priority;

    public void translate(Handler h){
        h.handle(this);
    }

    public TokenOperator(int priority,String type, String value){
        super(type,value);
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
