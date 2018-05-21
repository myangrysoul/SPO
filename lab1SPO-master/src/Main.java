import Handler.*;
import Lexer.Lexer;
import Lexer.Token;
import RPN.RPN;
import Parser.Parser1;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main{

    public static void main(String[] args) throws IOException {
        ArrayList<Token> tokens;
        final String input = new String(Files.readAllBytes(Paths.get("D:/testim/lab1SPO-master/src", "proga.txt")),"UTF-8");
        Lexer lexer = new Lexer();
        long start = System.nanoTime();
        tokens = lexer.getTokenList(input);
        Parser1 parser = new Parser1(tokens);
        parser.parse();
        RPN rpn = new RPN(tokens);
        int i=0;
        for (Token token: rpn.toRPN()){
            System.out.print(i+" "+token.getValue()+"\n");
            i++;
        }
        long finish = System.nanoTime();
        System.out.println("\n\n"+"Execution time = "+((finish-start)/Math.pow(10,6))+" ms");
    }
}