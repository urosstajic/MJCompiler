// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class SStat9 extends SingleStatement {

    private Expr Expr;
    private SStatmen1 SStatmen1;

    public SStat9 (Expr Expr, SStatmen1 SStatmen1) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.SStatmen1=SStatmen1;
        if(SStatmen1!=null) SStatmen1.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public SStatmen1 getSStatmen1() {
        return SStatmen1;
    }

    public void setSStatmen1(SStatmen1 SStatmen1) {
        this.SStatmen1=SStatmen1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(SStatmen1!=null) SStatmen1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(SStatmen1!=null) SStatmen1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(SStatmen1!=null) SStatmen1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SStat9(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SStatmen1!=null)
            buffer.append(SStatmen1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SStat9]");
        return buffer.toString();
    }
}
