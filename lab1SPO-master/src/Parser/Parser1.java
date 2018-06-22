package Parser;

import Lexer.Token;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser1 {

    private static final Token EOF = new Token("EOF", "");

    private final ArrayList<Token> tokens;

    private int pos;

    private final int size;

    public Parser1(ArrayList<Token> token) {
        this.tokens = token;
        size = tokens.size();
        lang();
    }

    private Token get(int relative) {
        final int position = pos + relative;
        if (position >= size) return EOF;
        return tokens.get(position);
    }

    private boolean match(String type, int args) {
        Pattern pattern;
        Matcher matcher;
        StringBuilder match=new StringBuilder();
        Token current;
        for (int i = 0; i < args; i++) {
            current = get(i);
            match.append(current.getType()).append(" ");
        }
        match.deleteCharAt(match.length() - 1);

        pattern = Pattern.compile(type);
        matcher = pattern.matcher(match.toString());
        if (matcher.matches()) {
            pos += args;
            return true;
        } else
            return false;
    }

    private void lang() {
        while (get(0) != EOF) {
            if (!expr()) {
                throw new RuntimeException("Syntax error");
            }
        }

    }

    private boolean expr() {

        return (match("VAR ASSIGN_OP", 2) && assign()) ||
                (match("VAR TYPE", 2) && type()) ||
                (match("CYCLE", 1) && cycle()) ||
                (match("PRINT", 1) && print())||
                (match("VAR (METHOD|GET)",2)&&method());

    }

    private boolean assign() {
        return assign_value() && match("END", 1);
    }

    private boolean assign_value() {
        return math_expr() && (!match("OP_DIV_MUL|OP_ADD_SUB", 1) || assign_value());
    }

    private boolean math_expr() {
        return add_expr() || math_br();
    }

    private boolean add_expr()  {
        return value() && (!match("OP_DIV_MUL|OP_ADD_SUB", 1) || add_expr());
    }

    private boolean math_br() {
        return match("L_B", 1) && assign_value() && match("R_B", 1);
    }

    private boolean value() {
        return methodget()||match("VAR|DIGIT",1);
    }
    private boolean methodget(){
        return match("VAR GET",2) &&match("L_B",1)&&value()&&match("R_B",1);

    }
    //a.add(8)||a.set(a,9);
    private boolean method(){
        return  (get(-1).getValue().equals(".set"))&&match("L_B",1)
                &&value()&&match("COMA",1)&&value()&&match("R_B END",2)||
                match("L_B",1) &&value()&&match("R_B END",2);


    }

    private boolean cycle() {
        return compare()& body();
    }

    private boolean compare() {
        return match("L_B VAR COMP_OP DIGIT R_B", 5);
    }
    private boolean cycleBody(){
        return (match("R_F_B",1))||(expr()&&cycleBody());
    }


    private boolean body() {
        return match("L_F_B", 1) &&(match("R_F_B", 1)|| cycleBody());
    }

    private boolean printBody() {
        return value() && (!match("COMA", 1) || printBody());
    }

    private boolean print() {

        return match("L_B", 1) && printBody() && match("R_B END", 2);
    }

    private boolean type() {
        return match("(STRING|LIST|HASHSET) END", 2);
    }
}