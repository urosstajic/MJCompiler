// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class GlobalNakonTipa1 extends GlobalNakonTip {

    private SamoJedno SamoJedno;

    public GlobalNakonTipa1 (SamoJedno SamoJedno) {
        this.SamoJedno=SamoJedno;
        if(SamoJedno!=null) SamoJedno.setParent(this);
    }

    public SamoJedno getSamoJedno() {
        return SamoJedno;
    }

    public void setSamoJedno(SamoJedno SamoJedno) {
        this.SamoJedno=SamoJedno;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SamoJedno!=null) SamoJedno.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SamoJedno!=null) SamoJedno.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SamoJedno!=null) SamoJedno.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalNakonTipa1(\n");

        if(SamoJedno!=null)
            buffer.append(SamoJedno.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalNakonTipa1]");
        return buffer.toString();
    }
}
