// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class Fac1 extends Factor {

    private TipConst TipConst;

    public Fac1 (TipConst TipConst) {
        this.TipConst=TipConst;
        if(TipConst!=null) TipConst.setParent(this);
    }

    public TipConst getTipConst() {
        return TipConst;
    }

    public void setTipConst(TipConst TipConst) {
        this.TipConst=TipConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TipConst!=null) TipConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TipConst!=null) TipConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TipConst!=null) TipConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Fac1(\n");

        if(TipConst!=null)
            buffer.append(TipConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Fac1]");
        return buffer.toString();
    }
}
