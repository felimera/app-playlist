package com.project.appplaylist.repository;

import com.project.appplaylist.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayListRepository extends JpaRepository<PlayList, Integer> {
    @Query("select  pl from PlayList pl where upper(pl.name) = :name ")
    Optional<PlayList> getOneByName(String name);
}
