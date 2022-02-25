// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class Statement1 extends Statement {

    private ZaLabelu ZaLabelu;
    private SingleStatement SingleStatement;

    public Statement1 (ZaLabelu ZaLabelu, SingleStatement SingleStatement) {
        this.ZaLabelu=ZaLabelu;
        if(ZaLabelu!=null) ZaLabelu.setParent(this);
        this.SingleStatement=SingleStatement;
        if(SingleStatement!=null) SingleStatement.setParent(this);
    }

    public ZaLabelu getZaLabelu() {
        return ZaLabelu;
    }

    public void setZaLabelu(ZaLabelu ZaLabelu) {
        this.ZaLabelu=ZaLabelu;
    }

    public SingleStatement getSingleStatement() {
        return SingleStatement;
    }

    public void setSingleStatement(SingleStatement SingleStatement) {
        this.SingleStatement=SingleStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ZaLabelu!=null) ZaLabelu.accept(visitor);
        if(SingleStatement!=null) SingleStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ZaLabelu!=null) ZaLabelu.traverseTopDown(visitor);
        if(SingleStatement!=null) SingleStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ZaLabelu!=null) ZaLabelu.traverseBottomUp(visitor);
        if(SingleStatement!=null) SingleStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Statement1(\n");

        if(ZaLabelu!=null)
            buffer.append(ZaLabelu.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleStatement!=null)
            buffer.append(SingleStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Statement1]");
        return buffer.toString();
    }
}
