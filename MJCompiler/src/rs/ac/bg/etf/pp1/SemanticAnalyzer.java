package rs.ac.bg.etf.pp1;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;
import rs.ac.bg.etf.pp1.ast.AddO1;
import rs.ac.bg.etf.pp1.ast.GlobalDeclImeProm;
import rs.ac.bg.etf.pp1.ast.GlobalType;
import rs.ac.bg.etf.pp1.ast.PovrTip1;
import rs.ac.bg.etf.pp1.ast.SamoJedno;
import rs.ac.bg.etf.pp1.ast.DesignatorStatement;
import rs.ac.bg.etf.pp1.ast.FormalParamDecl;
import rs.ac.bg.etf.pp1.ast.TipConst;
import rs.ac.bg.etf.pp1.ast.Zagrade;
import rs.ac.bg.etf.pp1.ast.Fac5;


public class SemanticAnalyzer extends VisitorAdaptor{
	
	
	Logger log = Logger.getLogger(getClass());
	
	//Obj boolType=new Obj(Struct.Bool, "bool", new Struct(Struct.Bool));
	//Struct boolStruct=new Struct(Struct.Bool);
	
	boolean zafinal=false;
	boolean returnFound = false;
	boolean errorDetected = false;
	Obj currentMethod = null;
	Struct currType=null;
	boolean ne_dodaj=false;
	boolean da_li_je_niz=false;
	boolean u_klasi=false;
	boolean element_niza=false;
	boolean in_meth=false;
	int broj_formalnih_param=0;
	boolean do_while_petlja=false;
	int nVars;
	Struct currTypeMeth=null;
	Obj currDesignator=null;
	Obj currDesignatorNiz=null;
	Obj currDesignatorRekord=null;
	Obj currClass=null;
	Struct izvedeno_iz=null;
	List<Obj> imenaKlasa=new ArrayList<>();
	List<List<Obj>> metodaPoljaSve=new ArrayList<>();
	List<Obj> metodaPoljaJedno=new ArrayList<>();
	Obj zaProg=null;
	
	Stack<Obj> currDesigNiz=new Stack<Obj>();
	
	
	List<Obj> samoMetodeSve=new ArrayList<>();
	List<List<Obj>> samoMetodeJedno=new ArrayList<>();
	String izvedenoIzIme="?";
	List<String> imenaRecord=new ArrayList<>();
	boolean u_metodi=false;
	ClassName dodajNakonExtends=null;
	
	
	List<Obj> paramJednaMetoda=new ArrayList<>();
	List<List<Obj>> paramSveMetode=new ArrayList<>();
	List<Struct> prosledjeniParam=new ArrayList<>();
	List<String> listaLabela = new ArrayList<>();
	
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	
	public void visit(ProgName progName){
		//MojaTabela.insert(Obj.Type, "bool", boolStruct);
		Obj ha=MojaTabela.find("ord");
		Collection<Obj> kolek=ha.getLocalSymbols();
		for (Obj obj : kolek) {
			paramJednaMetoda.add(obj);
		}
		
		samoMetodeSve.add(ha);
		paramSveMetode.add(paramJednaMetoda);
		paramJednaMetoda=new ArrayList<>();
		
		ha=MojaTabela.find("chr");
		samoMetodeSve.add(ha);
		kolek=ha.getLocalSymbols();
		for (Obj obj : kolek) {
			paramJednaMetoda.add(obj);
		}
		paramSveMetode.add(paramJednaMetoda);
		paramJednaMetoda=new ArrayList<>();
		
		ha=MojaTabela.find("len");
		samoMetodeSve.add(ha);
		kolek=ha.getLocalSymbols();
		for (Obj obj : kolek) {
			paramJednaMetoda.add(obj);
		}
		paramSveMetode.add(paramJednaMetoda);
		paramJednaMetoda=new ArrayList<>();
		
		
		progName.obj = MojaTabela.insert(Obj.Prog, progName.getProgName(), MojaTabela.noType);
		MojaTabela.openScope();
	}
	
	public void visit(Program program) {
		nVars = MojaTabela.currentScope.getnVars();
		
		/*Obj zaMain = Tab.find("main");
		if(zaMain!=Tab.noObj && zaMain.getKind()==Obj.Meth && zaMain.getType()==Tab.noType && zaMain.getLevel()==0) {
			report_info("Postoji main", program);
		}else {
			report_error("Ne postoji definisan main", program);
		}*/
		SymbolDataStructure pom= MojaTabela.currentScope.getLocals();
		zaProg=program.getProgName().obj;
		zaProg.getType().setMembers(MojaTabela.currentScope.getLocals());
		program.obj=zaProg;
		MojaTabela.chainLocalSymbols(program.getProgName().obj);
		MojaTabela.closeScope();
	}
	
