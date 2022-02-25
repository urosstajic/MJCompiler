// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class CondTerm2 extends CondTerm {

    private CondTerm CondTerm;
    private ZaAnd ZaAnd;
    private CondFact CondFact;
    private KrajCondTerm2 KrajCondTerm2;

    public CondTerm2 (CondTerm CondTerm, ZaAnd ZaAnd, CondFact CondFact, KrajCondTerm2 KrajCondTerm2) {
        this.CondTerm=CondTerm;
        if(CondTerm!=null) CondTerm.setParent(this);
        this.ZaAnd=ZaAnd;
        if(ZaAnd!=null) ZaAnd.setParent(this);
        this.CondFact=CondFact;
        if(CondFact!=null) CondFact.setParent(this);
        this.KrajCondTerm2=KrajCondTerm2;
        if(KrajCondTerm2!=null) KrajCondTerm2.setParent(this);
    }

    public CondTerm getCondTerm() {
        return CondTerm;
    }

    public void setCondTerm(CondTerm CondTerm) {
        this.CondTerm=CondTerm;
    }

    public ZaAnd getZaAnd() {
        return ZaAnd;
    }

    public void setZaAnd(ZaAnd ZaAnd) {
        this.ZaAnd=ZaAnd;
    }

    public CondFact getCondFact() {
        return CondFact;
    }

    public void setCondFact(CondFact CondFact) {
        this.CondFact=CondFact;
    }

    public KrajCondTerm2 getKrajCondTerm2() {
        return KrajCondTerm2;
    }

    public void setKrajCondTerm2(KrajCondTerm2 KrajCondTerm2) {
        this.KrajCondTerm2=KrajCondTerm2;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondTerm!=null) CondTerm.accept(visitor);
        if(ZaAnd!=null) ZaAnd.accept(visitor);
        if(CondFact!=null) CondFact.accept(visitor);
        if(KrajCondTerm2!=null) KrajCondTerm2.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondTerm!=null) CondTerm.traverseTopDown(visitor);
        if(ZaAnd!=null) ZaAnd.traverseTopDown(visitor);
        if(CondFact!=null) CondFact.traverseTopDown(visitor);
        if(KrajCondTerm2!=null) KrajCondTerm2.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondTerm!=null) CondTerm.traverseBottomUp(visitor);
        if(ZaAnd!=null) ZaAnd.traverseBottomUp(visitor);
        if(CondFact!=null) CondFact.traverseBottomUp(visitor);
        if(KrajCondTerm2!=null) KrajCondTerm2.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondTerm2(\n");

        if(CondTerm!=null)
            buffer.append(CondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ZaAnd!=null)
            buffer.append(ZaAnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondFact!=null)
            buffer.append(CondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(KrajCondTerm2!=null)
            buffer.append(KrajCondTerm2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondTerm2]");
        return buffer.toString();
    }
}
