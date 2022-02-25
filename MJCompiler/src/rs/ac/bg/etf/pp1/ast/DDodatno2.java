// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class DDodatno2 extends DDodatno {

    private Redosled Redosled;
    private Expr Expr;
    private PomocZaPracenjePristupa PomocZaPracenjePristupa;

    public DDodatno2 (Redosled Redosled, Expr Expr, PomocZaPracenjePristupa PomocZaPracenjePristupa) {
        this.Redosled=Redosled;
        if(Redosled!=null) Redosled.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.PomocZaPracenjePristupa=PomocZaPracenjePristupa;
        if(PomocZaPracenjePristupa!=null) PomocZaPracenjePristupa.setParent(this);
    }

    public Redosled getRedosled() {
        return Redosled;
    }

    public void setRedosled(Redosled Redosled) {
        this.Redosled=Redosled;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public PomocZaPracenjePristupa getPomocZaPracenjePristupa() {
        return PomocZaPracenjePristupa;
    }

    public void setPomocZaPracenjePristupa(PomocZaPracenjePristupa PomocZaPracenjePristupa) {
        this.PomocZaPracenjePristupa=PomocZaPracenjePristupa;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Redosled!=null) Redosled.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(PomocZaPracenjePristupa!=null) PomocZaPracenjePristupa.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Redosled!=null) Redosled.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(PomocZaPracenjePristupa!=null) PomocZaPracenjePristupa.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Redosled!=null) Redosled.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(PomocZaPracenjePristupa!=null) PomocZaPracenjePristupa.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DDodatno2(\n");

        if(Redosled!=null)
            buffer.append(Redosled.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(PomocZaPracenjePristupa!=null)
            buffer.append(PomocZaPracenjePristupa.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DDodatno2]");
        return buffer.toString();
    }
}
