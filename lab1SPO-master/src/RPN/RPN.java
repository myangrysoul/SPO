package RPN;

import Handler.TokenOperand;
import Handler.TokenOperator;
import Lexer.Token;

import java.util.*;

public class RPN {
    private static Stack<TokenOperator> stack = new Stack<>();

    public ArrayList<Token> toRPN(ArrayList<Token> tokens1){
        ArrayList<Token> output = new ArrayList<>();
        for(Token token: tokens1){

            if(token instanceof TokenOperand){
                output.add(token);
            } else if(token instanceof TokenOperator){
                TokenOperator tokenOperator = (TokenOperator) token;
                if(tokenOperator.getType().equals("L_B")){
                    stack.push(tokenOperator);
                }
                else if(tokenOperator.getType().equals("R_B")){
                    while (!stack.peek().getType().equals("L_B")){
                        output.add(stack.pop());
                    }
                    stack.pop();
                }
                else if(!stack.empty() && tokenOperator.getPriority() <= stack.peek().getPriority()){
                    while (tokenOperator.getPriority() <= stack.peek().getPriority()) {
                        output.add(stack.pop());
                    }
                    stack.push(tokenOperator);
                } else {
                    stack.push(tokenOperator);
                }
            } else if(tokens1.size()-1==tokens1.indexOf(token)){
                while (!stack.empty()){
                    output.add(stack.pop());
                }
            }
        }
        return output;
    }
    //a b c d * 1 2 + e - / + asd - as as 78 / - 97 - 23 * 234 / + :=
}
