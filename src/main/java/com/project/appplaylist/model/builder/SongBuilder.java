package com.project.appplaylist.model.builder;


import com.project.appplaylist.model.PlayList;
import com.project.appplaylist.model.Song;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SongBuilder {
    private Integer id;
    private String title;
    private String artist;
    private String album;
    private String year;
    private String gender;
    private PlayListBuilder playListBuilder;

    private SongBuilder toSongBuilder() {
        return SongBuilder.builder()
                .id(1)
                .title("Name title")
                .artist("Name artist")
                .album("Name album")
                .year("2024")
                .gender("Name gender")
                .playListBuilder(null)
                .build();
    }

    public Song toSong() {
        SongBuilder builder = toSongBuilder();
        return new Song(
                builder.id,
                builder.title,
                builder.artist,
                builder.album,
                builder.year,
                builder.gender,
                null
        );
    }

    public Song toEditId(Integer id,PlayList playList) {
        SongBuilder builder = toSongBuilder();
        return new Song(id, builder.title, builder.artist, builder.album, builder.year, builder.gender, playList);
    }

    public Song toEditPLayList(PlayList playList) {
        SongBuilder builder = toSongBuilder();
        return new Song(builder.id, builder.title, builder.artist, builder.album, builder.year, builder.gender, playList);
    }
}
