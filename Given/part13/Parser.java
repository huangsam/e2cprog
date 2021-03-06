public class Parser {

    // need a symbol table
    private Symtab symtab = new Symtab();

    // the first sets.
    // note: we cheat sometimes:
    // when there is only a single token in the set,
    // we generally just compare tkrep with the first token.
    TK f_declaration[] = {TK.VAR, TK.CONST, TK.none};
    TK f_var_decl[] = {TK.VAR, TK.none};
    //TK f_decl_id[] = {TK.VAR, TK.LBRACKET, TK.RBRACKET, TK.none};
    TK f_const_decl[] = {TK.CONST, TK.none};
    TK f_statement[] = {TK.ID, TK.PRINT, TK.IF, TK.WHILE, TK.FOR, TK.REPEAT, TK.none};
    TK f_print[] = {TK.PRINT, TK.none};
    TK f_assignment[] = {TK.ID, TK.none};
    TK f_if[] = {TK.IF, TK.none};
    TK f_while[] = {TK.WHILE, TK.none};
    TK f_for[] = {TK.FOR, TK.none};
    TK f_repeat[] = {TK.REPEAT, TK.UNTIL, TK.none};
    TK f_expression[] = {TK.ID, TK.NUM, TK.LPAREN, TK.none};

    // tok is global to all these parsing methods;
    // scan just calls the scanner's scan method and saves the result in tok.
    private Token tok; // the current token
    private void scan() {
        tok = scanner.scan();
    }

    private Scan scanner;
    
    Parser(Scan scanner) {
        this.scanner = scanner;
        scan();
        program();
        if( tok.kind != TK.EOF ) {
			//System.out.println(tok.kind);
            parse_error("junk after logical end of program" /*; STR,TK=" + tok.string + ',' + tok.kind */);
		}
    }

    // for code generation
    private static final int initialValueEVariable = 8888;
    private static final int initialArrayElemValue = 4444; // - for part 13

    // print something in the generated code
    private void gcprint(String str) {
        System.out.println(str);
    }
    // print identifier in the generated code
    // it prefixes x_ in case id conflicts with C keyword.
    private void gcprintid(String str) {
        System.out.println("x_"+str);
    }

    private void program() {
        gcprint("#include <stdio.h>");
        // part 14
        // include a bc() function for the array bound checking (as explained in the assignment)
//        int bc(int arrayName, int index) {
//        	int curSize = sizeOf(arrayName);
//        	if (index < 0 || index > curSize) {
//        		fprintf(stderr, "Index out of bounds");
//        		exit(1);
//        	}
//        	else {
//        		return 1;
//        	}
//        }
        // I have a script that from the above text produces the one below in case you need it too.
        gcprint(" int bc(int *arrayName, int index) {\n 		");
        gcprint("     int curSize = sizeof(arrayName);\n 		");
        gcprint("     if(index < 0 || index >= curSize) {\n 		");
        gcprint("     fprintf(stderr, \"%s\\n\", \"" + "index out of bounds" + '"'/*+ "\\n\""*/); //  \"index out of bounds\n\") ;\n 		");
        gcprint("\"%s\\n\", \"" + tok.string + '"' + ");\n 		");
        gcprint("     exit(1);\n 		");
        gcprint("     }\n 		");
        gcprint("     else {\n 		");
        gcprint("         return 1;\n 		");
        gcprint("     }\n 		");
        gcprint(" }      \n 		");
        // end part 14
        gcprint("main() ");
        block();
    }

    private void block() {
        gcprint("{");
        symtab.begin_st_block();
        while( first(f_declaration) ) {
            declaration();
        }
        while( first(f_statement) ) {
            statement();
        }
        symtab.end_st_block();
        gcprint("}");
     }

    private void declaration() {
        if (first(f_var_decl)) {
            var_decl();
        }
        else if (first(f_const_decl)) {
            const_decl();
        }
        else
            parse_error("oops -- declaration bad first");
    }

    private void var_decl() {
        mustbe(TK.VAR);
        var_decl_decl_id();
        while( is(TK.COMMA) ) {
            scan();
            var_decl_decl_id();
        }
    }    
    
    private void var_decl_id() {
        if( is(TK.ID) ) {
            if (symtab.add_entry(tok.string, tok.lineNumber, TK.VAR)) {
                gcprint("int ");
                gcprintid(tok.string);
                gcprint("="+initialValueEVariable+";");
            }
            scan();
        }
        else {
            parse_error("expected id in var declaration, got " + tok);
        }
    }
   
    private void var_decl_decl_id() {
    	if( is(TK.ID) ) {
    		if (symtab.add_entry(tok.string, tok.lineNumber, TK.VAR)) 
    		{
    			gcprint("int ");	
    			String name = tok.string;
    			scan();
    			if( is(TK.LBRACKET) ) 
    			{
    				scan();
    				String negative = "";
    				if( is(TK.MINUS) ) {
    					negative += "-";
    					scan();
    				}
    				Token tmptok = tok;
    				mustbe(TK.NUM);
    				int LB = Integer.parseInt(negative + tmptok.string);
    				mustbe(TK.COLON);
    				negative = "";
    				if( is(TK.MINUS) ) {
    					negative += "-";
    					scan();
    				}
    				tmptok = tok;
    				mustbe(TK.NUM);
    				int UB = Integer.parseInt(negative + tmptok.string);
    				int size = UB - LB;
    				if (size <= 0) {
    					parse_error("The size of the array is negative or 0:" + size );
    				}
    				gcprintid(name+ "[" + size + "];");
    				arrayInit(name, size);
    				scan();
    			} else {
    				gcprintid(name);
    				gcprint("="+initialValueEVariable+";");
    			}
    		} else {
    			scan();
    		}
    	} else {
    		parse_error("expected id in var declaration, got " + tok);
    	}
    }
    //if (is(TK.LBRACKET)) {
    // 		    //gcprint("[");
    // 		    bound();
    // 		    ub = Integer.ParseInt(tok.string);
    // 		    mustbe(TK.COLON);
    // 		    //gcprint(":");
    // 		    bound();
    // 		    lb = Integer.ParseInt(tok.string);
    // 		    //mustbe(TK.RBRACKET);
    // 		    //gcprint("]");
    // 		}
    //gcprint(initialValueEVariable);
    //gcprint(

    private void arrayInit(String name, int size) {
    	gcprint("{\nint i;");
    	gcprint("for(i=0; i < " +size+ "; i++) {");
    	gcprint("x_"+name+"[i]" +"="+initialArrayElemValue+";");
    	gcprint("}\n}");
    	symtab.search(name).setIsArray(true);
    }   

    private void const_decl() {
        mustbe(TK.CONST);
        boolean newConst = const_decl_id();
        mustbe(TK.EQ);
        if (newConst) {
            gcprint("=");
            gcprint(tok.string);
            gcprint(";");
        }
        mustbe(TK.NUM);
    }

    private boolean const_decl_id() {
        if( is(TK.ID) ) {
            boolean ret;
            if (ret = symtab.add_entry(tok.string, tok.lineNumber, TK.CONST)) {
                gcprint("int ");
                gcprintid(tok.string);
            }
            scan();
            return ret;
        }
        else {
            parse_error("expected id in const declaration, got " + tok);
            return false; // meaningless since parse_error doesn't return
        }
    }

    private void statement(){
        if( first(f_assignment) )
            assignment();
        else if( first(f_print) )
            print();
        else if( first(f_if) )
            ifproc();
        else if( first(f_while) )
            whileproc();
        else if( first(f_for) )
            forproc();
	else if( first(f_repeat) )
	    repeatproc();
        else
            parse_error("oops -- statement bad first");
    }

    private void assignment(){
        if( is(TK.ID) ) {
            lvalue_id(tok.string, tok.lineNumber);
            scan();
        }
        else {
            parse_error("missing id on left-hand-side of assignment");
        }
        mustbe(TK.ASSIGN);
        gcprint("=");
        expression();
        gcprint(";");
    }

    private void print(){
        mustbe(TK.PRINT);
	if(is(TK.DQUOTE)){
	    gcprint("printf(");
	    gcprint("\"%s\\n\", \"" + tok.string + '"'/*+ "\\n\""*/);
	    scan();
	}
	else{
          gcprint("printf(\"%d\\n\", ");
	  expression();
	}
	gcprint(");");
    }

    private void ifproc(){
        mustbe(TK.IF);
        gcprint("if(");
        expression();
        gcprint(")");
        mustbe(TK.THEN);
        block();
        while( is(TK.ELSIF) ) {
            scan();
            gcprint("else if(");
            expression();
            gcprint(")");
            mustbe(TK.THEN);
            block();
        }
        if( is(TK.ELSE) ) {
            scan();
            gcprint("else");
            block();
        }
        mustbe(TK.END);
    }

    private void whileproc(){
        mustbe(TK.WHILE);
        gcprint("while(");
        expression();
        gcprint(")");
        mustbe(TK.DO);
        block();
        mustbe(TK.END);
    }

    private void forproc(){
        mustbe(TK.FOR);
        gcprint("for(");
        String id = tok.string;
        Entry iv = null; // index variable in symtab
        if( is(TK.ID) ) {
	    if( symtab.search(id).getIsArray() ) {
	      System.err.println("array on left-hand-side of assignment (used as index variable) "
	      + id + " on line " + tok.lineNumber );
	      System.exit(1);
	    }
            iv = lvalue_id(tok.string, tok.lineNumber);
            iv.setIsIV(true); // mark Entry as IV
            scan();
        }
        else {
            parse_error("missing id on left-hand-side of assignment in for");
        }
        mustbe(TK.ASSIGN);
        gcprint("=");
        expression();
        gcprint(";");
        boolean up = true;
        if( is(TK.TO) ) {
            up = true;
        }
        else if( is(TK.DOWNTO) ) {
            up = false;
        }
        else
            parse_error("for statement is missing to/downto");
        scan();
        gcprintid(id);
        gcprint(up?"<=":">=");
        expression();
        mustbe(TK.DO);
        gcprint(";");
        gcprintid(id);
        gcprint(up?"++)":"--)");
        block();
        mustbe(TK.END);
        iv.setIsIV(false); // mark Entry as no longer IV
    }
    
    private void repeatproc(){
	mustbe(TK.REPEAT);
	gcprint("do"); // the '{' will be generated
	block();
        mustbe(TK.UNTIL);
	gcprint("while("); // the '}' will be generated
	expression2(); // part 12
	gcprint(");");
    }
    
    private void expression(){
        simple();
        while( is(TK.EQ) || is(TK.LT) || is(TK.GT) ||
               is(TK.NE) || is(TK.LE) || is(TK.GE)) {
            if( is(TK.EQ) ) gcprint("==");
            else if( is(TK.NE) ) gcprint("!=");
            else gcprint(tok.string);
            scan();
            simple();
        }
    }
    
    private void expression2(){
        simple();
        while( is(TK.EQ) || is(TK.LT) || is(TK.GT) ||
               is(TK.NE) || is(TK.LE) || is(TK.GE)) {
            if( is(TK.EQ) ) gcprint("!=");
            else if( is(TK.NE) ) gcprint("==");
      	    else if( is(TK.GT) ) gcprint("<="); // part 12
	    else if( is(TK.GE) ) gcprint("<"); // part 12
	    else if( is(TK.LT) ) gcprint(">="); // part 12
	    else if( is(TK.LE) ) gcprint(">"); // part 12
            else gcprint(tok.string);
            scan();
            simple();
        }
    }

    private void simple(){
        term();
        while( is(TK.PLUS) || is(TK.MINUS) ) {
            gcprint(tok.string);
            scan();
            term();
        }
    }

    private void term(){
        factor();
        while(  is(TK.TIMES) || is(TK.DIVIDE) ) {
            gcprint(tok.string);
            scan();
            factor();
        }
    }

    private void factor(){
        if( is(TK.LPAREN) ) {
            gcprint("(");
            scan();
            expression();
            mustbe(TK.RPAREN);
            gcprint(")");
        }
        else if( is(TK.ID) ) {
            rvalue_id(tok.string, tok.lineNumber);
            scan();
        }
        else if( is(TK.NUM) ) {
            gcprint(tok.string);
            scan();
        }
        else
            parse_error("factor");
    }

    private Entry lvalue_id(String id, int lno) {
        Entry e = symtab.search(id);
        if( e == null) {
            System.err.println("undeclared variable "+ id + " on line "
                               + lno);
            System.exit(1);
        }
        if( !e.isVar()) {
            System.err.println("constant on left-hand-side of assignment "+ id + " on line "
                               + lno);
            System.exit(1);
        }
        if( e.getIsIV()) {
            System.err.println("index variable on left-hand-side of assignment "+ id + " on line "
                               + lno);
            System.exit(1);
        }
        if( e.getIsArray() ) {
	    scan();
	  if( !is(TK.LBRACKET) ) {
	    System.err.println("missing subscript for array " + id + " on line " + lno );
	    System.exit(1);
	  }
	  scan();
	  Token tmptok = tok;
	  mustbe(TK.NUM);
	  gcprintid(id+"["+tmptok.string+"]");
	  return e;
	}
	gcprintid(id);
        return e;
    }

    private void rvalue_id(String id, int lno) {
        Entry e = symtab.search(id);
        if( e == null) {
            System.err.println("undeclared variable "+ id + " on line "
                               + lno);
            System.exit(1);
        }
        if( e.getIsArray() ) {
	    scan();
	   if( !is(TK.LBRACKET) ) {
	      System.err.println("missing subscript for array " + id + " on line " + lno );
	      System.exit(1);
	   }
	  scan();
	  Token tmptok = tok;
	  mustbe(TK.NUM);
	  gcprintid(id+"["+tmptok.string+"]");
	  return;
	}
        gcprintid(id);
    }


    // is current token what we want?
    private boolean is(TK tk) {
        return tk == tok.kind;
    }

    // ensure current token is tk and skip over it.
    private void mustbe(TK tk) {
        if( ! is(tk) ) {
            System.err.println( "mustbe: want " + tk + ", got " +
                                    tok);
            parse_error( "missing token (mustbe)" );
        }
        scan();
    }
    boolean first(TK [] set) {
        int k = 0;
        while(set[k] != TK.none && set[k] != tok.kind) {
            k++;
        }
        return set[k] != TK.none;
    }

    private void parse_error(String msg) {
        System.err.println( "can't parse: line "
                            + tok.lineNumber + " " + msg );
        System.exit(1);
    }
}
