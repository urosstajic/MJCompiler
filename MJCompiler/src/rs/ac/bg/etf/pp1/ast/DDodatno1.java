// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class DDodatno1 extends DDodatno {

    private String dodatnoIme;

    public DDodatno1 (String dodatnoIme) {
        this.dodatnoIme=dodatnoIme;
    }

    public String getDodatnoIme() {
        return dodatnoIme;
    }

    public void setDodatnoIme(String dodatnoIme) {
        this.dodatnoIme=dodatnoIme;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DDodatno1(\n");

        buffer.append(" "+tab+dodatnoIme);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DDodatno1]");
        return buffer.toString();
    }
}
