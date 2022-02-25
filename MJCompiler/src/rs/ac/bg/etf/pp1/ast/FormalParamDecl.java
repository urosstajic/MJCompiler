// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class FormalParamDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private String paramName;
    private Zagrade Zagrade;

    public FormalParamDecl (Type Type, String paramName, Zagrade Zagrade) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.paramName=paramName;
        this.Zagrade=Zagrade;
        if(Zagrade!=null) Zagrade.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName=paramName;
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
        if(Type!=null) Type.accept(visitor);
        if(Zagrade!=null) Zagrade.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(Zagrade!=null) Zagrade.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(Zagrade!=null) Zagrade.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormalParamDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+paramName);
        buffer.append("\n");

        if(Zagrade!=null)
            buffer.append(Zagrade.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalParamDecl]");
        return buffer.toString();
    }
}
