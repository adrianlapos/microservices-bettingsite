package com.adrianlapos.bets.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "team")
public class TeamDTO {
    private Long id;
    private String name;

    public TeamDTO() {
        // No-argument constructor is required by JAXB
    }
    public TeamDTO(Long id,String name) {
        this.id = id;
        this.name = name;
    }
    @XmlElement(name="name")
    public String getName() {
        return name;
    }

    @XmlElement(name="id")
    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id){
        this.id = id;
    }
}
