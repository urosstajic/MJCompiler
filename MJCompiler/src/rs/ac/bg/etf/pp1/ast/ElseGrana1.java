// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class ElseGrana1 extends ElseGrana {

    private ElseRec ElseRec;
    private Statement Statement;

    public ElseGrana1 (ElseRec ElseRec, Statement Statement) {
        this.ElseRec=ElseRec;
        if(ElseRec!=null) ElseRec.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ElseRec getElseRec() {
        return ElseRec;
    }

    public void setElseRec(ElseRec ElseRec) {
        this.ElseRec=ElseRec;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ElseRec!=null) ElseRec.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ElseRec!=null) ElseRec.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ElseRec!=null) ElseRec.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ElseGrana1(\n");

        if(ElseRec!=null)
            buffer.append(ElseRec.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ElseGrana1]");
        return buffer.toString();
    }
}
