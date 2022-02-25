// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class Cond2 extends Condition {

    private Condition Condition;
    private ZaOr ZaOr;
    private CondTerm CondTerm;
    private KrajCond2 KrajCond2;

    public Cond2 (Condition Condition, ZaOr ZaOr, CondTerm CondTerm, KrajCond2 KrajCond2) {
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.ZaOr=ZaOr;
        if(ZaOr!=null) ZaOr.setParent(this);
        this.CondTerm=CondTerm;
        if(CondTerm!=null) CondTerm.setParent(this);
        this.KrajCond2=KrajCond2;
        if(KrajCond2!=null) KrajCond2.setParent(this);
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public ZaOr getZaOr() {
        return ZaOr;
    }

    public void setZaOr(ZaOr ZaOr) {
        this.ZaOr=ZaOr;
    }

    public CondTerm getCondTerm() {
        return CondTerm;
    }

    public void setCondTerm(CondTerm CondTerm) {
        this.CondTerm=CondTerm;
    }

    public KrajCond2 getKrajCond2() {
        return KrajCond2;
    }

    public void setKrajCond2(KrajCond2 KrajCond2) {
        this.KrajCond2=KrajCond2;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Condition!=null) Condition.accept(visitor);
        if(ZaOr!=null) ZaOr.accept(visitor);
        if(CondTerm!=null) CondTerm.accept(visitor);
        if(KrajCond2!=null) KrajCond2.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(ZaOr!=null) ZaOr.traverseTopDown(visitor);
        if(CondTerm!=null) CondTerm.traverseTopDown(visitor);
        if(KrajCond2!=null) KrajCond2.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(ZaOr!=null) ZaOr.traverseBottomUp(visitor);
        if(CondTerm!=null) CondTerm.traverseBottomUp(visitor);
        if(KrajCond2!=null) KrajCond2.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Cond2(\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ZaOr!=null)
            buffer.append(ZaOr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondTerm!=null)
            buffer.append(CondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(KrajCond2!=null)
            buffer.append(KrajCond2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Cond2]");
        return buffer.toString();
    }
}
