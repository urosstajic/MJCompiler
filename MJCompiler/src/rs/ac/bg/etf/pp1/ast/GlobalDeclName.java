// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class GlobalDeclName implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private GlobalDeclImeProm GlobalDeclImeProm;
    private Zagrade Zagrade;

    public GlobalDeclName (GlobalDeclImeProm GlobalDeclImeProm, Zagrade Zagrade) {
        this.GlobalDeclImeProm=GlobalDeclImeProm;
        if(GlobalDeclImeProm!=null) GlobalDeclImeProm.setParent(this);
        this.Zagrade=Zagrade;
        if(Zagrade!=null) Zagrade.setParent(this);
    }

    public GlobalDeclImeProm getGlobalDeclImeProm() {
        return GlobalDeclImeProm;
    }

    public void setGlobalDeclImeProm(GlobalDeclImeProm GlobalDeclImeProm) {
        this.GlobalDeclImeProm=GlobalDeclImeProm;
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
        if(GlobalDeclImeProm!=null) GlobalDeclImeProm.accept(visitor);
        if(Zagrade!=null) Zagrade.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobalDeclImeProm!=null) GlobalDeclImeProm.traverseTopDown(visitor);
        if(Zagrade!=null) Zagrade.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobalDeclImeProm!=null) GlobalDeclImeProm.traverseBottomUp(visitor);
        if(Zagrade!=null) Zagrade.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalDeclName(\n");

        if(GlobalDeclImeProm!=null)
            buffer.append(GlobalDeclImeProm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Zagrade!=null)
            buffer.append(Zagrade.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalDeclName]");
        return buffer.toString();
    }
}
