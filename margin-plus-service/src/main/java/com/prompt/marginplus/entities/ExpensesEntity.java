package com.prompt.marginplus.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Lenovo on 02-03-2018.
 */
@Entity
@Table(name = "expenses", schema = "accounting")
public class ExpensesEntity {
    private String userid;
    private long expenseid;
    private String expensetype;
    private Date expensedate;
    private BigDecimal expenseamount;
    private String expensecomment;
    private Timestamp creationtimestamp;
    private Timestamp modificationtimestamp;

    @Basic
    @Column(name = "userid")
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Id
    @Column(name = "expenseid")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getExpenseid() {
        return expenseid;
    }

    public void setExpenseid(long expenseid) {
        this.expenseid = expenseid;
    }

    @Basic
    @Column(name = "expensetype")
    public String getExpensetype() {
        return expensetype;
    }

    public void setExpensetype(String expensetype) {
        this.expensetype = expensetype;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "expensedate")
    public Date getExpensedate() {
        return expensedate;
    }

    public void setExpensedate(Date expensedate) {
        this.expensedate = expensedate;
    }

    @Basic
    @Column(name = "expenseamount")
    public BigDecimal getExpenseamount() {
        return expenseamount;
    }

    public void setExpenseamount(BigDecimal expenseamount) {
        this.expenseamount = expenseamount;
    }

    @Basic
    @Column(name = "expensecomment")
    public String getExpensecomment() {
        return expensecomment;
    }

    public void setExpensecomment(String expensecomment) {
        this.expensecomment = expensecomment;
    }

    @Basic
    @Column(name = "creationtimestamp")
    public Timestamp getCreationtimestamp() {
        return creationtimestamp;
    }

    public void setCreationtimestamp(Timestamp creationtimestamp) {
        this.creationtimestamp = creationtimestamp;
    }

    @Basic
    @Column(name = "modificationtimestamp")
    public Timestamp getModificationtimestamp() {
        return modificationtimestamp;
    }

    public void setModificationtimestamp(Timestamp modificationtimestamp) {
        this.modificationtimestamp = modificationtimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpensesEntity that = (ExpensesEntity) o;

        if (expenseid != that.expenseid) return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (expensetype != null ? !expensetype.equals(that.expensetype) : that.expensetype != null) return false;
        if (expensedate != null ? !expensedate.equals(that.expensedate) : that.expensedate != null) return false;
        if (expenseamount != null ? !expenseamount.equals(that.expenseamount) : that.expenseamount != null)
            return false;
        if (expensecomment != null ? !expensecomment.equals(that.expensecomment) : that.expensecomment != null)
            return false;
        if (creationtimestamp != null ? !creationtimestamp.equals(that.creationtimestamp) : that.creationtimestamp != null)
            return false;
        if (modificationtimestamp != null ? !modificationtimestamp.equals(that.modificationtimestamp) : that.modificationtimestamp != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid != null ? userid.hashCode() : 0;
        result = 31 * result + (int) (expenseid ^ (expenseid >>> 32));
        result = 31 * result + (expensetype != null ? expensetype.hashCode() : 0);
        result = 31 * result + (expensedate != null ? expensedate.hashCode() : 0);
        result = 31 * result + (expenseamount != null ? expenseamount.hashCode() : 0);
        result = 31 * result + (expensecomment != null ? expensecomment.hashCode() : 0);
        result = 31 * result + (creationtimestamp != null ? creationtimestamp.hashCode() : 0);
        result = 31 * result + (modificationtimestamp != null ? modificationtimestamp.hashCode() : 0);
        return result;
    }
}
