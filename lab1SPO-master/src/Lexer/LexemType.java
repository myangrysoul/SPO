package Lexer;

import java.util.regex.Pattern;

public enum LexemType {
    CYCLE("CYCLE",Pattern.compile("^while|if"),0),
    PRINT("PRINT",Pattern.compile("^print"),0),
    DIGIT("DIGIT", Pattern.compile("^(0|[1-9][0-9]*)"),0),
    TYPE("TYPE", Pattern.compile("^type"),1),
    GET("GET",Pattern.compile("^[.]get"),5),
    METHOD("METHOD",Pattern.compile("^[.](get|set|add|contains|remove|size)"),5),
    LIST("LIST",Pattern.compile("^List"),0),
    HASHSET("HASHSET",Pattern.compile("^HashSet"),0),
    STRING("STRING",Pattern.compile("^String"),0),
    VAR("VAR", Pattern.compile("^([a-zA-Z]+[0-9]*[a-zA-Z]*)"),0),
    COMP_OP("COMP_OP",Pattern.compile("^(==|<|>)"),1),
    ASSIGN_OP("ASSIGN_OP", Pattern.compile("^(:=)"),2),
    OP_ADD_SUB("OP_ADD_SUB", Pattern.compile("^\\+|-"),3),
    OP_DIV_MUL("OP_DIV_MUL", Pattern.compile("^\\*|/"),4),
    SPACE("SPACE",Pattern.compile("^\\s"),0),
    JUMP("JUMP",Pattern.compile("^!"),0),
    J_C("J_C",Pattern.compile("^!F"),0),
    L_B("L_B",Pattern.compile("^[(]"),0),
    R_B("R_B",Pattern.compile("^[)]"),0),
    L_F_B("L_F_B",Pattern.compile("^[{]"),0),
    R_F_B("R_F_B",Pattern.compile("^[}]"),0),
    END("END",Pattern.compile("^[;]"),0),
    COMA("COMA",Pattern.compile("^[,]"),0);

    private String type;
    private Pattern pattern;
    private int priority;

    LexemType(String type, Pattern pattern,int priority){
        this.type = type;
        this.pattern = pattern;
        this.priority = priority;
    }

    public String getType(){
        return type;
    }
    public Pattern getPattern(){
        return pattern;
    }

    public int getPriority() {
        return priority;
    }
}
