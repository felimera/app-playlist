package com.project.appplaylist;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AppPlaylistApplicationTests {

    @Test
    void contextLoads() {
        AppPlaylistApplication myClass = new AppPlaylistApplication();
        assertThat(myClass).isNotNull();
    }
}
