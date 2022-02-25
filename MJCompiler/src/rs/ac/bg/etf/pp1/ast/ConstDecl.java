// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ConstType ConstType;
    private ConstIzraz ConstIzraz;
    private DodatnoConstDecl DodatnoConstDecl;

    public ConstDecl (ConstType ConstType, ConstIzraz ConstIzraz, DodatnoConstDecl DodatnoConstDecl) {
        this.ConstType=ConstType;
        if(ConstType!=null) ConstType.setParent(this);
        this.ConstIzraz=ConstIzraz;
        if(ConstIzraz!=null) ConstIzraz.setParent(this);
        this.DodatnoConstDecl=DodatnoConstDecl;
        if(DodatnoConstDecl!=null) DodatnoConstDecl.setParent(this);
    }

    public ConstType getConstType() {
        return ConstType;
    }

    public void setConstType(ConstType ConstType) {
        this.ConstType=ConstType;
    }

    public ConstIzraz getConstIzraz() {
        return ConstIzraz;
    }

    public void setConstIzraz(ConstIzraz ConstIzraz) {
        this.ConstIzraz=ConstIzraz;
    }

    public DodatnoConstDecl getDodatnoConstDecl() {
        return DodatnoConstDecl;
    }

    public void setDodatnoConstDecl(DodatnoConstDecl DodatnoConstDecl) {
        this.DodatnoConstDecl=DodatnoConstDecl;
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
        if(ConstType!=null) ConstType.accept(visitor);
        if(ConstIzraz!=null) ConstIzraz.accept(visitor);
        if(DodatnoConstDecl!=null) DodatnoConstDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstType!=null) ConstType.traverseTopDown(visitor);
        if(ConstIzraz!=null) ConstIzraz.traverseTopDown(visitor);
        if(DodatnoConstDecl!=null) DodatnoConstDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstType!=null) ConstType.traverseBottomUp(visitor);
        if(ConstIzraz!=null) ConstIzraz.traverseBottomUp(visitor);
        if(DodatnoConstDecl!=null) DodatnoConstDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDecl(\n");

        if(ConstType!=null)
            buffer.append(ConstType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstIzraz!=null)
            buffer.append(ConstIzraz.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DodatnoConstDecl!=null)
            buffer.append(DodatnoConstDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDecl]");
        return buffer.toString();
    }
}