	public void visit(KrajPrograma kraj) {
		Obj zaMain = MojaTabela.find("main");
		if(zaMain!=MojaTabela.noObj && zaMain.getKind()==Obj.Meth && zaMain.getType()==MojaTabela.noType) {
			report_info("Postoji main", kraj);
		}else {
			report_error("Ne postoji definisan main", kraj);
		}
	}
	
	public void visit(GlobalDeclImeProm glbDeclImeProm) {
		
		
	}
	
	public void visit(GlobalDecl glb) {
		currType=null;
		ne_dodaj=false;
		zafinal=false;
	}
	
	public void visit(GlobalDeclName glb) {
		if(ne_dodaj==true) {
			return;
		}
		if(!da_li_vec_postoji_simbol(glb.getGlobalDeclImeProm().getVarName())) {
			if(glb.getZagrade() instanceof Zagrade1) {
				Obj pom=MojaTabela.insert(Obj.Var,glb.getGlobalDeclImeProm().getVarName(), new Struct(Struct.Array, currType));
				report_info("Deklarisana promenljiva "+ glb.getGlobalDeclImeProm().getVarName(), glb);
				//pom.setFpPos(1);
				da_li_je_niz=false;
			}else if(glb.getZagrade() instanceof Zagrade2) {
				MojaTabela.insert(Obj.Var,glb.getGlobalDeclImeProm().getVarName(), currType);
				report_info("Deklarisana promenljiva "+ glb.getGlobalDeclImeProm().getVarName(), glb);
			}
		}else {
			report_error("Greska, zeljeno ime vec postoji u tabeli simbola", glb);
		}
		
	}
	
	public void visit(GlobalType glbType) {
		currType=glbType.getType().struct;
	}
	
	public void visit(SamoJedno samoJedno) {
		currType=MojaTabela.noType;
	}

	
	
	 public void visit(Type type){
	    	Obj typeNode = MojaTabela.find(type.getTypeName());
	    	
	    	if(typeNode == MojaTabela.noObj){
	    		report_error("Nije pronadjen tip " + type.getTypeName() + " u MojaTabelaeli simbola! ", null);
	    		type.struct = MojaTabela.noType;
	    		ne_dodaj=true;
	    	}else{
	    		if(Obj.Type == typeNode.getKind()){
	    			type.struct = typeNode.getType();
	    			ne_dodaj=false;
	    		}else{
	    			report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
	    			type.struct = MojaTabela.noType;
	    			ne_dodaj=true;
	    		}
	    	}
	 }
	 
	 public void visit(PovrTip1 povrTip) {
		 currType=MojaTabela.noType;
		 povrTip.struct=MojaTabela.noType;
	 }
	 
	 public void visit(MethodTypeName methodTypeName){
		//report_info(methodTypeName.getPovratniTip().toString(), methodTypeName);
		/*if(currType==MojaTabela.noType && !(methodTypeName.getPovratniTip() instanceof PovrTip1)) {
			return;
		}*/
		 if(ne_dodaj==true) {
			 return;
		 }
		in_meth=true;
	    currentMethod = MojaTabela.insert(Obj.Meth, methodTypeName.getMethName(), methodTypeName.getPovratniTip().struct);
	    methodTypeName.obj = currentMethod;
	    currTypeMeth=methodTypeName.getPovratniTip().struct;
	    /*if(currTypeMeth!=null) {
	    	report_info("USPEH!",methodTypeName);
	    }*/
	    MojaTabela.openScope();
		report_info("Obradjuje se funkcija " + methodTypeName.getMethName(), methodTypeName);
		ne_dodaj=false;
	 }
	 
	 public void visit(MethodDecl methodDecl){
		 	if(currentMethod==null) {
		 		return;
		 	}
	    	if(!returnFound && currentMethod.getType() != MojaTabela.noType){
				report_error("Semanticka greska na liniji " + methodDecl.getLine() + ": funkcija " + currentMethod.getName() + " nema return iskaz!", null);
	    	}
	    	if(u_klasi) {
		    	metodaPoljaJedno.add(currentMethod);
		    	report_info("AHHAHAHA"+metodaPoljaJedno.size(), methodDecl);
		    	report_info(metodaPoljaJedno.get(0).getName()+"kednooo", methodDecl);
		    }
	    	currentMethod.setLevel(broj_formalnih_param);
	    	samoMetodeSve.add(currentMethod);
	    	paramSveMetode.add(paramJednaMetoda);
	    	paramJednaMetoda=new ArrayList<>();
	    	MojaTabela.chainLocalSymbols(currentMethod);
	    	MojaTabela.closeScope();
	    	
	    	returnFound = false;
	    	currentMethod = null;
	    	currTypeMeth=null;
	    	//currTypeMeth=null;
	    	broj_formalnih_param=0;
	    	u_metodi=false;
	    	in_meth=false;
	    	
	  }
	 
	 public void visit(KrajMetode kraj) {
		 //currentMethod = null;
	 }
	 
