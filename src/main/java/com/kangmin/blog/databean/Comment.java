package com.kangmin.blog.databean;

import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class Comment {

    private int id;
    private Date date;
    private String content;
    private int replyTo;
    private String byWho;
    private String byEmail;

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public int getReplyTo() {
        return replyTo;
    }

    public String getByWho() {
        return byWho;
    }

    public String getByEmail() {
        return byEmail;
    }

    public void setId(final int i) {
        this.id = i;
    }

    public void setDate(final Date d) {
        this.date = d;
    }

    public void setReplyTo(final int i) {
        this.replyTo = i;
    }

    public void setByWho(final String s) {
        this.byWho = s;
    }

    public void setByEmail(final String s) {
        this.byEmail = s;
    }

    public void setContent(final String s) {
        this.content = s;
    }
}
