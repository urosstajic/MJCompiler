// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class MethodTypeName implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private PovratniTip PovratniTip;
    private String methName;

    public MethodTypeName (PovratniTip PovratniTip, String methName) {
        this.PovratniTip=PovratniTip;
        if(PovratniTip!=null) PovratniTip.setParent(this);
        this.methName=methName;
    }

    public PovratniTip getPovratniTip() {
        return PovratniTip;
    }

    public void setPovratniTip(PovratniTip PovratniTip) {
        this.PovratniTip=PovratniTip;
    }

    public String getMethName() {
        return methName;
    }

    public void setMethName(String methName) {
        this.methName=methName;
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
        if(PovratniTip!=null) PovratniTip.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(PovratniTip!=null) PovratniTip.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(PovratniTip!=null) PovratniTip.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodTypeName(\n");

        if(PovratniTip!=null)
            buffer.append(PovratniTip.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+methName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodTypeName]");
        return buffer.toString();
    }
}
