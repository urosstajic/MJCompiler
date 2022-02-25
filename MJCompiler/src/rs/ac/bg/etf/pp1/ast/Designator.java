// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class Designator implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private DesignatorName DesignatorName;
    private DesigDodatno DesigDodatno;

    public Designator (DesignatorName DesignatorName, DesigDodatno DesigDodatno) {
        this.DesignatorName=DesignatorName;
        if(DesignatorName!=null) DesignatorName.setParent(this);
        this.DesigDodatno=DesigDodatno;
        if(DesigDodatno!=null) DesigDodatno.setParent(this);
    }

    public DesignatorName getDesignatorName() {
        return DesignatorName;
    }

    public void setDesignatorName(DesignatorName DesignatorName) {
        this.DesignatorName=DesignatorName;
    }

    public DesigDodatno getDesigDodatno() {
        return DesigDodatno;
    }

    public void setDesigDodatno(DesigDodatno DesigDodatno) {
        this.DesigDodatno=DesigDodatno;
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
        if(DesignatorName!=null) DesignatorName.accept(visitor);
        if(DesigDodatno!=null) DesigDodatno.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorName!=null) DesignatorName.traverseTopDown(visitor);
        if(DesigDodatno!=null) DesigDodatno.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorName!=null) DesignatorName.traverseBottomUp(visitor);
        if(DesigDodatno!=null) DesigDodatno.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Designator(\n");

        if(DesignatorName!=null)
            buffer.append(DesignatorName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesigDodatno!=null)
            buffer.append(DesigDodatno.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator]");
        return buffer.toString();
    }
}
