package com.nighthawk.spring_portfolio.mvc.channel;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Channel {

    @Id @GeneratedValue
    @Column(name = "channel_id")
    private Long Id;
    private String name;
    private String desc;
    private String creator;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreator() { 
        return creator; 
    }

    public void setCreator(String creator) { 
        this.creator = creator; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return Objects.equals(Id, channel.Id) &&
                Objects.equals(name, channel.name) &&
                Objects.equals(desc, channel.desc) &&
                Objects.equals(creator, channel.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, desc, creator);
    }
}