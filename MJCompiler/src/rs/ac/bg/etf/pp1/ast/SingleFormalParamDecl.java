// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class SingleFormalParamDecl extends FormalParamList {

    private OneFormalParam OneFormalParam;

    public SingleFormalParamDecl (OneFormalParam OneFormalParam) {
        this.OneFormalParam=OneFormalParam;
        if(OneFormalParam!=null) OneFormalParam.setParent(this);
    }

    public OneFormalParam getOneFormalParam() {
        return OneFormalParam;
    }

    public void setOneFormalParam(OneFormalParam OneFormalParam) {
        this.OneFormalParam=OneFormalParam;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OneFormalParam!=null) OneFormalParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OneFormalParam!=null) OneFormalParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OneFormalParam!=null) OneFormalParam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleFormalParamDecl(\n");

        if(OneFormalParam!=null)
            buffer.append(OneFormalParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleFormalParamDecl]");
        return buffer.toString();
    }
}
