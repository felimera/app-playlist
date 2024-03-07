package com.project.appplaylist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_playlist")
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pl_id")
    private Integer id;
    @Column(name = "pl_name")
    private String name;
    @Column(name = "pl_description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
