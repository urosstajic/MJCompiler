// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class ViseGlobalZ extends ViseGlobalZarez {

    private GlobalDeclName GlobalDeclName;

    public ViseGlobalZ (GlobalDeclName GlobalDeclName) {
        this.GlobalDeclName=GlobalDeclName;
        if(GlobalDeclName!=null) GlobalDeclName.setParent(this);
    }

    public GlobalDeclName getGlobalDeclName() {
        return GlobalDeclName;
    }

    public void setGlobalDeclName(GlobalDeclName GlobalDeclName) {
        this.GlobalDeclName=GlobalDeclName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(GlobalDeclName!=null) GlobalDeclName.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobalDeclName!=null) GlobalDeclName.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobalDeclName!=null) GlobalDeclName.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ViseGlobalZ(\n");

        if(GlobalDeclName!=null)
            buffer.append(GlobalDeclName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ViseGlobalZ]");
        return buffer.toString();
    }
}
