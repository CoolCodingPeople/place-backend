package com.nighthawk.spring_portfolio.mvc.message;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {

    @Id @GeneratedValue
    @Column(name = "message_id")
    private Long Id;

    private String text;
    private String writer;

    private String time;
    private String channelId;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTime() { 
        return time; 
    }

    public void setTime(String time) { 
        this.time = time; 
    }

    public String getChannel() { 
        return channelId; 
    }

    public void setChannel(String channelId) { 
        this.channelId = channelId; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(Id, message.Id) &&
                Objects.equals(text, message.text) &&
                Objects.equals(writer, message.writer) &&
                Objects.equals(time, message.time) &&
                Objects.equals(channelId, message.channelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, text, writer, channelId, time);
    }
}