	 public void visit(PovrTip2 povr) {
		 povr.struct=povr.getType().struct;
	 }
	 
	
	public void visit(Term1 term1) {
		if(term1.getFactor().struct!=MojaTabela.intType || term1.getMultiFac().struct!=MojaTabela.intType) {
			report_error("Cinioci moraju biti tipa int da bi se mogli mnoziti", term1);
			term1.struct=MojaTabela.noType;
		}else {
			term1.struct=term1.getFactor().struct;
		}
	}
	
	public void visit(Term2 term2) {
		term2.struct=term2.getFactor().struct;
		//report_info("dodelio sam u term2" + term2.struct.getKind(), term2);
	}
	
	public void visit(Expr1 expr1) {
		expr1.struct=expr1.getTerm().struct;
		report_info("dodelio sam expr" + expr1.struct.getKind(), expr1);
		if(expr1.getTerm().struct!=MojaTabela.intType) {
			report_error("Term simbol mora biti tipa int", expr1);
			expr1.struct=MojaTabela.noType;
		}
		
	}
	
	public void visit(CondFact2 cond) {
		if(cond.getExpr().struct!=MojaTabela.boolType) {
			report_error("Greska, tip neterminala expr mora biti bool1", cond);
			cond.struct=MojaTabela.noType;
		}else {
			cond.struct=cond.getExpr().struct;
		}
	}
	
	
	public void visit(AddT1 addT1) {
		addT1.struct=addT1.getTerm().struct;
		if(addT1.getTerm().struct!=MojaTabela.intType) {
			report_error("Term simbol mora biti tipa int", addT1);
			addT1.struct=MojaTabela.noType;
		}
		
	}
	
	public void visit(NoAddTerm noadd) {
		noadd.struct=Tab.noType;
	}
	
	public void visit(MultiF1 multi) {
		multi.struct=multi.getFactor().struct;
	}
	
	public void visit(MultiF2 multi) {
		multi.struct=multi.getFactor().struct;
	}
	
	
	public void visit(ConstType constType) {
		if(constType.getType().struct!=MojaTabela.intType && constType.getType().struct!=MojaTabela.charType && constType.getType().struct!=MojaTabela.boolType) {
			//kako da proverim i za bool tip
			report_error("Greska, konstanta mora biti tipa int, char ili bool!", constType);
			currType=Tab.noType;
		}
		currType=constType.getType().struct;
	}
	
	public void visit(DodatnoCD2 dodatno2) {
		
	}
	
	public void visit(DodatnoCD1 dodatno1) {
		
	}
	
	public void visit(ConstDecl co) {
		currType=null;
	}
	
	public void visit(ConstIzraz constIzraz) {
		if(!da_li_vec_postoji_simbol(constIzraz.getVarName())) {
			if(currType==constIzraz.getTipConst().struct) {
				constIzraz.obj=MojaTabela.insert(Obj.Con, constIzraz.getVarName(), constIzraz.getTipConst().struct);
				report_info("Dodat je const simbol sa imenom" + constIzraz.getVarName(), constIzraz);
				//proveri jel treba nesto da se postavlja za obj
				if(constIzraz.obj.getType()==MojaTabela.boolType) {
					TipConst3 pom=(TipConst3)constIzraz.getTipConst();
					if(pom.getB1().equals("true")) {
						constIzraz.obj.setAdr(1);
					}else {
						constIzraz.obj.setAdr(0);
					}
				}else if(constIzraz.obj.getType()==MojaTabela.intType) {
					TipConst1 pom=(TipConst1)constIzraz.getTipConst();
					constIzraz.obj.setAdr(pom.getN1());
				}else if(constIzraz.obj.getType()==MojaTabela.charType) {
					TipConst2 pom=(TipConst2)constIzraz.getTipConst();
					constIzraz.obj.setAdr(pom.getC1().charAt(1));
				}
			}else {
				report_error("Greska, ne poklapaju se tipovi pre i posle znaka jednakosti", constIzraz);
			}
		}else {
			report_error("Greska, zeljeni simbol se vec nalazi u MojaTabelaeli simbola", constIzraz);
		}
		
		//currType=null;
		
	}
	
	public void visit(Fac10 fac10) {
		if(fac10.getType().struct.getElemType()==new Struct(Struct.Class)) {
			report_error("Neterminal Type mora oznacavati klasu", fac10);
			fac10.struct=MojaTabela.noType;
		}else {
			report_info("Jeste klasa" + fac10.getType().getTypeName().toString(), fac10);
			fac10.struct=fac10.getType().struct;
		}
	}
	
	public void visit(Fac5 fac5) {
		if(fac5.getExpr().struct!=MojaTabela.intType) {
			report_error("Greska, neterminal Expr mora biti tipa int", fac5);
		}else {
			fac5.struct=new Struct(Struct.Array, fac5.getType().struct);
		}
	}
	
