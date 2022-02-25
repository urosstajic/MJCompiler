// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class MultiF1 extends MultiFac {

    private MultiFac MultiFac;
    private Mulop Mulop;
    private Factor Factor;

    public MultiF1 (MultiFac MultiFac, Mulop Mulop, Factor Factor) {
        this.MultiFac=MultiFac;
        if(MultiFac!=null) MultiFac.setParent(this);
        this.Mulop=Mulop;
        if(Mulop!=null) Mulop.setParent(this);
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
    }

    public MultiFac getMultiFac() {
        return MultiFac;
    }

    public void setMultiFac(MultiFac MultiFac) {
        this.MultiFac=MultiFac;
    }

    public Mulop getMulop() {
        return Mulop;
    }

    public void setMulop(Mulop Mulop) {
        this.Mulop=Mulop;
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MultiFac!=null) MultiFac.accept(visitor);
        if(Mulop!=null) Mulop.accept(visitor);
        if(Factor!=null) Factor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MultiFac!=null) MultiFac.traverseTopDown(visitor);
        if(Mulop!=null) Mulop.traverseTopDown(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MultiFac!=null) MultiFac.traverseBottomUp(visitor);
        if(Mulop!=null) Mulop.traverseBottomUp(visitor);
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultiF1(\n");

        if(MultiFac!=null)
            buffer.append(MultiFac.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Mulop!=null)
            buffer.append(Mulop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultiF1]");
        return buffer.toString();
    }
}
