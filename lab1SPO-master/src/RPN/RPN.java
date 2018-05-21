package RPN;

import Handler.TokenOperand;
import Handler.TokenOperator;
import Lexer.LexemType;
import Lexer.Token;

import java.util.*;

public class RPN {
    private static Stack<TokenOperator> stack = new Stack<>();
    private int i;
    private ArrayList<Token> output = new ArrayList<>();
    private ArrayList<Token> input;


   public RPN(ArrayList<Token> tokens){
        this.input=tokens;

    }

    public void exprHandle(){


            if(input.get(i) instanceof TokenOperand){
                output.add(input.get(i));
            }
            else if(input.get(i) instanceof TokenOperator){
                OperatorHandle();
            }
            else if(input.get(i).getType().equals("END")){
                while(!stack.empty()){
                    output.add(stack.pop());
                }
            }
        }

    public void OperatorHandle() {

        TokenOperator tokenOperator = (TokenOperator) input.get(i);
        if (tokenOperator.getType().equals("L_B")) {
            stack.push(tokenOperator);
        } else if (tokenOperator.getType().equals("R_B")) {
            while (!stack.peek().getType().equals("L_B")) {
                output.add(stack.pop());
            }
            stack.pop();
        } else if (!stack.empty() && tokenOperator.getPriority() <= stack.peek().getPriority()) {
            while (!stack.empty()&&tokenOperator.getPriority() <= stack.peek().getPriority()) {
                output.add(stack.pop());
            }
            stack.push(tokenOperator);
        } else {
            stack.push(tokenOperator);
        }

    }
    public void cycleRPN(){
        i++;
        int JmpToStart=output.size();
        int buff;
        while(!input.get(i).getType().equals("L_F_B")){
            exprHandle();
            i++;
        }
        buff=output.size();
        output.add(new TokenOperand("VAR"," "));
        output.add(new TokenOperator(0,LexemType.J_C.getType(),"!F"));
        while(!input.get(i+1).getType().equals("R_F_B")){
            if(input.get(i+1).getType().equals("CYCLE")){
                i++;
                cycleRPN();
            }
            i++;
            exprHandle();
        }
        output.add(new TokenOperand("DIGIT","P"+Integer.toString(JmpToStart)));
        output.add(new TokenOperator(0,LexemType.JUMP.getType(),"!"));
        output.set(buff,new TokenOperand("DIGIT","P"+Integer.toString(output.size())));





    }
    public ArrayList<Token> toRPN() {
       for(i = 0;i<input.size(); i++){
           if(input.get(i).getType().equals("CYCLE")){
               cycleRPN();
           }
           else {
               exprHandle();
           }

       }
        output.add(new TokenOperand("VAR","end point"));
       return output;
    }

}
    //a b c d * 1 2 + e - / + asd - as as 78 / - 97 - 23 * 234 / + :=


