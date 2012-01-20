import java.util.*;

class Entry {
    private String name;        // name of symbol.
    private int lineNumber;     // line on which declared
    private TK varOrConst;      // variable or const?
    private boolean isIV ;      // is presently index variable?
    private boolean isArray;	// is the variable an array?

    public Entry(String name, int lineNumber, TK varOrConst) {
        this.name = name;
        this.lineNumber = lineNumber;
        this.varOrConst = varOrConst;
        this.isIV = false;
	this.isArray = false;
    }
    String getName() {
        return name;
    }
    int getLineNumber() {
        return lineNumber;
    }
    boolean isVar() {
        return (varOrConst == TK.VAR);
    }
    boolean isConst() {
        return (varOrConst == TK.CONST);
    }
    boolean getIsIV() {
        return isIV;
    }
    void setIsIV(boolean is) {
        isIV = is;
    }
    void setIsArray(boolean is) { // part 13
	isArray = is;      
    }
    boolean getIsArray() {	// part 13
	return isArray;      
    }
    
    public String whatAreYou() {
        return  isVar()?"variable"
               :isConst()?"constant"
               :"OOPS Entry whatAreYou()";
    }
    public String toString() {
        return name + "  declared on line " + lineNumber + " as "+ varOrConst;
    }
}
