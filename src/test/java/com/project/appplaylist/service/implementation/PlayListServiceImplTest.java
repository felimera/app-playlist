package com.project.appplaylist.service.implementation;

import com.project.appplaylist.exception.BadRequest;
import com.project.appplaylist.exception.NotFoundException;
import com.project.appplaylist.model.PlayList;
import com.project.appplaylist.model.builder.PlayListBuilder;
import com.project.appplaylist.repository.PlayListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayListServiceImplTest {

    @Mock
    private PlayListRepository playListRepository;
    @InjectMocks
    private PlayListServiceImpl playListServiceImpl;
    PlayList playList;

    @BeforeEach
    void setUp() {
        playList = PlayListBuilder.builder().build().toPlayList();
    }

    @DisplayName("Test JUnit for the GetAll method.")
    @Test
    void whenThePlayListQueryReturnsList() {
        PlayList entity = PlayListBuilder.builder().build().toEditId(2);
        given(playListRepository.findAll()).willReturn(List.of(playList, entity));
        List<PlayList> playLists = playListServiceImpl.getAll();
        assertThat(playLists).isNotNull();
        assertTrue(playLists.size() >= 2);
    }

    @DisplayName("Test JUnit for the PostPlaylist method.")
    @Test
    void whenRecordSuccessfullyRegistered() {
        given(playListRepository.getOneByName("Regguetton".toUpperCase())).willReturn(Optional.empty());
        given(playListRepository.save(any())).willReturn(playList);
        PlayList entity = playListServiceImpl.postPlayList(playList);
        assertThat(entity).isNotNull();
    }

    @DisplayName("Test JUnit for the PostPlaylist method.")
    @Test
    void whenThePlaylistNameRepeated() {
        given(playListRepository.getOneByName("Regguetton".toUpperCase())).willReturn(Optional.of(playList));
        assertThrows(BadRequest.class, () -> playListServiceImpl.postPlayList(playList));
        verify(playListRepository, never()).save(playList);
    }

    @DisplayName("Test JUnit for the DeletePlayList method.")
    @Test
    void deleteByIdSuccessful() {
        given(playListRepository.findById(1)).willReturn(Optional.of(playList));
        willDoNothing().given(playListRepository).delete(playList);
        playListServiceImpl.deletePlayList(1);
        verify(playListRepository, times(1)).delete(playList);
    }

    @DisplayName("Test JUnit for the DeletePlayList method.")
    @Test
    void wheTheRecordDoesNotExist() {
        given(playListRepository.findById(anyInt())).willReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> playListServiceImpl.deletePlayList(1));
        verify(playListRepository, never()).delete(playList);
    }
}