	public void visit(Fac8 fac8) {
		//report_info("POZVANO JE STAT8", fac8);
		//if(fac8.getDesignator().getDesignatorName().getDesName().equals("ord"))return;
		if(fac8.getDesignator().obj.getKind()!=Obj.Meth) {
			report_error("Greska, neterminal Designator mora biti metod", fac8);
			fac8.struct=MojaTabela.noType;
		}else {
			/*if(fac8.getDesignator().getDesignatorName().getDesName().equals("ord")) {
				fac8.struct=fac8.getDesignator().obj.getType();
				return;
			}*/
			
			for(int i=0;i<samoMetodeSve.size();i++) {
				//report_info("GLEDAM STA JE SA IMENOM FUNKCIJE" + fac8.getDesignator().getDesignatorName().getDesName() + " , a ime metode" + samoMetodeSve.get(i).getName(), fac8);
				if(samoMetodeSve.get(i).getName().equals(fac8.getDesignator().getDesignatorName().getDesName())) {
					if(paramSveMetode.get(i).size()!=prosledjeniParam.size()) {
						//report_info("param metode" + paramSveMetode.get(i).size() + ", a param prosledjeno " + prosledjeniParam.size(), fac8);
						continue;
					}
					//report_info("USLO JE U IF KOLIKO PUTA", fac8);
					int ispis=0;
					for(int j=0;j<paramSveMetode.get(i).size();j++) {
						if(paramSveMetode.get(i).get(j).getType()==prosledjeniParam.get(j)) {
							ispis=1;
						}else {
							ispis=0;
						}
					}
					if(ispis==1) {
						report_info("Parametri su ispravni za metodu " + fac8.getDesignator().getDesignatorName().getDesName(), fac8);
						fac8.struct=fac8.getDesignator().obj.getType();
						prosledjeniParam=new ArrayList<>();
						
					}else {
						report_info("Parametri nisu ispravni za metodu " + fac8.getDesignator().getDesignatorName().getDesName(), fac8);
						fac8.struct=MojaTabela.noType;
						prosledjeniParam=new ArrayList<>();
					}
				}
			}
			
		}
		//ovde fali da se provere parametri
	}
	
	public void visit(Fac6 fac6) {
		if(fac6.getDesignator().obj.getKind()!=Obj.Meth) {
			report_error("Greska, neterminal Designator mora biti metod", fac6);
			fac6.struct=MojaTabela.noType;
		}else {
			fac6.struct=fac6.getDesignator().obj.getType();
		}
	}
	
	public boolean da_li_tipovi_kompatibilni(Struct tip1, Struct tip2) {
		if(tip1.getKind()==tip2.getKind()) {
			return true;
		}
		if(tip1==MojaTabela.noType && (tip2.getKind()==Struct.Class || tip2.getKind()==Struct.Array)) {
			return true;
		}
		if(tip1.getKind()==Struct.Array && tip2.getKind()==Struct.Array) {
			if(tip1.getElemType()==tip2.getElemType()) {
				return true;
			}
		}
		
		return false;
	}
	
	public void visit(VarType varType) {
		currType=varType.getType().struct;
		varType.struct=varType.getType().struct;
	}
	
	public void visit(VarDecl varDecl) {
		ne_dodaj=false;
	}
	
	public void visit(VarNameDecl varName) {
		if(ne_dodaj==true) {
			report_error("NE DODAJ", varName);
			return;
		}
		if(currentMethod==null && in_meth==true) {
			return;
		}
		if(da_li_vec_postoji_simbol(varName.getVarName())) {
			report_error("Greska, zeljeno ime vec postoji u tabeli simbola", varName);
		}else {
			int tip;
			if(u_klasi && u_metodi==false) {
				tip=Obj.Fld;
			}else {
				tip=Obj.Var;
			}
			if(varName.getZagrade() instanceof Zagrade1) {
				varName.obj=MojaTabela.insert(tip,varName.getVarName(), new Struct(Struct.Array, currType));
				//report_info("JESTE NIZ",varName);
				if(u_klasi && u_metodi==false) {
					metodaPoljaJedno.add(varName.obj);
				}
				da_li_je_niz=false;
				currType=null;
			}else if(varName.getZagrade() instanceof Zagrade2) {
				varName.obj=MojaTabela.insert(tip,varName.getVarName(), currType);
				//report_info(MojaTabela.find(varName.getVarName()).getType().getKind()+"ovo je dodatno", varName);
				if(u_klasi && u_metodi==false) {
					metodaPoljaJedno.add(varName.obj);
				}
				//currType=null;
			}
		}
	}
	
	public void visit(ViseVarDecl viseVarDecl) {
		//currType=null;
	}
	
	public void visit(Zagrade1 zagrade1) {
		da_li_je_niz=true;
	}
	
