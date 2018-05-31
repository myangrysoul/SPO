import Lexer.Lexer;
import Lexer.Token;

import RPN.RPN;
import Parser.Parser1;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;

import StackMachine.StackMachine;



public class Main{

    public static void main(String[] args) throws IOException {

        ArrayList<Token> tokens;
        final String input = new String(Files.readAllBytes(Paths.get("D:/testim/lab1SPO-master/src", "proga.txt")),"UTF-8");
        Lexer lexer = new Lexer();
        tokens = lexer.getTokenList(input);
        long start = System.nanoTime();
        new Parser1(tokens);
        long finish = System.nanoTime();
        RPN rpn = new RPN(tokens);
        StackMachine stackMachine=new StackMachine(rpn.toRPN());
        stackMachine.calculation();
        System.out.println("\n\n"+"Execution time = "+((finish-start)/Math.pow(10,6))+" ms");


    }
}