package com.project.appplaylist.service.implementation;

import com.project.appplaylist.exception.NotFoundException;
import com.project.appplaylist.model.PlayList;
import com.project.appplaylist.model.Song;
import com.project.appplaylist.repository.SongRepository;
import com.project.appplaylist.service.PlayListService;
import com.project.appplaylist.service.SongService;
import com.project.appplaylist.util.Constants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private SongRepository songRepository;
    private PlayListService playListService;

    @Autowired
    public SongServiceImpl(SongRepository songRepository, PlayListService playListService) {
        this.songRepository = songRepository;
        this.playListService = playListService;
    }


    @Override
    public List<Song> getAll() {
        return songRepository.findAll();
    }

    @Override
    public List<Song> getPlayListByListName(String listName) {
        List<Song> songList = songRepository.getPlayListByListName(listName.toUpperCase());
        if (songList.isEmpty())
            throw new NotFoundException(Constants.MESSAGE_NOT_FOUND, "404", HttpStatus.NOT_FOUND);
        return songList;
    }

    @Override
    @Transactional
    public List<Song> postPlayList(PlayList playList, List<Song> songList) {

        PlayList playListNew = playListService.postPlayList(playList);

        songList.forEach(song -> song.setPlayList(playListNew));

        return songRepository.saveAll(songList);
    }

    @Override
    @Transactional
    public void deletePlayList(String listName) {

        List<Song> songOlds = songRepository.getPlayListByListName(listName.toUpperCase());
        if (!songOlds.isEmpty()) {
            PlayList playList = songOlds.get(0).getPlayList();
            songOlds.forEach(song -> song.setPlayList(null));
            songRepository.saveAll(songOlds);
            playListService.deletePlayList(playList.getId());
        } else {
            throw new NotFoundException(Constants.MESSAGE_NOT_FOUND, "404", HttpStatus.NOT_FOUND);
        }
    }
}
