package com.project.appplaylist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "son_id")
    private Integer id;
    @Column(name = "son_title")
    private String title;
    @Column(name = "son_artist")
    private String artist;
    @Column(name = "son_album")
    private String album;
    @Column(name = "son_year")
    private String year;
    @Column(name = "son_gender")
    private String gender;
}
