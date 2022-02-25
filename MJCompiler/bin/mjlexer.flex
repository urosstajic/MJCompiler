





package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	private Symbol new_symbol(int type){
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	private Symbol new_symbol(int type, Object value){
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}


%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" "  { }
"\b" { }
"\t" { }
"\r\n" { }
"\f" { }

"program"       {return new_symbol(sym.PROGRAM, yytext());}
"read"  		 { return new_symbol(sym.READ, yytext());}
"print" 		{ return new_symbol(sym.PRINT, yytext()); }
"goto" 			{ return new_symbol(sym.GOTO, yytext()); }
"new" 			{ return new_symbol(sym.NEW, yytext()); }
"record" 		{ return new_symbol(sym.RECORD, yytext()); }
"if" 			{ return new_symbol(sym.IF, yytext()); }
"else" 			{ return new_symbol(sym.ELSE, yytext()); }
"do" 			{ return new_symbol(sym.DO, yytext()); }
"while" 		{ return new_symbol(sym.WHILE, yytext()); }
"break" 		{ return new_symbol(sym.BREAK, yytext()); }
"continue"	 	{ return new_symbol(sym.CONTINUE, yytext()); }
"return" 		{ return new_symbol(sym.RETURN, yytext()); }
"void" 			{ return new_symbol(sym.VOID, yytext()); }
"const" 		{ return new_symbol(sym.CONST, yytext()); }
"class" 		{ return new_symbol(sym.CLASS, yytext()); }
"extends" 		{ return new_symbol(sym.EXTENDS, yytext()); }
"final" 		{ return new_symbol(sym.FINALL, yytext()); }
"+" 			{ return new_symbol(sym.PLUS, yytext()); }
"=" 			{ return new_symbol(sym.JEDNAKO, yytext()); }
";" 			{ return new_symbol(sym.TACKAZAREZ, yytext()); }
"," 			{ return new_symbol(sym.ZAREZ, yytext()); }
"(" 			{ return new_symbol(sym.LEVA_ZAGRADA, yytext()); }
")" 			{ return new_symbol(sym.DESNA_ZAGRADA, yytext()); }
"{" 			{ return new_symbol(sym.LEVA_VITICASTA, yytext()); }
"}"				{ return new_symbol(sym.DESNA_VITICASTA, yytext()); }
"*"				{ return new_symbol(sym.PUTA, yytext()); }
":"				{ return new_symbol(sym.DVETACKE, yytext()); }
"%"				{ return new_symbol(sym.POSTO, yytext()); }
"||"			{ return new_symbol(sym.ILI, yytext()); }
"&&"			{ return new_symbol(sym.II, yytext()); }
"/"				{ return new_symbol(sym.PODELJENO, yytext()); }
"-"				{ return new_symbol(sym.MINUS, yytext()); }
"++"			{ return new_symbol(sym.PLUSPLUS, yytext()); }
"--"			{ return new_symbol(sym.MINUSMINUS, yytext()); }
"["				{ return new_symbol(sym.OTVORENA_UGLASTA, yytext()); }
"]"				{ return new_symbol(sym.ZATVORENA_UGLASTA, yytext()); }
"=="			{ return new_symbol(sym.JEDNAKOJEDNAKO, yytext()); }
"!="			{ return new_symbol(sym.RAZLICITO, yytext()); }
">"				{ return new_symbol(sym.VECE, yytext()); }
"<"				{ return new_symbol(sym.MANJE, yytext()); }
">="			{ return new_symbol(sym.VECEJEDNAKO, yytext()); }
"<="			{ return new_symbol(sym.MANJEJEDNAKO, yytext()); }
"."				{ return new_symbol(sym.TACKA, yytext()); }
"@"				{ return new_symbol(sym.MAJMUN, yytext()); }
"+++" 			{ return new_symbol(sym.TRIPLUS, yytext()); }


"//" {yybegin(COMMENT);}
<COMMENT> . {yybegin(COMMENT);}
<COMMENT> "\r\n" { yybegin(YYINITIAL); }

[0-9]+  { return new_symbol(sym.NUM_CONST, new Integer (yytext())); }
"true" | "false"  { return new_symbol(sym.BOOL_CONST, yytext()); }
([a-z]|[A-Z])[a-zA-Z0-9_]* 	{return new_symbol(sym.IDENT, yytext()); }
"'" [ -~]  "'"  { return new_symbol(sym.CHAR_CONST, yytext()); }

. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }