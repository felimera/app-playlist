package com.project.appplaylist.controller;

import com.project.appplaylist.controller.dto.CancionDTO;
import com.project.appplaylist.controller.dto.PlayListDTO;
import com.project.appplaylist.controller.mapper.PlayListMapper;
import com.project.appplaylist.controller.mapper.SongMapper;
import com.project.appplaylist.model.Song;
import com.project.appplaylist.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "lists")
public class SongController {

    private SongService songService;

    @Autowired

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Song> songList = songService.getAll();
        List<PlayListDTO> playListDTOS = new ArrayList<>();
        Map<Integer, List<Song>> mapSonIntSongs = songList.stream().collect(Collectors.groupingBy(song -> song.getPlayList().getId()));
        for (Map.Entry<Integer, List<Song>> entry : mapSonIntSongs.entrySet()) {
            List<CancionDTO> cancionDTOList = new ArrayList<>();
            PlayListDTO playListDTO = PlayListMapper.INSTANCE.toDto(entry.getValue().get(0).getPlayList());

            for (Song song : entry.getValue()) {
                CancionDTO cancionDTO = SongMapper.INSTANCE.toDto(song);
                cancionDTOList.add(cancionDTO);
            }
            playListDTO.setCanciones(cancionDTOList);

            playListDTOS.add(playListDTO);
        }
        return ResponseEntity.ok(playListDTOS);
    }
}
