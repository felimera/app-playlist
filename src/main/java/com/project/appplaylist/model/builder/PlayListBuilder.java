package com.project.appplaylist.model.builder;

import com.project.appplaylist.model.PlayList;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayListBuilder {
    private Integer id;
    private String name;
    private String description;

    private PlayListBuilder toPlayListBuilder() {
        return PlayListBuilder.builder()
                .id(1)
                .name("Regguetton")
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras eu nibh non ante maximus ultricies. Pellentesque ac scelerisque odio. Aliquam.")
                .build();
    }

    public PlayList toPlayList() {
        PlayListBuilder builder = toPlayListBuilder();
        return new PlayList(
                builder.id,
                builder.name,
                builder.description
        );
    }

    public PlayList toEditId(Integer id) {
        PlayListBuilder builder = toPlayListBuilder();
        return new PlayList(id, builder.name, builder.description);
    }
}
