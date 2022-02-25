// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class ClassDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ClassName ClassName;
    private Izvodjenje Izvodjenje;
    private VarDeclList VarDeclList;
    private UnutarKlase UnutarKlase;

    public ClassDecl (ClassName ClassName, Izvodjenje Izvodjenje, VarDeclList VarDeclList, UnutarKlase UnutarKlase) {
        this.ClassName=ClassName;
        if(ClassName!=null) ClassName.setParent(this);
        this.Izvodjenje=Izvodjenje;
        if(Izvodjenje!=null) Izvodjenje.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.UnutarKlase=UnutarKlase;
        if(UnutarKlase!=null) UnutarKlase.setParent(this);
    }

    public ClassName getClassName() {
        return ClassName;
    }

    public void setClassName(ClassName ClassName) {
        this.ClassName=ClassName;
    }

    public Izvodjenje getIzvodjenje() {
        return Izvodjenje;
    }

    public void setIzvodjenje(Izvodjenje Izvodjenje) {
        this.Izvodjenje=Izvodjenje;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public UnutarKlase getUnutarKlase() {
        return UnutarKlase;
    }

    public void setUnutarKlase(UnutarKlase UnutarKlase) {
        this.UnutarKlase=UnutarKlase;
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
        if(ClassName!=null) ClassName.accept(visitor);
        if(Izvodjenje!=null) Izvodjenje.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(UnutarKlase!=null) UnutarKlase.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassName!=null) ClassName.traverseTopDown(visitor);
        if(Izvodjenje!=null) Izvodjenje.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(UnutarKlase!=null) UnutarKlase.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassName!=null) ClassName.traverseBottomUp(visitor);
        if(Izvodjenje!=null) Izvodjenje.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(UnutarKlase!=null) UnutarKlase.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDecl(\n");

        if(ClassName!=null)
            buffer.append(ClassName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Izvodjenje!=null)
            buffer.append(Izvodjenje.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(UnutarKlase!=null)
            buffer.append(UnutarKlase.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDecl]");
        return buffer.toString();
    }
}
