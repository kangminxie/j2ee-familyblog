package com.kangmin.blog.databean;

import java.util.Date;

import org.genericdao.MaxSize;
import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class Post {

    private int id;
    private Date date;
    private String content;
    private String userEmail;

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setId(final int i) {
        this.id = i;
    }

    public void setDate(final Date d) {
        this.date = d;
    }

    public void setUserEmail(final String s) {
        this.userEmail = s;
    }

    @MaxSize(1024)
    public void setContent(final String s) {
        this.content = s;
    }
}
