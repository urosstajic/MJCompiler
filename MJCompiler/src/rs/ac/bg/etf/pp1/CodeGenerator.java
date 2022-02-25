package rs.ac.bg.etf.pp1;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import rs.ac.bg.etf.pp1.CounterVisitor.*;
import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor{

	
	private int mainPc;
	
	private Obj prog;
	private Obj programcic=null;
	private Obj currDesigNiz=null;
	private Obj currDesig=null;
	private Obj currDesigElem=null;
	int pom=0;
	Obj adrNiza=null;
	int opetPomoc;
	
	Stack<Integer> ifStack=new Stack<Integer>();
	Stack<Integer> elseStack=new Stack<Integer>();
	
	Stack<Integer> pocetakDoWhile=new Stack<Integer>();
	Stack<Integer> zakrpiDoWhile=new Stack<Integer>();
	
	Stack<Integer> breakStack=new Stack<Integer>();
	Stack<Integer> continueStack=new Stack<Integer>();
	
	Stack<Integer> zaOr=new Stack<Integer>();
	Stack<Integer> zaAnd=new Stack<Integer>();
	
	//proba
	int adr, adr2, top, adr3, zaB, zaC;
	int zaprvi;
	private boolean zafinal=false;
	
	private List<String> listaLabela=new ArrayList<>();
	private List<Integer> offsetLabela = new ArrayList<>();
	private List<String> listaNenadjenihLabela=new ArrayList<>();
	private List<Integer> offsetNenadjeneLabela = new ArrayList<>();
	
	

	
	public int getMainPc(){
		return mainPc;
	}
	
	
	private void pocetak() {
		Obj pom=MojaTabela.lenObj;
		pom.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(pom.getLevel());
		Code.put(pom.getLocalSymbols().size());
		Code.put(Code.load_n);
		Code.put(Code.arraylength);
		Code.put(Code.exit);
		Code.put(Code.return_);
		
		Obj obj = MojaTabela.chrObj;
		obj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(obj.getLevel());
		Code.put(obj.getLocalSymbols().size());
		Code.put(Code.load_n);
		Code.put(Code.exit);
		Code.put(Code.return_);
		
		Obj pom2 = MojaTabela.ordObj;
		pom2.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(pom2.getLevel());
		Code.put(pom2.getLocalSymbols().size());
		Code.put(Code.load_n);
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	
	public void visit(TipConst1 cnst){
		Obj con = MojaTabela.insert(Obj.Con, "$", cnst.struct);
		con.setLevel(0);
		con.setAdr(cnst.getN1());
		//ovo je dodato za modif niz[1,2]
		//zaprvi=cnst.getN1();
		Code.load(con);
	}
	
	public void visit(TipConst2 cnst){
		Obj con = MojaTabela.insert(Obj.Con, "$", cnst.struct);
		con.setLevel(0);
		con.setAdr(cnst.getC1().charAt(1));
		
		Code.load(con);
	}
	
	public void visit(TipConst3 cnst){
		Obj con = MojaTabela.insert(Obj.Con, "$", cnst.struct);
		con.setLevel(0);
		if(cnst.getB1().equals("true")) {
			con.setAdr(1);
		}else {
			con.setAdr(0);
		}
		
		Code.load(con);
	}
	
	public void visit(ProgName progN) {
		prog=MojaTabela.find(progN.getProgName());
		pocetak();
	}
	
	public void visit(Program prog) {
		programcic=prog.obj;
		int pom=0;
	}
	
	public void visit(MethodDecl meth) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(MethodTypeName meth) {
		if("main".equalsIgnoreCase(meth.getMethName())){
			mainPc = Code.pc;
		}
		meth.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		SyntaxNode methodNode = meth.getParent();
	
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
	}
	
	//ovo je read
	public void visit(SStat8 sstat8) {
		if(sstat8.getDesignator().obj.getType()==MojaTabela.charType) {
			Code.put(Code.bread);
		}else {
			Code.put(Code.read);
		}
		Code.store(sstat8.getDesignator().obj);
	}
	
	
	//ovo je print
	public void visit(SStat9 sstat9) {
		//ovo je dodato za modifikaciju da kad se kaze print(niz) da se ispisu svi elem niza, ili ono print(niz,3) da se ispise niz[3], u cup nista nije menjano
		/*if(sstat9.getExpr().struct.getKind()==Struct.Array) {
			if(sstat9.getSStatmen1() instanceof YesSStatmen2) {
				Code.loadConst(((YesSStatmen2)sstat9.getSStatmen1()).getN1());
				Code.put(Code.3);
				Code.loadConst(5);
				Code.put(Code.print);
				//Code.loadConst(0);
			}else {
				//ovde mi se prvo nalazila adresa niza, i onda posle toga idem redom i ispisujem elemente niza
				int pomocko;
				Code.put(Code.dup);
				Code.loadConst(0);
				pomocko=Code.pc;
				Code.put(Code.dup2);//ovim bude adr niza, 0, adrniza 0
				
				Code.put(Code.aload);
				Code.loadConst(5);
				Code.put(Code.print);
				//ispisan prvi elem niza
				Code.loadConst(1);
				Code.put(Code.add);//ovde sad adr niza i 1
				//sad da vidim jel treba da izadjem iz obilaska niza
				Code.put(Code.dup_x1);//1 adr niza 1
				Code.put(Code.pop);//1 adr niza
				Code.put(Code.dup);// 1 adr niza adr niza
				Code.put(Code.arraylength); //1 adr niza vel niza
				Code.put(Code.dup_x2);//vel 1 adr niza vel niza
				Code.put(Code.pop);
				Code.put(Code.dup_x2);
				Code.put(Code.pop);//ovde bude adr niza vel 1
				Code.put(Code.dup2);//ovo zbog provere sa false jump
				Code.putFalseJump(Code.ne, 0);
				int zaFixup=Code.pc-2;
				//ovde nisu jednaki jos
				Code.put(Code.dup_x1);
				Code.put(Code.pop);
				Code.put(Code.pop);
				Code.putJump(pomocko);
				
				
				
				//ovde su jednaki i ovo neka bude kraj
				Code.fixup(zaFixup);
				Code.put(Code.pop);
				Code.put(Code.pop);
			
			}
			
			return;
		}*/
		//ovo je ono sa nizom bool i onda da ispise binarno u dec broj
		//  && sstat9.getExpr().struct.getKind()!=Struct.Array ovo je dodatno za 3.fazu u sstat8
//		if(sstat9.getExpr().struct.getKind()==Struct.Array) {
//			//ovde kad dodjem imam adresu niza
//			Code.put(Code.arraylength);
//			Code.loadConst(1);
//			Code.put(Code.sub);
//			Code.load(currDesigNiz);
//			Code.put(Code.dup_x1);
//			Code.put(Code.pop);
//			Code.put(Code.aload);//ovde ucitan posl elem niza
//			Code.load(currDesigNiz);
//			Code.put(Code.arraylength);
//			Code.loadConst(1);
//			Code.put(Code.sub);
//			Code.loadConst(1);
//			Code.put(Code.sub);//zbir,koji elem niza
//			
//			Code.put(Code.dup);
//			Code.load(currDesigNiz);
//			Code.put(Code.dup_x1);
//			Code.put(Code.pop);
//			Code.put(Code.aload);//zbir , koji elem niza, niz[koji elem niza]
//			int adrzaskok=Code.pc;
//			Code.put(Code.dup2);
//			Code.put(Code.pop);
//			Code.load(currDesigNiz);
//			Code.put(Code.arraylength);
//			Code.loadConst(1);
//			Code.put(Code.sub);//zbir , koji elem niza, niz[koji elem niza], koji elem niza, duzina niza-1 92 linija
//			Code.put(Code.dup_x1);
//			Code.put(Code.pop);
//			//Code.put(Code.dup_x2);
//			//Code.put(Code.pop);//zbir niz[koji elem niza], koji elem niza, duzina niza-1
//			Code.put(Code.sub);//nakon ovoga zbir, koji elem niza, niz[koji elem niza], koliko za 2
//			//ovde sad treba da vidim koliko za 2
//			Code.loadConst(2);
//			int ovdeskacem=Code.pc;
//			Code.put(Code.dup_x1);
//			Code.put(Code.pop);
//			Code.loadConst(1);
//			Code.put(Code.sub);
//			Code.put(Code.dup);
//			Code.loadConst(0);
//			Code.putFalseJump(Code.ne, 0);
//			int fixadr=Code.pc-2;
//			Code.put(Code.dup_x1);
//			Code.put(Code.pop);
//			Code.loadConst(2);
//			Code.put(Code.mul);
//			Code.putJump(ovdeskacem);
//			
//			
//		
//			
//			
//			//ovde fix za petlju za 2
//			Code.fixup(fixadr);
//			Code.put(Code.pop);
//			//sad na steku zbir, koji elem niza,niz[koji elem niza],  2
//			Code.put(Code.mul);
//			Code.put(Code.dup_x1);
//			Code.put(Code.pop);
//			Code.put(Code.dup_x2);
//			Code.put(Code.pop);
//			Code.put(Code.add);
//			Code.put(Code.dup_x1);
//			Code.put(Code.pop);
//			
//			Code.loadConst(1);
//			Code.put(Code.sub);
//			Code.put(Code.dup);
//			Code.loadConst(-1);
//			Code.putFalseJump(Code.ne, 0);
//			int drugifix=Code.pc-2;
//			Code.load(currDesigNiz);
//			Code.put(Code.dup2);
//			Code.put(Code.pop);
//			Code.put(Code.aload);
//			Code.putJump(adrzaskok);
//			
//			//niz dosao do 0 iskoci
//			Code.fixup(drugifix);
//			Code.put(Code.pop);//ostaje samo zbir
//			Code.loadConst(5);
//			Code.put(Code.print);
//			return;
//		}
		
		
		if(sstat9.getExpr().struct == MojaTabela.intType || sstat9.getExpr().struct == MojaTabela.boolType){
			if(sstat9.getSStatmen1() instanceof YesSStatmen2) {
				Code.loadConst(((YesSStatmen2)sstat9.getSStatmen1()).getN1());
			}else {
				Code.loadConst(0);
			}
			//Code.loadConst(5);
			Code.put(Code.print);
		}else{
			if(sstat9.getSStatmen1() instanceof YesSStatmen2) {
				Code.loadConst(((YesSStatmen2)sstat9.getSStatmen1()).getN1());
			}else {
				Code.loadConst(0);
			}
			//Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}
	
	public void visit(Expr1 expr) {
		Code.put(Code.neg);
	}
	
	
	//plus plus
	public void visit(DesignatorStatement4 des) {
		if(des.getDesignator().obj.getKind()==Obj.Elem) {
			Code.put(Code.dup2);
			Code.load(des.getDesignator().obj);
			Code.put(Code.const_1);
			Code.put(Code.add);
			Code.store(des.getDesignator().obj);
		}else {
			Code.load(des.getDesignator().obj);
			Code.put(Code.const_1);
			Code.put(Code.add);
			Code.store(des.getDesignator().obj);
		}
	}
	
	//minus minus
	public void visit(DesignatorStatement5 des) {
		if(des.getDesignator().obj.getKind()==Obj.Elem) {
			Code.put(Code.dup2);
			Code.load(des.getDesignator().obj);
			Code.put(Code.const_1);
			Code.put(Code.sub);
			Code.store(des.getDesignator().obj);
		}else {
			Code.load(des.getDesignator().obj);
			Code.put(Code.const_1);
			Code.put(Code.sub);
			Code.store(des.getDesignator().obj);
		}
	}
	
	
	//plus i minus
	public void visit(AddT1 addT) {
		if(addT.getAddop() instanceof AddO1) {
			Code.put(Code.add);
		}else if(addT.getAddop() instanceof AddO2) {
			Code.put(Code.sub);
		}
	}
	
	
	//puta, podeljeno..
	public void visit(MultiF1 multi) {
		if(multi.getMulop() instanceof MulO1) {
			Code.put(Code.mul);
		}else if(multi.getMulop() instanceof MulO2) {
			Code.put(Code.div);
		}else if(multi.getMulop() instanceof MulO3) {
			Code.put(Code.rem);
		}
	}
	
	public void visit(MultiF2 multi) {
		if(multi.getMulop() instanceof MulO1) {
			Code.put(Code.mul);
		}else if(multi.getMulop() instanceof MulO2) {
			Code.put(Code.div);
		}else if(multi.getMulop() instanceof MulO3) {
			Code.put(Code.rem);
		}
	}
	
	public void visit(Fac7 fac7) {
		Code.load(fac7.getDesignator().obj);
	}
	
	//za return
	public void visit(SStat7 returnExpr){
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(SStat6 returnNoExpr){
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	int moze=0;
	//doraditi ova dva
	public void visit(Fac5 fac5) {
		//ova dva reda dodata da bude duplo veca duzina zbog onog za pristup elementu niza
		//Code.loadConst(2);
		//Code.put(Code.mul);
		Code.put(Code.newarray);
		if(fac5.getType().struct==MojaTabela.charType) {
			Code.put(0);
		}else {
			Code.put(1);
		}
		
		//modif za da se broji pristup elementima niza
		//ovde da sve stavim na 0 u svaki elem niza na pocetku
		moze=1;
	}
	
	public void visit(Fac10 fac10) {
		String ime=fac10.getType().getTypeName();
		
		Code.put(Code.new_);
		Code.put2(fac10.struct.getNumberOfFields()*4);
		Code.put(Code.dup);
		Code.loadConst(0);
		Code.put(Code.putfield);
	    Code.put2(0);
	}
	
	
	//dodela vrednosti
	public void visit(DesignatorStatement1 des) {
		//ovo je modifikacija za final
		//ovde kad udjem na steku je adr niza, koji elem i vrednost koja da se sacuva
//		if(des.getDesignator().obj.getType().getKind()!=Struct.Array) {
//			if(des.getDesignator().obj.getFpPos()==1) {
//				Obj temp=des.getDesignator().getDesignatorName().obj;
//				Code.put(Code.dup2);//sad imam 2 0 15 0 15
//				Code.put(Code.pop);
//				Code.load(temp);
//				Code.put(Code.arraylength);
//				Code.loadConst(2);
//				Code.put(Code.div);
//				Code.put(Code.add);
//				//2 0 15 5
//				Code.put(Code.dup);//da mi se sacuva index gde treba da upisem 1
//				Code.load(temp);
//				Code.put(Code.dup_x1);
//				Code.put(Code.pop);//2 0 15 5 2 5 
//				Code.put(Code.aload);
//				Code.loadConst(1);
//				Code.putFalseJump(Code.ne, 0);
//				int poms=Code.pc-2;
//				//upisi 1 na odredjeni index
//				Code.load(temp);
//				Code.put(Code.dup_x1);
//				Code.put(Code.pop);
//				Code.loadConst(1);
//				Code.put(Code.astore);
//				Code.store(des.getDesignator().obj);
//				//i skociti na kraj
//				Code.loadConst(1);
//				Code.loadConst(1);
//				Code.putFalseJump(Code.ne, 0);
//				int nakraj=Code.pc-2;
//				
//				
//				//upisana vrednost
//				Code.fixup(poms);
//				Code.put(Code.pop);
//				Code.put(Code.pop);
//				Code.put(Code.pop);
//				Code.put(Code.pop);
//				//na kraj
//				Code.fixup(nakraj);
//			}else {
//				Code.store(des.getDesignator().obj);
//			}
//			return;
//		}
		
		
		Code.store(des.getDesignator().obj);
		moze=0;
		//sad ovde hocu da dodam za onu modif sa final
		
	}
	
	
	
	
	public void visit(Pomoc p) {
		
	}
	
	//ovde za [] i .
	public void visit(DDodatno1 dod) {
		//if(currDesig==null && currDesigNiz==null) return;
		if(dod.obj.getType().getKind()==Struct.Array) {
			Code.load(currDesig);
			currDesigNiz=dod.obj;
			currDesig=null;
			currDesigElem=null;
		}else {
			if(currDesig!=null) {
				Code.load(currDesig);
				currDesig=null;
			}
		}
		
		
	}
	
	
	public void visit(DDodatno2 des) {
		
	}
	
	public void visit(Designator des) {
		/*if(des.getDesigDodatno() instanceof DesDodatno) {
			if(((DesDodatno)des.getDesigDodatno()).getDDodatno() instanceof DDodatno2) {
				
			}
		}*/
	}
	
	public void visit(DesignatorName name) {
		if(name.obj.getType().getKind()==Struct.Array) {
			currDesigNiz=name.obj;
			adrNiza=currDesigNiz;
			int p=0;
			p++;
			if(name.obj.getType().getElemType().getKind()==Struct.Class) {
				currDesigElem=new Obj(Obj.Elem, "elem", name.obj.getType().getElemType());
				currDesigElem.getType().setMembers(name.obj.getType().getElemType().getMembersTable());
			}
		}else {
			if(name.obj.getType().getKind()==Struct.Class) {
				currDesig=name.obj;
			}
		}
	}
	
	public void visit(Redosled red) {
		//ovde radim modif da se racuna broj pristupa elementu niza
		
		//ovo je dodato zbog niz[1,2] one modifikacije
		/*SyntaxNode po=red.getParent();
		if(((DDodatno2)po).getNovo() instanceof Novo1) {
			Code.load(currDesigNiz);
			//currDesigNiz=null;
			
			return;
		}*/
		if(currDesigNiz!=null) {
			if(currDesigNiz.getType().getElemType().getKind()==Struct.Class) {
				Code.load(currDesigNiz);
				currDesig=((DDodatno2)red.getParent()).obj;
				currDesigNiz=null;
			}else {
				Code.load(currDesigNiz);
				currDesigNiz=null;
			}
			
		}
		
	}
	
	
	//poziv funkcije
	public void visit(Fac8 fac8) {
		Obj functionObj = fac8.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		
		Code.put2(offset);
	}
	
	public void visit(Fac6 fac6) {
		Obj functionObj = fac6.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		
		Code.put2(offset);
	}
	
	public void visit(DesignatorStatement2 des) {
		Obj functionObj = des.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		
		Code.put2(offset);
		if(des.getDesignator().obj.getType()!=MojaTabela.noType) {
			Code.put(Code.pop);
		}
	}
	
	public void visit(DesignatorStatement3 des) {
		Obj functionObj = des.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		
		Code.put2(offset);
		if(des.getDesignator().obj.getType()!=MojaTabela.noType) {
			Code.put(Code.pop);
		}
	}
	
	public void visit(CondFact1 cond) {
		if(cond.getRelop() instanceof Relop1) {
			Code.putFalseJump(Code.eq,0);
		}else if(cond.getRelop() instanceof Relop2) {
			Code.putFalseJump(Code.ne,0);
		}else if(cond.getRelop() instanceof Relop3) {
			Code.putFalseJump(Code.gt,0);
		}else if(cond.getRelop() instanceof Relop4) {
			Code.putFalseJump(Code.lt,0);
		}else if(cond.getRelop() instanceof Relop5) {
			Code.putFalseJump(Code.ge,0);
		}else if(cond.getRelop() instanceof Relop5) {
			Code.putFalseJump(Code.le,0);
		}
	}
	
	public void visit(SStat2 sstat2) {
		
	}
	
	public void visit(ZaCond cond) {
		ifStack.add(Code.pc-2);
	}
	
	public void visit(NoElse noe) {
		Code.fixup(ifStack.pop());
	}
	
	public void visit(ElseGrana1 elseg) {
		Code.fixup(elseStack.pop());
	}
	
	public void visit(ElseRec elser) {
		Code.putJump(0);
		adr2=Code.pc-2;
		elseStack.add(adr2);
		Code.fixup(ifStack.pop());
	}
	
	//do while
	public void visit(SStat3 sstat3) {
		
	}
	
	public void visit(StatDO stat) {
		pocetakDoWhile.add(Code.pc);
	}
	
	public void visit(KrajWhile krajk) {
		int pom=pocetakDoWhile.pop();
		int pom2=zakrpiDoWhile.pop();
		Code.putJump(pom);
		Code.fixup(pom2);
		if(breakStack.size()!=0)Code.fixup(breakStack.pop());
	}
	
	public void visit(ConditionDo cond) {
		adr3=Code.pc-2;
		zakrpiDoWhile.add(adr3);
		if(continueStack.size()!=0)Code.fixup(continueStack.pop());
	}
	
	public void visit(SStat4 sstat4) {
		Code.putJump(0);
		zaB=Code.pc-2;
		breakStack.add(zaB);
	}
	
	public void visit(SStat5 sstat5) {
		Code.putJump(0);
		zaC=Code.pc-2;
		continueStack.add(zaC);
	}
	
	public void visit(ZaOr zo) {
		//Code.put(Code.dup);
		Code.put(Code.const_1);
		Code.putFalseJump(Code.eq, 0);
		zaOr.push(Code.pc-2);
		Code.put(Code.pop);
	}
	
	public void visit(ZaAnd za) {
		Code.put(Code.dup);
		Code.put(Code.const_n);
		Code.putFalseJump(Code.ne, Code.pc+1); 
		// ako je na steku 0 => preskoci taj AND, jer je netacan
		// ako je na steku 1 => ne skace se, vec se uzima sledeci izraz
		zaAnd.add(Code.pc-2);
		Code.put(Code.pop);
	}
	
	public void visit(Cond2 cond2) {
		while(zaOr.size()>0) {
			Code.fixup(zaOr.pop());
		}
	}
	
	public void visit(CondTerm2 condTerm2) {
		
	}
	
	public void visit(KrajCondTerm2 kraj) {
		
	}
	
	public void visit(KrajCond2 kraj) {
		
	}
	
	//za labelu
	public void visit(ZaLabelu labela) {
		if(listaNenadjenihLabela.contains(labela.getLabel().getName())) {
			int pom=listaNenadjenihLabela.indexOf(labela.getLabel().getName());
			int zaofs=offsetNenadjeneLabela.get(pom);
			Code.fixup(zaofs);
			listaNenadjenihLabela.remove(pom);
			offsetNenadjeneLabela.remove(pom);
		}
		listaLabela.add(labela.getLabel().getName());
		offsetLabela.add(Code.pc);
	}
	
	public void visit(SStat10 stat) {
		if(listaLabela.contains(stat.getLabel().getName())) {
			int skk=listaLabela.indexOf(stat.getLabel().getName());
			Code.putJump(skk);
		}else {
			listaNenadjenihLabela.add(stat.getLabel().getName());
			offsetNenadjeneLabela.add(Code.pc+1);
			Code.putJump(Code.pc);
			Code.fixup(offsetNenadjeneLabela.get(0));
		}
	}
	
	//| (SStat11) Designator MAJMUN Designator TACKAZAREZ ima i ovo, ne secam se za sta je modif, aha ovo je kao da formira novi niz duzine kao duzi niz bla bla, a elementi da budu zbir iz des1 i des2
	//1.modifikacija
	//x@y -> x^2 + 2*x*y + y^2
	//u singlestatement je dodato ovo | (SStat11) Designator MAJMUN NUM_CONST TACKAZAREZ
	//public void visit(SStat11 des) {
		/*Code.load(des.getDesignator().obj);
		Code.load(des.getDesignator().obj);
		Code.put(Code.mul);
		Code.put(Code.const_2);
		Code.load(des.getDesignator().obj);
		Code.put(Code.mul);
		Code.load(des.getDesignator1().obj);
		Code.put(Code.mul);
		Code.put(Code.add);
		Code.load(des.getDesignator1().obj);
		Code.load(des.getDesignator1().obj);
		Code.put(Code.mul);
		Code.put(Code.add);
		Code.store(des.getDesignator().obj);*/
		
		//ovo je 2.modifikacija
		//i to je niz@1 = niz[1] + niz[5-1]; gde je 5 arraylength, ova jedinica je NUMBER nije Expr
		/*int pom=des.getN2();
		Code.load(des.getDesignator().obj);
		Code.loadConst(pom);
	
		Code.load(des.getDesignator().obj);
		Code.loadConst(pom);
		Code.put(Code.aload);
		//int va=Code.pop;
		Code.load(des.getDesignator().obj);
		Code.load(des.getDesignator().obj);
		Code.put(Code.arraylength);
		Code.loadConst(pom);
		Code.put(Code.sub);
		Code.put(Code.aload);
		
		Code.put(Code.add);
		//int val=Code.pop;
		
		
		Code.put(Code.astore);
	}*/
	
	
	//ovo je ona sa tri+, x+++->x+=2
	//u designatorstatement dodato | (DesignatorStatement6) Designator TRIPLUS 
	/*public void visit(DesignatorStatement6 des) {
		Code.load(des.getDesignator().obj);
		Code.put(Code.const_2);
		Code.put(Code.add);
		Code.store(des.getDesignator().obj);
	}*/
	
	
	//ovo je zbog niz[1,2], tj radi se swap niz[1] i niz[2]
	//takodje, uddodatno iza expr je dodato Novi, a Novi ima ZAREZ NUM_CONST ILI /*epsilon*/
	//i ovo | (DesignatorStatement7) Designator u designatorstatement
	/*public void visit(Novo1 nov) {
		int po=nov.getN1();
		
		Code.put(Code.pop);
		Code.loadConst(po);
		Code.load(currDesigNiz);
		Code.loadConst(zaprvi);
		Code.put(Code.aload);
		//int prva_vr=Code.pop;
		Code.load(currDesigNiz);
		
		Code.loadConst(zaprvi);
		Code.load(currDesigNiz);
		
		
		//int po=nov.getN1();
		Code.loadConst(po);
		Code.put(Code.aload);
		//int druga=Code.pop;
		//druga++;
		Code.put(Code.astore);
		Code.put(Code.astore);
		//ovde sacuvano za drugu vrednost
		currDesigNiz=null;
	}*/
	
	
	//ovo je za max niza
	/*public void visit(SStat11 des) {
		int adrneka, adrkraj;
		int josneka;
		
		/*Code.load(des.getDesignator().obj);
		Code.put(Code.arraylength);
		
		Code.loadConst(1);
		Code.put(Code.add);//ovo da mi duzina niza bude 6
		int gde=Code.pc;
		Code.loadConst(1);
		Code.put(Code.sub);//sad imam 5
		//sad uvek bi trebalo da imam vrednost koliko sam simbola prosao
		Code.put(Code.dup);
		Code.loadConst(0);
		Code.putFalseJump(Code.ne, 0);//da skoci na kraj i izadje iz programa ako je pom dostiglo duzinu niza
		
		
		Code.load(des.getDesignator().obj);
		Code.put(Code.dup2);
		Code.put(Code.pop);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.put(Code.aload);

		
		
		Code.putJump(gde);
		Code.fixup(Code.pc);*/
		
		//nakon ovoga bi trebalo na steku da imam sve vrednosti
		
		/*Code.load(des.getDesignator().obj);
		Code.loadConst(0);
		Code.put(Code.aload);
		//sad mi je na steku prvi elem i on je za sad max
		Code.loadConst(1);
		Code.load(des.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.aload);
		//ovde je prvi elem ucitan
		//i za sad na steku 3 1 9
		int nekiPc=Code.pc;
		Code.put(Code.dup_x1);
		
		//sad je 3 9 1 9
		Code.put(Code.pop);
		//3 9 1
		Code.put(Code.dup_x2);
		//sad je 1 3 9 1
		Code.put(Code.pop);
		//sad je 1 3 9
		Code.put(Code.dup2);

		Code.putFalseJump(Code.gt, 0);
		int adr=Code.pc-2;
		//ako je vece
		Code.put(Code.pop);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.put(Code.dup);
		Code.load(des.getDesignator().obj);
		Code.put(Code.arraylength);
		Code.putFalseJump(Code.ne, 0);
		adrkraj=Code.pc-2;
		
		Code.load(des.getDesignator().obj);
		
		Code.put(Code.dup2);
		Code.put(Code.pop);
		Code.put(Code.aload);
		Code.putJump(nekiPc);
		
		//ako nije
		Code.fixup(adr);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.put(Code.dup);
		Code.load(des.getDesignator().obj);
		Code.put(Code.arraylength);
		Code.putFalseJump(Code.ne, 0);
		adrneka=Code.pc-2;
		
		
		Code.load(des.getDesignator().obj);
		
		Code.put(Code.dup2);
		Code.put(Code.pop);
		Code.put(Code.aload);
		Code.putJump(nekiPc);
		
		Code.fixup(adrneka);
		Code.fixup(adrkraj);
		//ovde izlaz
	}*/
	
	
	//ovo je modif za pristup elementu niza. kad se pristupa da se poveca broj pristupa
	//treba da se doda novi ispis samo da bude kao #niz[3]
	/*public void visit(PomocZaPracenjePristupa pom) {
		Code.put(Code.dup2);
		Code.put(Code.dup2);
		Code.put(Code.pop);
		Code.put(Code.arraylength);
		Code.loadConst(2);
		Code.put(Code.div);
		Code.put(Code.add);
		Code.put(Code.dup2);
		//koji elem u dodatku
		Code.put(Code.aload);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.put(Code.astore);
	}*/
	
	
	//nek se nadje za svaki slucaj
	/*//MAJA 1296124481
	//ZIZA 1514756673
	//niz@ 1296124481 ili 1514756673
	public void visit(HelpModification help) {
		for(int i=0;i<4;i++) {
			Code.load(help.getDesignator().obj);
			Code.loadConst(i);
		}
	} 
	
	public void visit(Modification mod) {
		Obj con = new Obj(Obj.Var,"$",Tab.intType);
		Code.store(con);
		
		Code.load(con);
		Code.put(Code.bastore);
		int x = 8;
		for(int i=0;i<3;i++) {
			Code.load(con);
			Code.loadConst(x);
			x+=8;
			Code.put(Code.shr);
			Code.put(Code.bastore);
		}
	}
	
	//za #niz[3] => broj ponavljanja
	public void visit(DesignatorArray desArr) {
		Code.put(Code.dup2);
		Code.put(Code.dup2);
		Code.put(Code.pop);
		Code.put(Code.arraylength);
		Code.loadConst(2);
		Code.put(Code.div);
		Code.put(Code.add);
		Code.put(Code.dup2);
		Code.put(Code.aload);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.put(Code.dup);
		Code.loadConst(5);
		Code.put(Code.print);
		Code.put(Code.astore);
	}
	//niz['a','b','c'] i kad se pozove niz[5]@4 => niz[(5+4)%arraylength]
		public void visit(Modification mod) {
			DesignatorArray des = (DesignatorArray)mod.getDesignator();
			Code.put(Code.add);
			Code.load(des.getArrayPrepare().obj);
			Code.put(Code.arraylength);
			Code.put(Code.rem);
			Code.put(Code.aload); ili baload za char
			
			Code.loadConst(5); ili Code.put(loadConst(1));
			Code.put(Code.print); ili Code.put(Code.bprint);
		}
		
		*/
}
