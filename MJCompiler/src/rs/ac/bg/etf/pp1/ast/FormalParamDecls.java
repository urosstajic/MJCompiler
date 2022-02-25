// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class FormalParamDecls extends FormalParamList {

    private ViseFormalParamDecl ViseFormalParamDecl;
    private FormalParamList FormalParamList;

    public FormalParamDecls (ViseFormalParamDecl ViseFormalParamDecl, FormalParamList FormalParamList) {
        this.ViseFormalParamDecl=ViseFormalParamDecl;
        if(ViseFormalParamDecl!=null) ViseFormalParamDecl.setParent(this);
        this.FormalParamList=FormalParamList;
        if(FormalParamList!=null) FormalParamList.setParent(this);
    }

    public ViseFormalParamDecl getViseFormalParamDecl() {
        return ViseFormalParamDecl;
    }

    public void setViseFormalParamDecl(ViseFormalParamDecl ViseFormalParamDecl) {
        this.ViseFormalParamDecl=ViseFormalParamDecl;
    }

    public FormalParamList getFormalParamList() {
        return FormalParamList;
    }

    public void setFormalParamList(FormalParamList FormalParamList) {
        this.FormalParamList=FormalParamList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ViseFormalParamDecl!=null) ViseFormalParamDecl.accept(visitor);
        if(FormalParamList!=null) FormalParamList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ViseFormalParamDecl!=null) ViseFormalParamDecl.traverseTopDown(visitor);
        if(FormalParamList!=null) FormalParamList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ViseFormalParamDecl!=null) ViseFormalParamDecl.traverseBottomUp(visitor);
        if(FormalParamList!=null) FormalParamList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormalParamDecls(\n");

        if(ViseFormalParamDecl!=null)
            buffer.append(ViseFormalParamDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormalParamList!=null)
            buffer.append(FormalParamList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalParamDecls]");
        return buffer.toString();
    }
}
