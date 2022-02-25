// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class ConstIzraz implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String varName;
    private TipConst TipConst;

    public ConstIzraz (String varName, TipConst TipConst) {
        this.varName=varName;
        this.TipConst=TipConst;
        if(TipConst!=null) TipConst.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public TipConst getTipConst() {
        return TipConst;
    }

    public void setTipConst(TipConst TipConst) {
        this.TipConst=TipConst;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
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
        buffer.append("ConstIzraz(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(TipConst!=null)
            buffer.append(TipConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstIzraz]");
        return buffer.toString();
    }
}
