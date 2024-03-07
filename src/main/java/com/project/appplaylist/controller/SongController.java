package com.project.appplaylist.controller;

import com.project.appplaylist.controller.dto.CancionDTO;
import com.project.appplaylist.controller.dto.PlayListDTO;
import com.project.appplaylist.controller.mapper.PlayListMapper;
import com.project.appplaylist.controller.mapper.SongMapper;
import com.project.appplaylist.model.PlayList;
import com.project.appplaylist.model.Song;
import com.project.appplaylist.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    public ResponseEntity<List<PlayListDTO>> getAll() {
        List<Song> songList = songService.getAll();
        List<PlayListDTO> playListDTOS = new ArrayList<>();
        Map<Integer, List<Song>> mapSonIntSongs = songList.stream()
                .filter(song -> Objects.nonNull(song.getPlayList()))
                .collect(Collectors.groupingBy(song -> song.getPlayList().getId()));

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

    @GetMapping(path = "/{listName}")
    public ResponseEntity<Object> getPlayListByListName(@PathVariable(name = "listName") String listName) {
        List<Song> songList = songService.getPlayListByListName(listName);
        PlayListDTO playListDTO = this.addPlayList(songList);
        return ResponseEntity.ok(playListDTO);
    }

    @PostMapping
    public ResponseEntity<PlayListDTO> postPlayList(@RequestBody PlayListDTO dto) {
        PlayList playList = PlayListMapper.INSTANCE.toEntity(dto);

        List<Song> songList = dto.getCanciones().stream().map(SongMapper.INSTANCE::toEntity).toList();
        List<Song> songListNew = songService.postPlayList(playList, songList);
        PlayListDTO playListDTO = this.addPlayList(songListNew);

        return ResponseEntity.status(HttpStatus.CREATED).body(playListDTO);
    }

    @DeleteMapping(path = "/{listName}")
    public ResponseEntity<Object> deletePlayList(@PathVariable(name = "listName") String listName) {
        songService.deletePlayList(listName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private PlayListDTO addPlayList(List<Song> songListNew) {
        PlayListDTO playListDTO = null;
        Map<Integer, List<Song>> mapSonIntSongs = songListNew.stream().collect(Collectors.groupingBy(song -> song.getPlayList().getId()));
        for (Map.Entry<Integer, List<Song>> entry : mapSonIntSongs.entrySet()) {
            List<CancionDTO> cancionDTOList = new ArrayList<>();
            playListDTO = PlayListMapper.INSTANCE.toDto(entry.getValue().get(0).getPlayList());

            for (Song song : entry.getValue()) {
                CancionDTO cancionDTO = SongMapper.INSTANCE.toDto(song);
                cancionDTOList.add(cancionDTO);
            }
            playListDTO.setCanciones(cancionDTOList);
        }

        return playListDTO;
    }
}
