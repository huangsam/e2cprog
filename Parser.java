public class Parser {

	// need a symbol table
	private Symtab symtab = new Symtab();

	// the first sets.
	// note: we cheat sometimes:
	// when there is only a single token in the set,
	// we generally just compare tkrep with the first token.
	TK f_declaration[] = { TK.VAR, TK.CONST, TK.none };
	TK f_var_decl[] = { TK.VAR, TK.none };
	TK f_const_decl[] = { TK.CONST, TK.none };
	TK f_statement[] = { TK.ID, TK.PRINT, TK.IF, TK.WHILE, TK.FOR, TK.REPEAT, TK.EVERY,
			TK.none };
	TK f_print[] = { TK.PRINT, TK.none };
	TK f_assignment[] = { TK.ID, TK.none };
	TK f_if[] = { TK.IF, TK.none };
	TK f_while[] = { TK.WHILE, TK.none };
	TK f_for[] = { TK.FOR, TK.none };
	TK f_repeat[] = { TK.REPEAT, TK.UNTIL, TK.none };
	TK f_expression[] = { TK.ID, TK.NUM, TK.LPAREN, TK.none };
	TK f_every[] = { TK.EVERY, TK.none };

	// tok is global to all these parsing methods;
	// scan just calls the scanner's scan method and saves the result in tok.
	private Token tok; // the current token

	private boolean boundCheck = false; // part 14

	private void scan() {
		tok = scanner.scan();
	}

	private Scan scanner;

	Parser(Scan scanner) {
		this.scanner = scanner;
		scan();
		program();
		if (tok.kind != TK.EOF) {
			// System.out.println(tok.kind);
			parse_error("junk after logical end of program" /*
									 * ; STR,TK=" +
									 * tok.string + ','
									 * + tok.kind
									 */);
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
		System.out.println("x_" + str);
	}

	private void program() {
		gcprint("#include <stdio.h>");
		gcprint("#include <stdlib.h>");
		gcprint("int bc(int *a, int ln, int exp, char arName);"); // part 14
		gcprint("int everyIndex;"); // part 15
		gcprint("main() ");
		block();
		if (boundCheck) // part 14
			generateBoundCheck();
	}

	private void generateBoundCheck() { // part 14
		gcprint("int bc(int *a, int ln, int exp, char arName)\n{");
		gcprint("int subscript = exp - a[0] + 2;");
		gcprint("if( subscript < a[1] + 2 && subscript >= 2 )\nreturn subscript;");
		gcprint("fprintf(stderr, \"subscript (%d) out of bounds for array \", exp );");
		gcprint("fprintf(stderr, \"%c[%d:%d] on line %d\\n\",arName,a[0],a[0]+a[1]-1,ln );");
		gcprint("exit(1);");
		gcprint("return 0;\n}");
	}

	private void block() {
		gcprint("{");
		symtab.begin_st_block();
		while (first(f_declaration)) {
			declaration();
		}
		while (first(f_statement)) {
			statement();
		}
		symtab.end_st_block();
		gcprint("}");
	}

	private void declaration() {
		if (first(f_var_decl)) {
			var_decl();
		} else if (first(f_const_decl)) {
			const_decl();
		} else
			parse_error("oops -- declaration bad first");
	}

	private void var_decl() {
		mustbe(TK.VAR);
		var_decl_id();
		while (is(TK.COMMA)) {
			scan();
			var_decl_id();
		}
	}
	
	private void var_decl_id() {
		if (is(TK.ID)) {
			if (symtab.add_entry(tok.string, tok.lineNumber, TK.VAR)) {
				int ub, lb;
				gcprint("int ");
				String arrayName = tok.string;
				scan();
				if (is(TK.LBRACKET)) {
					scan();
					String negative = "";
					if (is(TK.MINUS)) {
						negative += "-";
						scan();
					}
					Token temptok = tok;
					mustbe(TK.NUM);
					lb = Integer.parseInt(negative + temptok.string);
					mustbe(TK.COLON);
					negative = "";
					if (is(TK.MINUS)) {
						negative += "-";
						scan();
					}
					temptok = tok;
					mustbe(TK.NUM);
					ub = Integer.parseInt(negative + temptok.string);
					int arraySize = ub - lb + 1;
					if (arraySize <= 0) {
						System.err.println("declared size of " + arrayName
								+ " is <= 0 (" + arraySize + ") on line "
								+ tok.lineNumber);
						System.exit(1);
					}
					arrayInit(arrayName, arraySize, lb);
					scan();
				} else {
					gcprintid(arrayName);
					gcprint("=" + initialValueEVariable + ";");
				}
			} else {
				scan();
				if (is(TK.LBRACKET)) {
					do {
						scan();
					} while (!is(TK.RBRACKET));
					scan();
				}
			}
		} else {
			parse_error("expected id in var declaration, got " + tok);
		}
	}

	private void arrayInit(String arrayName, int arraySize, int lb) {
		String initialArrayValues = "";
		for (int i = 0; i <= arraySize; i++) {
			initialArrayValues += initialArrayElemValue + ", ";
		}
		gcprintid(arrayName + "[] = {" + lb + ", " + arraySize + ", "
				+ initialArrayValues + "};");
		symtab.search(arrayName).setIsArray(true);
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
		if (is(TK.ID)) {
			boolean ret;
			if (ret = symtab.add_entry(tok.string, tok.lineNumber, TK.CONST)) {
				gcprint("int ");
				gcprintid(tok.string);
			}
			scan();
			return ret;
		} else {
			parse_error("expected id in const declaration, got " + tok);
			return false; // meaningless since parse_error doesn't return
		}
	}

	private void statement() {
		if (first(f_assignment))
			assignment();
		else if (first(f_print))
			print();
		else if (first(f_if))
			ifproc();
		else if (first(f_while))
			whileproc();
		else if (first(f_for))
			forproc();
		else if (first(f_repeat))
			repeatproc();
		else if (first(f_every))
			everyproc();
		else
			parse_error("oops -- statement bad first");
	}
	

	private void assignment() {
		if (is(TK.ID)) {
			Token temptok = tok;
			lvalue_id(tok.string, tok.lineNumber);
			scan();
			if (is(TK.LBRACKET)) {
				System.err.println("subscripting non-array " + temptok.string
						+ " on line " + temptok.lineNumber);
				System.exit(1);
			}
		} else {
			parse_error("missing id on left-hand-side of assignment");
		}
		mustbe(TK.ASSIGN);
		gcprint("=");
		expression();
		gcprint(";");
	}

	private void print() {
		mustbe(TK.PRINT);
		if (is(TK.DQUOTE)) {
			gcprint("printf(");
			gcprint("\"%s\\n\", \"" + tok.string + '"'/* + "\\n\"" */);
			scan();
		} else {
			gcprint("printf(\"%d\\n\", ");
			expression();
		}
		gcprint(");");
	}

	private void ifproc() {
		mustbe(TK.IF);
		gcprint("if(");
		expression();
		gcprint(")");
		mustbe(TK.THEN);
		block();
		while (is(TK.ELSIF)) {
			scan();
			gcprint("else if(");
			expression();
			gcprint(")");
			mustbe(TK.THEN);
			block();
		}
		if (is(TK.ELSE)) {
			scan();
			gcprint("else");
			block();
		}
		mustbe(TK.END);
	}

	private void whileproc() {
		mustbe(TK.WHILE);
		gcprint("while(");
		expression();
		gcprint(")");
		mustbe(TK.DO);
		block();
		mustbe(TK.END);
	}

	private void everyproc() {
		mustbe(TK.EVERY);
		int index = 0;
		if(is(TK.ELEMENT)) {
			index= 0;
			mustbe(TK.ELEMENT);
		} else if (is(TK.INDEX)) {
			index= 1;
			mustbe(TK.INDEX);
		} 
		int reverse = 0;
		if(is(TK.FORWARD)) {
			reverse = 0;
			mustbe(TK.FORWARD);
		} else if (is(TK.REVERSE)) {
			reverse = 1;
			mustbe(TK.REVERSE);
		}
		Token indexTok = tok;
		mustbe(TK.ID);
		mustbe(TK.COLON);
		Token arrayTok = tok;
		mustbe(TK.ID);
		mustbe(TK.DO);
		gcprint("{");
		gcprint("int ");
		String indexName = indexTok.string;//+arrayTok.string ;
		gcprintid(indexName);
		gcprint(";\n");
		
		symtab.add_entry(indexName, indexTok.lineNumber, TK.VAR);
		Entry iv = symtab.search(indexName);
		iv.setIsIV( true );
//		symtab.add_entry(tok.string, tok.lineNumber, TK.VAR)
//		Entry iv = lvalue_id(, indexTok.lineNumber); 
		
//		for (index=2;index=sizeof( array );index++)
		String cindexName = "index"+indexTok.string;
		gcprint("int "+cindexName+";");
		
		gcprint("for(");
		if(reverse==1){
			/*
			 * 	for a reversed for loop the c bounds are 
				from j = a[1]-1; 
				to j >= 0;
				j--
				the e bounds are:
				from e = j +a[0]
				to e = j +a[0]
				e = a[1]f +a[0] 

			 */
			
			gcprint(cindexName+"=(");
			gcprintid(arrayTok.string);
			gcprint("[1]-1);"+cindexName+">=0;"+cindexName+"--)");
		} else {
			/* 
			 * for a forward for loop the c bounds are:
			 * from j = 0
			 * to j < a[1]
			 * j++
			 * the e bounds are:
			 * from e = j+a[0]
			 */
			gcprint(cindexName+"=0;"+cindexName+"<");
			gcprintid(arrayTok.string);
			gcprint("[1];"+cindexName+"++)");
		}
		gcprint("{");
		
		gcprintid(indexName);
		gcprint(" = ");
		if (index==1) {
			/*
			 * if in c index = 0
			 * then e index = a[0]
			 * if in c index = size 
			 * then e index = a[1]
			 */
			gcprint(cindexName+"+");
			gcprintid(arrayTok.string);
			gcprint("[0];");
		} else {
			gcprintid(arrayTok.string);
			gcprint("["+cindexName+"-");
			gcprintid(arrayTok.string);
			gcprint("[0]+1];");
		}
			
			
		block();
		mustbe(TK.END);
		gcprint("	}");
		gcprint("}");
	}

	private void forproc() {
		mustbe(TK.FOR);
		gcprint("for(");
		String id = tok.string;
		Entry iv = null; // index variable in symtab
		if (is(TK.ID)) {
			if (symtab.search(id) != null && symtab.search(id).getIsArray()) {
				System.err
						.println("array on left-hand-side of assignment (used as index variable) "
								+ id + " on line " + tok.lineNumber);
				System.exit(1);
			}
			iv = lvalue_id(tok.string, tok.lineNumber);
			iv.setIsIV(true); // mark Entry as IV
			scan();
		} else {
			parse_error("missing id on left-hand-side of assignment in for");
		}
		mustbe(TK.ASSIGN);
		gcprint("=");
		expression();
		gcprint(";");
		boolean up = true;
		if (is(TK.TO)) {
			up = true;
		} else if (is(TK.DOWNTO)) {
			up = false;
		} else
			parse_error("for statement is missing to/downto");
		scan();
		gcprintid(id);
		gcprint(up ? "<=" : ">=");
		expression();
		mustbe(TK.DO);
		gcprint(";");
		gcprintid(id);
		gcprint(up ? "++)" : "--)");
		block();
		mustbe(TK.END);
		iv.setIsIV(false); // mark Entry as no longer IV
	}

	private void repeatproc() {
		mustbe(TK.REPEAT);
		gcprint("do"); // the '{' will be generated
		block();
		mustbe(TK.UNTIL);
		gcprint("while("); // the '}' will be generated
		expression2(); // part 12
		gcprint(");");
	}

	private void expression() {
		simple();
		while (is(TK.EQ) || is(TK.LT) || is(TK.GT) || is(TK.NE) || is(TK.LE)
				|| is(TK.GE)) {
			if (is(TK.EQ))
				gcprint("==");
			else if (is(TK.NE))
				gcprint("!=");
			else
				gcprint(tok.string);
			scan();
			simple();
		}
	}

	private void expression2() {
		simple();
		while (is(TK.EQ) || is(TK.LT) || is(TK.GT) || is(TK.NE) || is(TK.LE)
				|| is(TK.GE)) {
			if (is(TK.EQ))
				gcprint("!=");
			else if (is(TK.NE))
				gcprint("==");
			else if (is(TK.GT))
				gcprint("<="); // part 12
			else if (is(TK.GE))
				gcprint("<"); // part 12
			else if (is(TK.LT))
				gcprint(">="); // part 12
			else if (is(TK.LE))
				gcprint(">"); // part 12
			else
				gcprint(tok.string);
			scan();
			simple();
		}
	}

	private void simple() {
		term();
		while (is(TK.PLUS) || is(TK.MINUS)) {
			gcprint(tok.string);
			scan();
			term();
		}
	}

	private void term() {
		factor();
		while (is(TK.TIMES) || is(TK.DIVIDE)) {
			gcprint(tok.string);
			scan();
			factor();
		}
	}

	private void factor() {
		if (is(TK.LPAREN)) {
			gcprint("(");
			scan();
			expression();
			mustbe(TK.RPAREN);
			gcprint(")");
		} else if (is(TK.ID)) {
			String ID = tok.string;
			rvalue_id(tok.string, tok.lineNumber);
			scan();
			if (is(TK.LBRACKET)) {
				System.err.println("subscripting non-array " + ID + " on line "
						+ tok.lineNumber);
				System.exit(1);
			}
		} else if (is(TK.NUM)) {
			gcprint(tok.string);
			scan();
		} else
			parse_error("factor");
	}

	private Entry lvalue_id(String id, int lno) {
		Entry e = symtab.search(id);
		if (e == null) {
			System.err.println("undeclared variable " + id + " on line " + lno);
			System.exit(1);
		}
		if (!e.isVar()) {
			System.err.println("constant on left-hand-side of assignment " + id
					+ " on line " + lno);
			System.exit(1);
		}
		if (e.getIsIV()) {
			System.err
					.println("index variable on left-hand-side of assignment "
							+ id + " on line " + lno);
			System.exit(1);
		}
		if (e.getIsArray()) {
			scan();
			if (!is(TK.LBRACKET)) {
				System.err.println("missing subscript for array " + id
						+ " on line " + lno);
				System.exit(1);
			}
			scan();
			boundCheck = true; // part 14
			/* (int *a, int ln, int exp, char arName) */
			gcprintid(id + "[bc(x_" + id + ", " + lno + ", "); // part 14
			expression();
			gcprint(", '" + id + "')]"); // part 14
			// gcprint("-x_"+id+"[0]+2]");
			return e;
		}
		gcprintid(id);
		return e;
	}

	private void rvalue_id(String id, int lno) {
		Entry e = symtab.search(id);
		if (e == null) {
			System.err.println("undeclared variable " + id + " on line " + lno);
			System.exit(1);
		}
		if (e.getIsArray()) {
			scan();
			if (!is(TK.LBRACKET)) {
				System.err.println("missing subscript for array " + id
						+ " on line " + lno);
				System.exit(1);
			}
			scan();
			boundCheck = true; // part 14
			/* (int *a, int ln, int exp, char arName) */
			gcprintid(id + "[bc(x_" + id + ", " + lno + ", "); // part 14
			expression();
			gcprint(", '" + id + "')]"); // part 14
			// gcprint("-x_"+id+"[0]+2]");
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
		if (!is(tk)) {
			System.err.println("mustbe: want " + tk + ", got " + tok);
			parse_error("missing token (mustbe)");
		}
		scan();
	}

	boolean first(TK[] set) {
		int k = 0;
		while (set[k] != TK.none && set[k] != tok.kind) {
			k++;
		}
		return set[k] != TK.none;
	}

	private void parse_error(String msg) {
		System.err.println("can't parse: line " + tok.lineNumber + " " + msg);
		System.exit(1);
	}
}
