// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class DodatnoCD2 extends DodatnoConstDecl {

    private DodatnoConstDecl DodatnoConstDecl;
    private ConstIzraz ConstIzraz;

    public DodatnoCD2 (DodatnoConstDecl DodatnoConstDecl, ConstIzraz ConstIzraz) {
        this.DodatnoConstDecl=DodatnoConstDecl;
        if(DodatnoConstDecl!=null) DodatnoConstDecl.setParent(this);
        this.ConstIzraz=ConstIzraz;
        if(ConstIzraz!=null) ConstIzraz.setParent(this);
    }

    public DodatnoConstDecl getDodatnoConstDecl() {
        return DodatnoConstDecl;
    }

    public void setDodatnoConstDecl(DodatnoConstDecl DodatnoConstDecl) {
        this.DodatnoConstDecl=DodatnoConstDecl;
    }

    public ConstIzraz getConstIzraz() {
        return ConstIzraz;
    }

    public void setConstIzraz(ConstIzraz ConstIzraz) {
        this.ConstIzraz=ConstIzraz;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DodatnoConstDecl!=null) DodatnoConstDecl.accept(visitor);
        if(ConstIzraz!=null) ConstIzraz.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DodatnoConstDecl!=null) DodatnoConstDecl.traverseTopDown(visitor);
        if(ConstIzraz!=null) ConstIzraz.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DodatnoConstDecl!=null) DodatnoConstDecl.traverseBottomUp(visitor);
        if(ConstIzraz!=null) ConstIzraz.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DodatnoCD2(\n");

        if(DodatnoConstDecl!=null)
            buffer.append(DodatnoConstDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstIzraz!=null)
            buffer.append(ConstIzraz.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DodatnoCD2]");
        return buffer.toString();
    }
}
