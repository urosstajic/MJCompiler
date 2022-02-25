// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class Fac5 extends Factor {

    private Type Type;
    private Expr Expr;
    private Pomoc Pomoc;

    public Fac5 (Type Type, Expr Expr, Pomoc Pomoc) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.Pomoc=Pomoc;
        if(Pomoc!=null) Pomoc.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public Pomoc getPomoc() {
        return Pomoc;
    }

    public void setPomoc(Pomoc Pomoc) {
        this.Pomoc=Pomoc;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(Pomoc!=null) Pomoc.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(Pomoc!=null) Pomoc.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(Pomoc!=null) Pomoc.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Fac5(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Pomoc!=null)
            buffer.append(Pomoc.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Fac5]");
        return buffer.toString();
    }
}
