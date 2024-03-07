package com.project.appplaylist.service;

import com.project.appplaylist.model.PlayList;
import com.project.appplaylist.model.Song;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SongService {
    List<Song> getAll();

    List<Song> getPlayListByListName(String listName);

    List<Song> postPlayList(PlayList playList, List<Song> songList);

    void deletePlayList(String listName);
}
