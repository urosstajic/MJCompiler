// generated with ast extension for cup
// version 0.8
// 25/0/2022 18:29:56


package rs.ac.bg.etf.pp1.ast;

public class SStat3 extends SingleStatement {

    private StatDO StatDO;
    private Statement Statement;
    private ConditionDo ConditionDo;
    private KrajWhile KrajWhile;

    public SStat3 (StatDO StatDO, Statement Statement, ConditionDo ConditionDo, KrajWhile KrajWhile) {
        this.StatDO=StatDO;
        if(StatDO!=null) StatDO.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.ConditionDo=ConditionDo;
        if(ConditionDo!=null) ConditionDo.setParent(this);
        this.KrajWhile=KrajWhile;
        if(KrajWhile!=null) KrajWhile.setParent(this);
    }

    public StatDO getStatDO() {
        return StatDO;
    }

    public void setStatDO(StatDO StatDO) {
        this.StatDO=StatDO;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public ConditionDo getConditionDo() {
        return ConditionDo;
    }

    public void setConditionDo(ConditionDo ConditionDo) {
        this.ConditionDo=ConditionDo;
    }

    public KrajWhile getKrajWhile() {
        return KrajWhile;
    }

    public void setKrajWhile(KrajWhile KrajWhile) {
        this.KrajWhile=KrajWhile;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StatDO!=null) StatDO.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(ConditionDo!=null) ConditionDo.accept(visitor);
        if(KrajWhile!=null) KrajWhile.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StatDO!=null) StatDO.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(ConditionDo!=null) ConditionDo.traverseTopDown(visitor);
        if(KrajWhile!=null) KrajWhile.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StatDO!=null) StatDO.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(ConditionDo!=null) ConditionDo.traverseBottomUp(visitor);
        if(KrajWhile!=null) KrajWhile.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SStat3(\n");

        if(StatDO!=null)
            buffer.append(StatDO.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionDo!=null)
            buffer.append(ConditionDo.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(KrajWhile!=null)
            buffer.append(KrajWhile.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SStat3]");
        return buffer.toString();
    }
}
