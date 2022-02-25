// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class VarNameDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String varName;
    private Zagrade Zagrade;

    public VarNameDecl (String varName, Zagrade Zagrade) {
        this.varName=varName;
        this.Zagrade=Zagrade;
        if(Zagrade!=null) Zagrade.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public Zagrade getZagrade() {
        return Zagrade;
    }

    public void setZagrade(Zagrade Zagrade) {
        this.Zagrade=Zagrade;
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
        if(Zagrade!=null) Zagrade.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Zagrade!=null) Zagrade.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Zagrade!=null) Zagrade.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarNameDecl(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(Zagrade!=null)
            buffer.append(Zagrade.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarNameDecl]");
        return buffer.toString();
    }
}
