package com.example.springpetproject.group.impl;

import com.example.springpetproject.group.GroupEntity;
import com.example.springpetproject.group.GroupRepository;
import com.example.springpetproject.group.GroupService;
import com.example.springpetproject.group.builder.BalanceGroupBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final BalanceGroupBuilder balanceGroupBuilder;

    @Autowired
    public GroupServiceImpl(final GroupRepository groupRepository, final BalanceGroupBuilder balanceGroupBuilder) {
        this.groupRepository = groupRepository;
        this.balanceGroupBuilder = balanceGroupBuilder;
    }

    public List<GroupEntity> getGroups() {
        return groupRepository.findAll();
    }

    @Override
    public GroupEntity getById(final Long groupId) {
        Optional<GroupEntity> groupOptional = groupRepository.findById(groupId);
        if (groupOptional.isEmpty()) {
            return null;
        }

        return balanceGroupBuilder.build(groupOptional.get());
    }
}
