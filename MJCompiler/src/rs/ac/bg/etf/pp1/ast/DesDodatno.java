// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class DesDodatno extends DesigDodatno {

    private DesigDodatno DesigDodatno;
    private DDodatno DDodatno;

    public DesDodatno (DesigDodatno DesigDodatno, DDodatno DDodatno) {
        this.DesigDodatno=DesigDodatno;
        if(DesigDodatno!=null) DesigDodatno.setParent(this);
        this.DDodatno=DDodatno;
        if(DDodatno!=null) DDodatno.setParent(this);
    }

    public DesigDodatno getDesigDodatno() {
        return DesigDodatno;
    }

    public void setDesigDodatno(DesigDodatno DesigDodatno) {
        this.DesigDodatno=DesigDodatno;
    }

    public DDodatno getDDodatno() {
        return DDodatno;
    }

    public void setDDodatno(DDodatno DDodatno) {
        this.DDodatno=DDodatno;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesigDodatno!=null) DesigDodatno.accept(visitor);
        if(DDodatno!=null) DDodatno.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesigDodatno!=null) DesigDodatno.traverseTopDown(visitor);
        if(DDodatno!=null) DDodatno.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesigDodatno!=null) DesigDodatno.traverseBottomUp(visitor);
        if(DDodatno!=null) DDodatno.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesDodatno(\n");

        if(DesigDodatno!=null)
            buffer.append(DesigDodatno.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DDodatno!=null)
            buffer.append(DDodatno.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesDodatno]");
        return buffer.toString();
    }
}
