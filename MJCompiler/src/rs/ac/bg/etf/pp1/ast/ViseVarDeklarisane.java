// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class ViseVarDeklarisane extends ViseVarDecl {

    private ViseVarDecl ViseVarDecl;
    private VarNameDecl VarNameDecl;

    public ViseVarDeklarisane (ViseVarDecl ViseVarDecl, VarNameDecl VarNameDecl) {
        this.ViseVarDecl=ViseVarDecl;
        if(ViseVarDecl!=null) ViseVarDecl.setParent(this);
        this.VarNameDecl=VarNameDecl;
        if(VarNameDecl!=null) VarNameDecl.setParent(this);
    }

    public ViseVarDecl getViseVarDecl() {
        return ViseVarDecl;
    }

    public void setViseVarDecl(ViseVarDecl ViseVarDecl) {
        this.ViseVarDecl=ViseVarDecl;
    }

    public VarNameDecl getVarNameDecl() {
        return VarNameDecl;
    }

    public void setVarNameDecl(VarNameDecl VarNameDecl) {
        this.VarNameDecl=VarNameDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ViseVarDecl!=null) ViseVarDecl.accept(visitor);
        if(VarNameDecl!=null) VarNameDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ViseVarDecl!=null) ViseVarDecl.traverseTopDown(visitor);
        if(VarNameDecl!=null) VarNameDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ViseVarDecl!=null) ViseVarDecl.traverseBottomUp(visitor);
        if(VarNameDecl!=null) VarNameDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ViseVarDeklarisane(\n");

        if(ViseVarDecl!=null)
            buffer.append(ViseVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarNameDecl!=null)
            buffer.append(VarNameDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ViseVarDeklarisane]");
        return buffer.toString();
    }
}
