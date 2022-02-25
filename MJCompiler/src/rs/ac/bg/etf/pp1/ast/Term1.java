// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class Term1 extends Term {

    private Factor Factor;
    private MultiFac MultiFac;

    public Term1 (Factor Factor, MultiFac MultiFac) {
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
        this.MultiFac=MultiFac;
        if(MultiFac!=null) MultiFac.setParent(this);
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public MultiFac getMultiFac() {
        return MultiFac;
    }

    public void setMultiFac(MultiFac MultiFac) {
        this.MultiFac=MultiFac;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Factor!=null) Factor.accept(visitor);
        if(MultiFac!=null) MultiFac.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
        if(MultiFac!=null) MultiFac.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        if(MultiFac!=null) MultiFac.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Term1(\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MultiFac!=null)
            buffer.append(MultiFac.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Term1]");
        return buffer.toString();
    }
}
