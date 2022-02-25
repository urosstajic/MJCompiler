// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class GlobalNakonTipa2 extends GlobalNakonTip {

    private ViseGlobalZarez ViseGlobalZarez;
    private GlobalNakonTip GlobalNakonTip;

    public GlobalNakonTipa2 (ViseGlobalZarez ViseGlobalZarez, GlobalNakonTip GlobalNakonTip) {
        this.ViseGlobalZarez=ViseGlobalZarez;
        if(ViseGlobalZarez!=null) ViseGlobalZarez.setParent(this);
        this.GlobalNakonTip=GlobalNakonTip;
        if(GlobalNakonTip!=null) GlobalNakonTip.setParent(this);
    }

    public ViseGlobalZarez getViseGlobalZarez() {
        return ViseGlobalZarez;
    }

    public void setViseGlobalZarez(ViseGlobalZarez ViseGlobalZarez) {
        this.ViseGlobalZarez=ViseGlobalZarez;
    }

    public GlobalNakonTip getGlobalNakonTip() {
        return GlobalNakonTip;
    }

    public void setGlobalNakonTip(GlobalNakonTip GlobalNakonTip) {
        this.GlobalNakonTip=GlobalNakonTip;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ViseGlobalZarez!=null) ViseGlobalZarez.accept(visitor);
        if(GlobalNakonTip!=null) GlobalNakonTip.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ViseGlobalZarez!=null) ViseGlobalZarez.traverseTopDown(visitor);
        if(GlobalNakonTip!=null) GlobalNakonTip.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ViseGlobalZarez!=null) ViseGlobalZarez.traverseBottomUp(visitor);
        if(GlobalNakonTip!=null) GlobalNakonTip.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalNakonTipa2(\n");

        if(ViseGlobalZarez!=null)
            buffer.append(ViseGlobalZarez.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobalNakonTip!=null)
            buffer.append(GlobalNakonTip.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalNakonTipa2]");
        return buffer.toString();
    }
}