	public boolean da_li_vec_postoji_simbol(String name) {
		if(MojaTabela.currentScope().findSymbol(name)!=null) {
			return true;
		}
		
		
		return false;
	}
	
	public void visit(FormalParamDecl formalParamDecl) {
		if(MojaTabela.find(formalParamDecl.getType().getTypeName())==MojaTabela.noObj) {
			return;
		}
		if(da_li_je_niz) {
			Obj novi_param=MojaTabela.insert(Obj.Var, formalParamDecl.getParamName(), new Struct(Struct.Array, formalParamDecl.getType().struct));
			paramJednaMetoda.add(novi_param);
			da_li_je_niz=false;
		}else {
			Obj novi_param=MojaTabela.insert(Obj.Var, formalParamDecl.getParamName(), formalParamDecl.getType().struct);
			paramJednaMetoda.add(novi_param);
		}
		
		broj_formalnih_param++;
	}
	
	public void visit(TipConst1 tipConst1) {
		tipConst1.struct=MojaTabela.intType;
	}
	
	public void visit(TipConst2 tipConst2) {
		tipConst2.struct=MojaTabela.charType;
	}

	public void visit(TipConst3 tipConst3) {
		//ovde treba da bool
		tipConst3.struct=MojaTabela.boolType;
	}
	
	public void visit(DesignatorStatement1 desStat1) {
		if(desStat1.getDesignator().obj.getKind()!=Obj.Var && desStat1.getDesignator().obj.getKind()!=Obj.Elem && desStat1.getDesignator().obj.getKind()!=Obj.Fld) {
			report_error("Greska, neterminal Designator mora biti ili promenljiva ili element niza ili polje unutar objekta", desStat1);
		}else {
			//report_info(desStat1.getDesignator().obj.getName()+"     imeee     "+desStat1.getDesignator().obj.getType().getKind(), desStat1);
			if(!desStat1.getDesignator().obj.getType().compatibleWith(desStat1.getExpr().struct)) {
				//report_info("designator"+desStat1.getDesignator().obj.getType().getKind(), desStat1);
				//report_info("designator je      |0" + desStat1.getDesignator().obj.getName(), desStat1);
				//report_info("expr"+desStat1.getExpr().struct.getKind(), desStat1);
				report_error("Greska, neterminali Designator i Expr moraju biti kompatibilni!", desStat1);
			}else {
				report_info("TIPOVI SU KOMPATIBILNI!", desStat1);
			}
		}
	}
	
	public void visit(DesignatorStatement4 desStat4) {
		
		if(desStat4.getDesignator().obj.getKind()!=Obj.Var && desStat4.getDesignator().obj.getKind()!=Obj.Elem && desStat4.getDesignator().obj.getKind()!=Obj.Fld) {
			report_error("Greska, neterminal Designator mora biti ili promenljiva ili element niza ili polje unutar objekta", desStat4);
		}else {
			if(desStat4.getDesignator().obj.getType()!=MojaTabela.intType) {
				report_error("Greska, neterminal Designator mora biti tipa int!", desStat4);
			}
		}
	}
	
	public void visit(DesignatorStatement5 desStat5) {
		if(desStat5.getDesignator().obj.getKind()!=Obj.Var && desStat5.getDesignator().obj.getKind()!=Obj.Elem && desStat5.getDesignator().obj.getKind()!=Obj.Fld) {
			report_error("Greska, neterminal Designator mora biti ili promenljiva ili element niza ili polje unutar objekta", desStat5);
		}else {
			if(desStat5.getDesignator().obj.getType()!=MojaTabela.intType) {
				report_error("Greska, neterminal Designator mora biti tipa int!", desStat5);
			}
		}
	}
	
	public void visit(DesignatorStatement2 desStat2) {
		//kako ovo
		if(desStat2.getDesignator().obj.getKind()==Obj.Meth) {
			//sad treba da proverim da li valjaju parametri
			
			for(int i=0;i<samoMetodeSve.size();i++) {
				if(samoMetodeSve.get(i).getName().equals(desStat2.getDesignator().getDesignatorName().getDesName())) {
					if(paramSveMetode.get(i).size()!=prosledjeniParam.size()) {
						continue;
					}
					int ispis=0;
					for(int j=0;j<paramSveMetode.get(i).size();j++) {
						if(paramSveMetode.get(i).get(j).getType()==prosledjeniParam.get(j)) {
							ispis=1;
						}else {
							ispis=0;
						}
					}
					if(ispis==1) {
						report_info("Valjaaaa", desStat2);
						prosledjeniParam=new ArrayList<>();
						
					}else {
						report_error("Nemas pojma", desStat2);
						prosledjeniParam=new ArrayList<>();
					}
				}
			}
		}else {
			report_error("Greska, ne postoji metoda sa zeljenim nazivom!", desStat2);
		}
	}
	
