// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class SStat2 extends SingleStatement {

    private ZaCondition ZaCondition;
    private Statement Statement;
    private ElseGrana ElseGrana;

    public SStat2 (ZaCondition ZaCondition, Statement Statement, ElseGrana ElseGrana) {
        this.ZaCondition=ZaCondition;
        if(ZaCondition!=null) ZaCondition.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.ElseGrana=ElseGrana;
        if(ElseGrana!=null) ElseGrana.setParent(this);
    }

    public ZaCondition getZaCondition() {
        return ZaCondition;
    }

    public void setZaCondition(ZaCondition ZaCondition) {
        this.ZaCondition=ZaCondition;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public ElseGrana getElseGrana() {
        return ElseGrana;
    }

    public void setElseGrana(ElseGrana ElseGrana) {
        this.ElseGrana=ElseGrana;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ZaCondition!=null) ZaCondition.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(ElseGrana!=null) ElseGrana.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ZaCondition!=null) ZaCondition.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(ElseGrana!=null) ElseGrana.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ZaCondition!=null) ZaCondition.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(ElseGrana!=null) ElseGrana.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SStat2(\n");

        if(ZaCondition!=null)
            buffer.append(ZaCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElseGrana!=null)
            buffer.append(ElseGrana.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SStat2]");
        return buffer.toString();
    }
}
