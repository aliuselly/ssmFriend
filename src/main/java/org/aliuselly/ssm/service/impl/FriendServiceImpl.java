package org.aliuselly.ssm.service.impl;

import org.aliuselly.ssm.dao.FriendMapper;
import org.aliuselly.ssm.domain.Friend;
import org.aliuselly.ssm.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional  // 添加注解式的 spring 事物管理
public class FriendServiceImpl implements FriendService {

//    注入 mapper 接口对象
    @Autowired
    private FriendMapper friendMapper;

    /**
     * 查询所有好友信息
     * @return
     */
    @Override
    public List<Friend> selectAll() {
        return friendMapper.selectAll();
    }

    /**
     * 添加/更新 好友信息
     * @param friend
     */
    @Override
    public void save(Friend friend) {
//        判断是添加还是修改操作
        if (friend.getId() != null)
        {
//            更新操作
            friendMapper.updateById(friend);
        }
        else
        {
//            添加操作
            friendMapper.insert(friend);
        }
    }

    /**
     * 根据 id 查询指定好友信息
     * @param id
     * @return
     */
    @Override
    public Friend selectById(Integer id) {
        return friendMapper.selectById(id);
    }

    /**
     * 根据 id 删除指定好友信息
     * @param ids
     */
    @Override
    public void deleteByIds(Integer[] ids) {
        friendMapper.deleteByIds(ids);
    }
}
