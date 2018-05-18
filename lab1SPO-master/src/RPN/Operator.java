package RPN;

public enum Operator {
    PLUS(3,"+"),
    MINUS(3,"-"),
    MUL(4,"*"),
    DIV(4,"/"),
    L_B(1,"("),
    R_B(1,")"),
    ASSIGN_OP(2,"="),
    MORE(0,">"),
    LESS(0,"<"),
    SAME(0,"=="),
    WHILE(0,"while"),
    IF(0,"if"),
    PRINT(0,"print");

    int priority;
    String type;
    Operator(int priority, String type){
        this.priority = priority;
        this.type = type;
    }
}
