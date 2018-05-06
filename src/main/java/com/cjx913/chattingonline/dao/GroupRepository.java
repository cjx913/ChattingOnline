package com.cjx913.chattingonline.dao;

import com.cjx913.chattingonline.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GroupRepository extends JpaRepository<Group,Integer> {
    List<Group> findGroupsById(Integer id);
}
