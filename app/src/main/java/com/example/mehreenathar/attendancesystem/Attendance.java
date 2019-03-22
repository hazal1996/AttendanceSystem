package com.example.mehreenathar.attendancesystem;

import java.io.Serializable;
import java.util.Date;

public class Attendance implements Serializable
{
    private String status;//A=absent,P=present,LA=late
    private Date date;
    private boolean marked;

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked)
    {
        this.marked = marked;
    }
    public Attendance(String status, Date date,boolean marked) {
        this.status = status;
        this.date = date;
        this.marked=marked;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
