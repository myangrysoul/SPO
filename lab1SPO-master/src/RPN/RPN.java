package RPN;


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

    private void exprHandle(){


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

    private void OperatorHandle() {

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
   private void cycleRPN(){
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
            if (input.get(i+1).getType().equals("PRINT")){
                i++;
                printHandle();
            }
            i++;
            exprHandle();
        }
        output.add(new TokenOperand("DIGIT",Integer.toString(JmpToStart)));
        output.add(new TokenOperator(0,LexemType.JUMP.getType(),"!"));
        output.set(buff,new TokenOperand("DIGIT",Integer.toString(output.size())));





    }
    private void printHandle(){
    i++;
    while(!input.get(i).getType().equals("END")){
        if(input.get(i) instanceof TokenOperand){
            output.add(input.get(i));
            output.add(new TokenOperator(0,"PRINT","print"));
        }
        i++;

    }
    i++;




    }


    public ArrayList<Token> toRPN() {
       for(i = 0;i<input.size(); i++){
           switch (input.get(i).getType()) {
               case "CYCLE":
                   cycleRPN();
                   break;
               case "PRINT":
                   printHandle();
                   break;
               default:
                   exprHandle();
                   break;
           }

       }
        output.add(new TokenOperand("VAR","end point"));
       return output;
    }

}
    //a b c d * 1 2 + e - / + asd - as as 78 / - 97 - 23 * 234 / + :=


