package com.adrianlapos.clients.team;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "team")
public class TeamDTO {
    private String name;

    public TeamDTO() {
        // No-argument constructor is required by JAXB
    }
    public TeamDTO(String name) {
        this.name = name;
    }
    @XmlElement(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
