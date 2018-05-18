package Parser;

import Lexer.Token;
import java.util.ArrayList;

public class Parser {
    private static ArrayList<Token> tokens;
    private static Token currentToken;
    private static int i = 0;

    public Parser() {
    }

    private void match() {
        currentToken = tokens.get(i);
        ++i;
    }

    private void check(String s) throws Exception {
        match();
        if (!currentToken.getType().equals(s)) {
            throw new Exception(s + " expected, but " + currentToken.getType() + " found!!!");
        }
    }

    public void parse(ArrayList<Token> token) {
        tokens = token;
        lang();
    }

    private void lang() {
        while(i < tokens.size() - 1) {
            expr();
        }
    }

    private void expr() {
        try {
            assign();
        } catch (Exception var2) {
            --i;
            cycle();
        }

    }

    private void assign() throws Exception {
        try {
            check("VAR");
            check("ASSIGN_OP");
            assign_value();
        } catch (Exception var2) {
            throw new Exception("Ne assign!!!");
        }
    }

    private void assign_value() {
        math_expr();
        for(currentToken = tokens.get(i); !currentToken.getType().equals("END") && !currentToken.getType().equals("R_B"); currentToken = tokens.get(i)) {
            try {
                check("OP");
                math_expr();
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }
        if (currentToken.getType().equals("END") && (tokens.get(0)).getType().equals("CYCLE")) {
            ++i;
        }
    }

    private void math_expr() {
        try {
            add_expr();
        } catch (Exception var2) {
            math_br();
        }

    }

    private void add_expr() throws Exception {
        try {
            value();
        } catch (Exception var3) {
            throw new Exception("ne add_expr!!!");
        }

        for(currentToken = tokens.get(i); !currentToken.getType().equals("END") && !currentToken.getType().equals("R_B"); currentToken = tokens.get(i)) {
            try {
                check("OP");
                value();
            } catch (Exception var2) {
                throw new Exception(".............");
            }
        }

    }

    private void value() throws Exception {
        try {
            check("VAR");
        } catch (Exception var4) {
            --i;

            try {
                check("DIGIT");
            } catch (Exception var3) {
                --i;
                throw new Exception("ne value!!!");
            }
        }

    }

    private void math_br() {
        try {
            check("L_B");
            assign_value();
            check("R_B");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    private void cycle() {
        try {
            check("CYCLE");
        } catch (Exception var2) {
            var2.printStackTrace();
        }
        comp();
        body();
    }

    private void comp() {
        try {
            check("L_B");
            check("VAR");
            check("COMP_OP");
            value();
            check("R_B");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    private void body() {
        try {
            check("L_F_B");
            while(!(tokens.get(i).getType().equals("R_F_B"))) {
                expr();
            }
            check("R_F_B");
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }
}
