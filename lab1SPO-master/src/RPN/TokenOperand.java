package Handler;

import Lexer.Token;

public class TokenOperand extends Token implements TranslationElement {
    public void translate(Handler h){
        h.handle(this);
    }
    public TokenOperand(String type, String value){
        super(type,value);
    }
}
