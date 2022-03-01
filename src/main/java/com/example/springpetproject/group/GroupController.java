package com.example.springpetproject.group;

import com.example.springpetproject.group.impl.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupServiceImpl groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/")
    public ResponseEntity<List<GroupEntity>> getAllGroups(){
        List<GroupEntity> groups = groupService.getGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupEntity> getGroupById(@PathVariable Long groupId){
        GroupEntity groupById = groupService.getById(groupId);
        HttpStatus httpStatus =  (groupById != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(groupById, httpStatus);
    }
}
