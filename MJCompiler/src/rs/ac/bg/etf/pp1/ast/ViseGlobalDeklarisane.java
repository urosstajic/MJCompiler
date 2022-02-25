// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class ViseGlobalDeklarisane extends ViseGlobalDecl {

    private ViseGlobalZarez ViseGlobalZarez;
    private ViseGlobalDecl ViseGlobalDecl;

    public ViseGlobalDeklarisane (ViseGlobalZarez ViseGlobalZarez, ViseGlobalDecl ViseGlobalDecl) {
        this.ViseGlobalZarez=ViseGlobalZarez;
        if(ViseGlobalZarez!=null) ViseGlobalZarez.setParent(this);
        this.ViseGlobalDecl=ViseGlobalDecl;
        if(ViseGlobalDecl!=null) ViseGlobalDecl.setParent(this);
    }

    public ViseGlobalZarez getViseGlobalZarez() {
        return ViseGlobalZarez;
    }

    public void setViseGlobalZarez(ViseGlobalZarez ViseGlobalZarez) {
        this.ViseGlobalZarez=ViseGlobalZarez;
    }

    public ViseGlobalDecl getViseGlobalDecl() {
        return ViseGlobalDecl;
    }

    public void setViseGlobalDecl(ViseGlobalDecl ViseGlobalDecl) {
        this.ViseGlobalDecl=ViseGlobalDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ViseGlobalZarez!=null) ViseGlobalZarez.accept(visitor);
        if(ViseGlobalDecl!=null) ViseGlobalDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ViseGlobalZarez!=null) ViseGlobalZarez.traverseTopDown(visitor);
        if(ViseGlobalDecl!=null) ViseGlobalDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ViseGlobalZarez!=null) ViseGlobalZarez.traverseBottomUp(visitor);
        if(ViseGlobalDecl!=null) ViseGlobalDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ViseGlobalDeklarisane(\n");

        if(ViseGlobalZarez!=null)
            buffer.append(ViseGlobalZarez.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ViseGlobalDecl!=null)
            buffer.append(ViseGlobalDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ViseGlobalDeklarisane]");
        return buffer.toString();
    }
}
