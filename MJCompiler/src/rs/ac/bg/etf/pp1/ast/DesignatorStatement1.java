// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatement1 extends DesignatorStatement {

    private Designator Designator;
    private Assignop Assignop;
    private Expr Expr;
    private DS1Dodatak DS1Dodatak;

    public DesignatorStatement1 (Designator Designator, Assignop Assignop, Expr Expr, DS1Dodatak DS1Dodatak) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.Assignop=Assignop;
        if(Assignop!=null) Assignop.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.DS1Dodatak=DS1Dodatak;
        if(DS1Dodatak!=null) DS1Dodatak.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public Assignop getAssignop() {
        return Assignop;
    }

    public void setAssignop(Assignop Assignop) {
        this.Assignop=Assignop;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public DS1Dodatak getDS1Dodatak() {
        return DS1Dodatak;
    }

    public void setDS1Dodatak(DS1Dodatak DS1Dodatak) {
        this.DS1Dodatak=DS1Dodatak;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(Assignop!=null) Assignop.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(DS1Dodatak!=null) DS1Dodatak.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(Assignop!=null) Assignop.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(DS1Dodatak!=null) DS1Dodatak.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(Assignop!=null) Assignop.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(DS1Dodatak!=null) DS1Dodatak.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatement1(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Assignop!=null)
            buffer.append(Assignop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DS1Dodatak!=null)
            buffer.append(DS1Dodatak.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatement1]");
        return buffer.toString();
    }
}
