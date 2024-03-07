package com.project.appplaylist.service;

import com.project.appplaylist.model.PlayList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayListService {
    List<PlayList> getAll();
}
