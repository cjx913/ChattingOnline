package com.cjx913.chattingonline.service.impl;


import com.cjx913.chattingonline.entity.Group;
import com.cjx913.chattingonline.service.GroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupServiceImpl extends BaseServiceImpl implements GroupService {
    @Override
    public List<Group> findGroupsById(Integer id) {
        return getGroupRepository().findGroupsById(id);
    }
}
