package com.epam.spring.core.domain;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {
    private long id;
    private String msg;
    private Date date;
    private DateFormat df;
    final private Random random = new Random();

    public Event(Date date, DateFormat df) {
        this.id = random.nextLong();
        this.date = date;
        this.df = df;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}
