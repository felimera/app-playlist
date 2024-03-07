package com.project.appplaylist.service.implementation;


import com.project.appplaylist.exception.NotFoundException;
import com.project.appplaylist.model.PlayList;
import com.project.appplaylist.model.Song;
import com.project.appplaylist.model.builder.PlayListBuilder;
import com.project.appplaylist.model.builder.SongBuilder;
import com.project.appplaylist.repository.SongRepository;
import com.project.appplaylist.service.PlayListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SongServiceImplTest {

    @Mock
    private SongRepository songRepository;
    @Mock
    private PlayListService playListService;

    @InjectMocks
    private SongServiceImpl songServiceImpl;

    Song song;
    PlayList playList;

    @BeforeEach
    void setUp() {
        playList = PlayListBuilder.builder().build().toPlayList();
        song = SongBuilder.builder().build().toEditPLayList(playList);
    }

    @DisplayName("Test JUnit for the GetAll method.")
    @Test
    void whenTheSongsQueryReturnsList() {
        PlayList playList = PlayListBuilder.builder().build().toPlayList();
        Song entity = SongBuilder.builder().build().toEditId(2, playList);
        given(songRepository.findAll()).willReturn(List.of(song, entity));
        List<Song> songList = songServiceImpl.getAll();
        assertThat(songList).isNotNull();
        assertTrue(songList.size() >= 2);
    }

    @DisplayName("Test JUnit for the GetPlayListByListName method.")
    @Test
    void whenQueryIsMadeByName() {
        given(songRepository.getPlayListByListName(anyString().toUpperCase())).willReturn(List.of(song));
        List<Song> songList = songServiceImpl.getPlayListByListName("Regguetton");
        assertThat(songList).isNotNull();
    }

    @DisplayName("Test JUnit for the GetPlayListByListName method.")
    @Test
    void whenTheQueryByNameDoesNotReturnResult() {
        given(songRepository.getPlayListByListName(anyString().toUpperCase())).willReturn(Collections.EMPTY_LIST);
        assertThrows(NotFoundException.class, () -> songServiceImpl.getPlayListByListName("Regguetton"));
        verify(songRepository, never()).getPlayListByListName("Regguetton");
    }

    @DisplayName("Test JUnit for the PostPlayList method.")
    @Test
    void whenRecordWasSuccessfullyRegistered() {
        given(playListService.postPlayList(playList)).willReturn(playList);
        given(songRepository.saveAll(Arrays.asList(song))).willReturn(Arrays.asList(song));
        List<Song> songList = songServiceImpl.postPlayList(playList, Arrays.asList(song));
        assertThat(songList).isNotNull();
        assertFalse(songList.isEmpty());
    }

    @DisplayName("Test JUnit for the DeletePlayList method.")
    @Test
    void whenRecordIsSuccessfullyDeleted() {
        given(songRepository.getPlayListByListName("Regguetton".toUpperCase())).willReturn(Arrays.asList(song));
        given(songRepository.saveAll(Arrays.asList(song))).willReturn(Collections.emptyList());
        willDoNothing().given(playListService).deletePlayList(1);
        songServiceImpl.deletePlayList("Regguetton");
        verify(songRepository, times(1)).saveAll(Arrays.asList(song));
    }

    @DisplayName("Test JUnit for the DeletePlayList method.")
    @Test
    void whenNonExistentRecordIsDeleted() {
        given(songRepository.getPlayListByListName("Regguetton".toUpperCase())).willReturn(Collections.emptyList());
        assertThrows(NotFoundException.class, () -> songServiceImpl.deletePlayList("Regguetton"));
        verify(songRepository, never()).getPlayListByListName("Regguetton");
    }
}