package com.project.appplaylist.repository;

import com.project.appplaylist.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
    @Query("select s from Song s inner join s.playList pl where upper(pl.name) like %:listName% ")
    List<Song>getPlayListByListName(String listName);
}
