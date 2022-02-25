// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class Statement2 extends Statement {

    private StatementMnozina StatementMnozina;

    public Statement2 (StatementMnozina StatementMnozina) {
        this.StatementMnozina=StatementMnozina;
        if(StatementMnozina!=null) StatementMnozina.setParent(this);
    }

    public StatementMnozina getStatementMnozina() {
        return StatementMnozina;
    }

    public void setStatementMnozina(StatementMnozina StatementMnozina) {
        this.StatementMnozina=StatementMnozina;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StatementMnozina!=null) StatementMnozina.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StatementMnozina!=null) StatementMnozina.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StatementMnozina!=null) StatementMnozina.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Statement2(\n");

        if(StatementMnozina!=null)
            buffer.append(StatementMnozina.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Statement2]");
        return buffer.toString();
    }
}
