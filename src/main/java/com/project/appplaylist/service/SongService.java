package com.project.appplaylist.service;

import com.project.appplaylist.model.Song;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SongService {
    List<Song> getAll();
}
