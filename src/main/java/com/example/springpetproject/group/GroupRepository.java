package com.example.springpetproject.group;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends PagingAndSortingRepository<GroupEntity, Long> {

    List<GroupEntity> findAll();
}
