package com.project.appplaylist.service.implementation;

import com.project.appplaylist.model.PlayList;
import com.project.appplaylist.repository.PlayListRepository;
import com.project.appplaylist.service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayListServiceImpl implements PlayListService {
    private PlayListRepository playListRepository;

    @Autowired
    public PlayListServiceImpl(PlayListRepository playListRepository) {
        this.playListRepository = playListRepository;
    }

    @Override
    public List<PlayList> getAll() {
        return playListRepository.findAll();
    }
}
