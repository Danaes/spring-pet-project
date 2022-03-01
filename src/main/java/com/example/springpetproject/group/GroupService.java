package com.example.springpetproject.group;

import java.util.List;

public interface GroupService {
    List<GroupEntity> getGroups();

    GroupEntity getById(Long groupId);
}
