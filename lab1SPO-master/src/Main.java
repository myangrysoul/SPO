import Lexer.Lexer;
import Lexer.Token;

import RPN.RPN;
import Parser.Parser1;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;

import StackMachine.StackMachine;



public class Main{

    public static void main(String[] args) throws IOException{

        ArrayList<Token> tokens;
        final String input;
        URL url=Main.class.getClassLoader().getResource("proga.txt");
        assert url != null;

        try {
            input = new String(Files.readAllBytes(Paths.get(url.toURI())));
        }
        catch (URISyntaxException e)
        {
            throw new RuntimeException("Error occurred with reading of file");
        }

        long start = System.nanoTime();
        Lexer lexer = new Lexer();
        tokens = lexer.getTokenList(input);
        new Parser1(tokens);
        RPN rpn = new RPN(tokens);
        StackMachine stackMachine=new StackMachine(rpn.toRPN());
        stackMachine.calculation();
        long finish = System.nanoTime();
        System.out.println("\n\n"+"Execution time = "+((finish-start)/Math.pow(10,6))+" ms");


    }
}