package org.cdisource.springapp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class Task {

    @NotNull
    private String title;

    private Boolean done;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Version
    @Column(name = "version")
    private Integer version;

    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Boolean getDone() {
        return this.done;
    }
    
    public void setDone(Boolean done) {
        this.done = done;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(getTitle()).append(", ");
        sb.append("Done: ").append(getDone());
        return sb.toString();
    }

    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }
}
