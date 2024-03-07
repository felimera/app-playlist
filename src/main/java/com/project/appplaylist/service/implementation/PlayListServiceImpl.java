package com.project.appplaylist.service.implementation;

import com.project.appplaylist.exception.BadRequest;
import com.project.appplaylist.exception.NotFoundException;
import com.project.appplaylist.model.PlayList;
import com.project.appplaylist.repository.PlayListRepository;
import com.project.appplaylist.service.PlayListService;
import com.project.appplaylist.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public PlayList postPlayList(PlayList playList) {
        if (isExitByName(playList.getName())) {
            throw new BadRequest("401", Constants.MESSAGE_NOT_FOUND);
        }

        return playListRepository.save(playList);
    }

    @Override
    public void deletePlayList(Integer id) {
        Optional<PlayList> optionalPlayList = playListRepository.findById(id);
        if (optionalPlayList.isPresent())
            playListRepository.delete(optionalPlayList.get());
        else
            throw  new NotFoundException(Constants.MESSAGE_NOT_FOUND,"404", HttpStatus.NOT_FOUND);
    }

    private boolean isExitByName(String name) {
        return !playListRepository.getOneByName(name.toUpperCase()).isEmpty();
    }
}
