package com.project.appplaylist.service.implementation;

import com.project.appplaylist.model.Song;
import com.project.appplaylist.repository.SongRepository;
import com.project.appplaylist.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private SongRepository songRepository;

    @Autowired
    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> getAll() {
        return songRepository.findAll();
    }
}