	public void visit(AP1 ap1) {
		prosledjeniParam.add(ap1.getExpr().struct);
	}
	
	public void visit(AP2 ap2) {
		prosledjeniParam.add(ap2.getExpr().struct);
	}
	
	public boolean da_li_valjaju_parametri() {
		return true;
	}
	
	public void visit(DesignatorStatement3 desStat3) {
		//ovo isto kao prethodno, samo nema actpars
	}
	
	
	public void visit(SStat4 sstat4) {
		//ovo je za break
		if(do_while_petlja) {
			
		}else {
			report_error("Greska, naredba break se mora naci u do while petlji", sstat4);
		}
		//do_while_petlja=false;
	}
	
	public void visit(StatDO statDo) {
		do_while_petlja=true;
	}
	
	public void visit(KrajWhile kraj) {
		do_while_petlja=false;
	}
	
	public void visit(SStat3 sstat3) {
		
	}
	
	public void visit(SStat5 sstat5) {
		//ovo je za continue
		if(do_while_petlja) {
			
		}else {
			report_error("Greska, naredba continue se mora naci u do while petlji", sstat5);
		}
	}
	
	public void visit(SStat8 sstat8) {
		if(sstat8.getDesignator().obj.getKind()!=Obj.Var && sstat8.getDesignator().obj.getKind()!=Obj.Elem && sstat8.getDesignator().obj.getKind()!=Obj.Fld) {
			report_error("Greska, neterminal Designator mora biti ili promenljiva ili element niza ili polje unutar objekta", sstat8);
		}else {
			if(sstat8.getDesignator().obj.getType()!=MojaTabela.intType && sstat8.getDesignator().obj.getType()!=MojaTabela.charType && sstat8.getDesignator().obj.getType()!=MojaTabela.boolType) {
				report_error("Greska, neterminal Designator mora biti tipa int, char ili bool!", sstat8);
			}
		}
	}
	
	public void visit(SStat9 sstat9) {
		//ovde fali za bool tip i kod sstat8
		if(sstat9.getExpr().struct!=MojaTabela.intType && sstat9.getExpr().struct!=MojaTabela.charType && sstat9.getExpr().struct!=MojaTabela.boolType) {
			report_error("Greska, neterminal Designator mora biti tipa int, char ili bool!", sstat9);
		}
	}
	
