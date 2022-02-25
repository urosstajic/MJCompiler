// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class VarDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private VarType VarType;
    private VarNameDecl VarNameDecl;
    private ViseVarDecl ViseVarDecl;

    public VarDecl (VarType VarType, VarNameDecl VarNameDecl, ViseVarDecl ViseVarDecl) {
        this.VarType=VarType;
        if(VarType!=null) VarType.setParent(this);
        this.VarNameDecl=VarNameDecl;
        if(VarNameDecl!=null) VarNameDecl.setParent(this);
        this.ViseVarDecl=ViseVarDecl;
        if(ViseVarDecl!=null) ViseVarDecl.setParent(this);
    }

    public VarType getVarType() {
        return VarType;
    }

    public void setVarType(VarType VarType) {
        this.VarType=VarType;
    }

    public VarNameDecl getVarNameDecl() {
        return VarNameDecl;
    }

    public void setVarNameDecl(VarNameDecl VarNameDecl) {
        this.VarNameDecl=VarNameDecl;
    }

    public ViseVarDecl getViseVarDecl() {
        return ViseVarDecl;
    }

    public void setViseVarDecl(ViseVarDecl ViseVarDecl) {
        this.ViseVarDecl=ViseVarDecl;
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
        if(VarType!=null) VarType.accept(visitor);
        if(VarNameDecl!=null) VarNameDecl.accept(visitor);
        if(ViseVarDecl!=null) ViseVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarType!=null) VarType.traverseTopDown(visitor);
        if(VarNameDecl!=null) VarNameDecl.traverseTopDown(visitor);
        if(ViseVarDecl!=null) ViseVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarType!=null) VarType.traverseBottomUp(visitor);
        if(VarNameDecl!=null) VarNameDecl.traverseBottomUp(visitor);
        if(ViseVarDecl!=null) ViseVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDecl(\n");

        if(VarType!=null)
            buffer.append(VarType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarNameDecl!=null)
            buffer.append(VarNameDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ViseVarDecl!=null)
            buffer.append(ViseVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDecl]");
        return buffer.toString();
    }
}
