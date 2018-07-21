package com.prompt.marginplus.models;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Lenovo on 02-03-2018.
 */
public class Expense {
    private long expenseId;
    private String expenseType;
    private Date expenseDate;
    private BigDecimal expenseAmount;
    private String expenseComment;

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public BigDecimal getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(BigDecimal expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getExpenseComment() {
        return expenseComment;
    }

    public void setExpenseComment(String expenseComment) {
        this.expenseComment = expenseComment;
    }

    public long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(long expenseId) {
        this.expenseId = expenseId;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", expenseType='" + expenseType + '\'' +
                ", expenseDate=" + expenseDate +
                ", expenseAmount=" + expenseAmount +
                ", expenseComment='" + expenseComment + '\'' +
                '}';
    }
}