	public void visit(SStat7 sstat7) {
		if(currentMethod==null) {
			return;
		}
		returnFound = true;
    	Struct currMethType = currentMethod.getType();
    	if(!currMethType.compatibleWith(sstat7.getExpr().struct)){
			report_error("Greska na liniji " + sstat7.getLine() + " : " + "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), null);
    	}
	}
	
	public void visit(Fac7 fac7) {
		report_info(fac7.getDesignator().getDesignatorName().getDesName(), fac7);
		fac7.struct=fac7.getDesignator().obj.getType();
		//valjda je to to
	}
	
	
	
	public void visit(SStat6 sstat6) {
		returnFound = true;
    	
	}
	
	public void visit(Cond2 cond2) {
		//treba za oba da proverim da li su bool
		cond2.struct=cond2.getCondTerm().struct;
	}
	
	public void visit(Cond1 cond1) {
		//i ovde treba da proverim da li je condterm bool
		cond1.struct=cond1.getCondTerm().struct;
	}
	
	public void visit(CondTerm1 cond) {
		cond.struct=cond.getCondFact().struct;
	}
	
	public void visit(CondTerm2 cond) {
		cond.struct=cond.getCondTerm().struct;
	}
	
	public void visit(CondFact1 condFact1) {
		if(condFact1.getExpr().struct.compatibleWith(condFact1.getExpr1().struct)) {
			if(condFact1.getExpr().struct.getKind()==Struct.Array || condFact1.getExpr().struct.getKind()==Struct.Class) {
				if(condFact1.getRelop() instanceof Relop1 || condFact1.getRelop() instanceof Relop2) {
					condFact1.struct=MojaTabela.boolType;
				}else {
					report_error("Greska, operator moze biti samo jednako jednako ili razlicito!", condFact1);
					//condFact1.struct=MojaTabela.noObj;
				}
			}
		}else {
			report_error("Greska, tipovi nisu kompatibilni!", condFact1);
		}
	}
	
	
	
	public void visit(Fac1 fac1) {
		fac1.struct=fac1.getTipConst().struct;
	}
	
	public void visit(Fac3 fac3) {
		fac3.struct=fac3.getExpr().struct;
	}
	
	public void visit(DesignatorName desName) {
		if(MojaTabela.find(desName.getDesName())==MojaTabela.noObj) {
			report_error("Zeljeno ime "+ desName.getDesName()+ " ne postoji u tabeli simbola!", desName);
			desName.obj=MojaTabela.noObj;
			currDesignator=null;
			return;
		}
		desName.obj=MojaTabela.find(desName.getDesName());
		//report_info("TIP DESIGNATOR" + desName.obj.getType().getKind(), desName);
		//currDesignator=desName.obj;
		if(desName.obj.getType().getKind()==Struct.Array) {
			//report_info("USLO I U NIZ", desName);
			currDesignatorNiz=desName.obj;
			//novo
			currDesigNiz.add(currDesignatorNiz);
		}
		if(desName.obj.getType().getKind()==Struct.Class) {
			currDesignatorRekord=desName.obj;
			//report_info("USLO I U KLASU", desName);
		}
		
		//report_info("currD postaje " + currDesignator.getName() +" i njegov tip je " + currDesignator.getType().getKind(), desName);
	}
	
	public void visit(Designator des) {
		if(des.getDesigDodatno() instanceof NoDesDodatno) {
			des.obj=des.getDesignatorName().obj;
			if(des.obj.getKind()==Obj.Con) {
				report_info("Pristup konstanti " + des.getDesignatorName().getDesName(), des);
			}else if(des.obj.getKind()==Obj.Var) {
				report_info("Pristup promenljivoj " + des.getDesignatorName().getDesName(), des);
			}
		}else {
			des.obj=des.getDesigDodatno().obj;
		}
		
		
	}
	
	public void visit(DesDodatno des) {
		des.obj=des.getDDodatno().obj;
	}
	
	public void visit(Expr2 expr2) {
		expr2.struct=expr2.getTerm().struct;
		//report_info("dodelio sam expr" + expr2.struct.getKind(), expr2);
	}
	
	public void visit(DDodatno1 dodatno) {
		if(currDesignatorRekord==null) {
			report_error("Greska, neterminal designator mora biti unutrasnja klasa! jel ovde", dodatno);
			dodatno.obj=MojaTabela.noObj;
			return;
		}
		if(currDesignatorRekord.getKind()==Obj.Type) {
			dodatno.obj=MojaTabela.noObj;
			report_error("Greska, mora biti prvo napravljen objekat klase!", dodatno);
			return;
		}
		
		if(currDesignatorRekord.getType().getMembersTable().searchKey(dodatno.getDodatnoIme())!=null) {
			report_info("Pristup polju klase " + currDesignatorRekord.getName(), dodatno);
			dodatno.obj=currDesignatorRekord.getType().getMembersTable().searchKey(dodatno.getDodatnoIme());
			currDesignatorRekord=null;
			//report_info("SADA GLEDAM KOJI TIP DOBIJAM ZA NIZ REKORDA!" + dodatno.obj.getType().getKind(), dodatno);
			if(dodatno.obj.getType().getKind()==Struct.Array) {
				currDesignatorNiz=dodatno.obj;
				//novo
				currDesigNiz.add(currDesignatorNiz);
			}
		}else {
			report_error("Greska, nije nadjeno polje klase!", dodatno);
			dodatno.obj=MojaTabela.noObj;
		}
			
		
	}
	
	public void visit(DDodatno2 dod) {
		//report_info("SADA JE DES STAT 2", dod);
		//SyntaxNode pom=dod.getParent();
		SyntaxNode pom=dod.getParent().getParent();
		
		if(dod.getExpr().struct!=MojaTabela.intType) {
			report_error("Greska, neterminal Expr mora biti tipa int!", dod);
			dod.obj=MojaTabela.noObj;
		}else {
			//report_info("currD za obradjivanje je " + currDesigNiz.peek().getName(), dod);
			//novo
			currDesignatorNiz=currDesigNiz.pop();
			if(currDesignatorNiz!=null) {
				//report_info("PROVERAVAM ZA CUR NIZ  " + currDesignatorNiz.getName() , dod);
				dod.obj=new Obj(Obj.Elem, currDesignatorNiz.getName(), currDesignatorNiz.getType().getElemType());
				//report_info("ovde za currD je" + currDesignatorNiz.getType().getKind() + currDesignatorNiz.getKind(), dod);
				//report_info("TIP ZA ELEM NIZA" + currDesignatorNiz.getType().getElemType().getKind(), dod);
				//ovo je za modifikaciju final
				/*if(currDesignatorNiz.getFpPos()==1) {
					dod.obj.setFpPos(1);
				}*/
				currDesignatorNiz=null;
				
				if(dod.obj.getType().getKind()==Struct.Class) {
					currDesignatorRekord=dod.obj;
				}
			}else {
				dod.obj=Tab.noObj;
				report_error("Greska, nije tip niz!", dod);
			}
			//niz[niz[a]]
		}
	}
	
	
	
	//ovde ide za klasu
	public void visit(ClassName className) {
		currClass=new Obj(Obj.Type, className.getClassName(),new Struct(Struct.Class));
		//currClass=MojaTabela.insert(Obj.Type, className.getClassName(),new Struct(Struct.Class));
		//report_info("Dodata klasa", className);
		className.obj=currClass;
		dodajNakonExtends=className;
		//Tab.insert(Obj.Fld, "TVF", className.obj.getType());
		//u_klasi=true;
		//imenaKlasa.add(currClass);
		//report_info(imenaKlasa.size()+"imena klasa", className);
		
		//MojaTabela.openScope();
	}
	
	public void visit(ClassDecl classDecl) {
		if(MojaTabela.find(currClass.getName())==MojaTabela.noObj) {
			return;
		}
		//currClass.getType().setMembers(Tab.currentScope().getLocals());
		MojaTabela.chainLocalSymbols(currClass.getType());
		MojaTabela.closeScope();
		report_info(metodaPoljaJedno.size()+"dodajem jedno u sve", classDecl);
		for(int i=0;i<imenaKlasa.size();i++) {
			if(imenaKlasa.get(i).getName().equals(izvedenoIzIme)) {
				for(int j=0;j<metodaPoljaSve.get(i).size();j++) {
					if(!metodaPoljaJedno.contains(metodaPoljaSve.get(i).get(j))) {
						metodaPoljaJedno.add(metodaPoljaSve.get(i).get(j));
					}
				}
			}
		}
		metodaPoljaSve.add(metodaPoljaJedno);
		//report_info(metodaPoljaSve.get(0).size()+"pre clean", classDecl);
		//metodaPoljaJedno.clear();
		metodaPoljaJedno=new ArrayList<>();
		//report_info(metodaPoljaSve.get(0).size()+"nakon clean", classDecl);
		//metodaPoljaJedno=new ArrayList<>();
		u_klasi=false;
		currClass=null;
		izvedeno_iz=null;
		izvedenoIzIme="?";
		if(u_klasi==false) {
			//report_info("Izaslo iz klase", classDecl);
		}
		
		
	}
	
	public void visit(Izvodjenje1 izv) {
		izvedeno_iz=izv.getType().struct;
		izvedenoIzIme=izv.getType().getTypeName();
		//report_info("izvedeno", izv);
		//report_info(izv.getType().getTypeName(), izv);
		if(izvedeno_iz.getKind()!=Struct.Class || imenaRecord.contains(izvedenoIzIme)) {
			report_error("Greska, iza extends mora stajati naziv klase!", izv);
			izvedeno_iz=MojaTabela.noType;
			izvedenoIzIme="?";
			dodajNakonExtends=null;
			return;
		}
		currClass=MojaTabela.insert(Obj.Type, dodajNakonExtends.getClassName(),new Struct(Struct.Class));
		MojaTabela.openScope();
		u_klasi=true;
		imenaKlasa.add(currClass);
		dodajNakonExtends=null;
	}
	
	public void visit(NoIzvodjenje izv) {
		currClass=MojaTabela.insert(Obj.Type, dodajNakonExtends.getClassName(),new Struct(Struct.Class));
		MojaTabela.openScope();
		u_klasi=true;
		imenaKlasa.add(currClass);
		izvedeno_iz=MojaTabela.noType;
		izvedenoIzIme="?";
		dodajNakonExtends=null;
		
		
	}
	
	//RECORD
	
	public void visit(RecordName recordName) {
		currClass=MojaTabela.insert(Obj.Type, recordName.getClassName(),new Struct(Struct.Class));
		//report_info("Dodata klasa", recordName);
		recordName.obj=currClass;
		//Tab.insert(Obj.Fld, "TVF", className.obj.getType());
		imenaKlasa.add(recordName.obj);
		//report_info(imenaKlasa.size()+"imena klasa", className);
		u_klasi=true;
		imenaRecord.add(recordName.getClassName());
		MojaTabela.openScope();
	}
	
	public void visit(RecordDecl recordDecl) {
		currClass.getType().setMembers(MojaTabela.currentScope().getLocals());
		MojaTabela.chainLocalSymbols(currClass.getType());
		MojaTabela.closeScope();
		metodaPoljaSve.add(metodaPoljaJedno);
		metodaPoljaJedno=new ArrayList<>();
		u_klasi=false;
		currClass=null;	
	}
	
	//nesto pomocno
	
	public void visit(UMetodi met) {
		u_metodi=true;
	}
	
	

    public boolean passed(){
    	return !errorDetected;
    }
    
    public void visit(Statement1 stat) {
    	if(!listaLabela.contains(stat.getZaLabelu().getLabel().getName())) {
    		listaLabela.add(stat.getZaLabelu().getLabel().getName());
    	}else {
    		report_error("Greska, zeljeno ime labele je vec deklarisano!", stat);
    	}
    }
    
    
    public void visit(SStat10 stat) {
    	if(listaLabela.contains(stat.getLabel().getName())) {
    		
    	}else {
    		//report_error("Greska, ne postoji zeljena labela!", stat);
    	}
    }
	public void visit(ZaFin1 zaf) {
		zafinal=true;
	}
}
