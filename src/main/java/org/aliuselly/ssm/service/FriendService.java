package org.aliuselly.ssm.service;

import org.aliuselly.ssm.domain.Friend;

import java.util.List;

/**
 * service 层：操控好友信息
 */
public interface FriendService {

    /**
     * 查询所有好友
     * @return
     */
    List<Friend> selectAll();

    /**
     * 添加
     * @param friend
     */
    void save(Friend friend);

    /**
     * 通过 id 查询好友信息
     * @param id
     * @return
     */
    Friend selectById(Integer id);

    /**
     * 根据 id 删除指定好友
     * @param ids
     */
    void deleteByIds(Integer[] ids);
}
