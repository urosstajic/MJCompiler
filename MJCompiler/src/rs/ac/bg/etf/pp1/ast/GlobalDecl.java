// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class GlobalDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ZaFinal ZaFinal;
    private GlobalType GlobalType;
    private GlobalNakonTip GlobalNakonTip;

    public GlobalDecl (ZaFinal ZaFinal, GlobalType GlobalType, GlobalNakonTip GlobalNakonTip) {
        this.ZaFinal=ZaFinal;
        if(ZaFinal!=null) ZaFinal.setParent(this);
        this.GlobalType=GlobalType;
        if(GlobalType!=null) GlobalType.setParent(this);
        this.GlobalNakonTip=GlobalNakonTip;
        if(GlobalNakonTip!=null) GlobalNakonTip.setParent(this);
    }

    public ZaFinal getZaFinal() {
        return ZaFinal;
    }

    public void setZaFinal(ZaFinal ZaFinal) {
        this.ZaFinal=ZaFinal;
    }

    public GlobalType getGlobalType() {
        return GlobalType;
    }

    public void setGlobalType(GlobalType GlobalType) {
        this.GlobalType=GlobalType;
    }

    public GlobalNakonTip getGlobalNakonTip() {
        return GlobalNakonTip;
    }

    public void setGlobalNakonTip(GlobalNakonTip GlobalNakonTip) {
        this.GlobalNakonTip=GlobalNakonTip;
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
        if(ZaFinal!=null) ZaFinal.accept(visitor);
        if(GlobalType!=null) GlobalType.accept(visitor);
        if(GlobalNakonTip!=null) GlobalNakonTip.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ZaFinal!=null) ZaFinal.traverseTopDown(visitor);
        if(GlobalType!=null) GlobalType.traverseTopDown(visitor);
        if(GlobalNakonTip!=null) GlobalNakonTip.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ZaFinal!=null) ZaFinal.traverseBottomUp(visitor);
        if(GlobalType!=null) GlobalType.traverseBottomUp(visitor);
        if(GlobalNakonTip!=null) GlobalNakonTip.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalDecl(\n");

        if(ZaFinal!=null)
            buffer.append(ZaFinal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobalType!=null)
            buffer.append(GlobalType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobalNakonTip!=null)
            buffer.append(GlobalNakonTip.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalDecl]");
        return buffer.toString();
    }
}
