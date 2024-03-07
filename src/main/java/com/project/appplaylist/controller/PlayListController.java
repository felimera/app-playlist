package com.project.appplaylist.controller;

import com.project.appplaylist.model.PlayList;
import com.project.appplaylist.service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "lists")
public class PlayListController {

    private PlayListService playListService;

    @Autowired
    public PlayListController(PlayListService playListService) {
        this.playListService = playListService;
    }

    @GetMapping
    public ResponseEntity<List<PlayList>> getAll() {
        return ResponseEntity.ok(playListService.getAll());
    }
}
