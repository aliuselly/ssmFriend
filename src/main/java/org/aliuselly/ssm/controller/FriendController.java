package org.aliuselly.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aliuselly.ssm.domain.Friend;
import org.aliuselly.ssm.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/friend")
public class FriendController {

//    注入业务对象
    @Autowired
    private FriendService friendService;

//    存储预返回页面的对象数据
    private Map<String, Object> result = new HashMap<>();

    /**
     * 注意，这里并没有将其转换为 JSON 数据的
     * 仅仅是将这个数据放进去 response body 里面的
     * 然后呢，再由游览器那边接收，然后进行解析的
     * 如果直接输出是错误的
     *
     * 可能是转换成字符串数据了，但是，并没有进行解析
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/getFriendList")
    @ResponseBody  // 将 Map 转化为 JSON 数据
    public Map<String, Object> getFriendList(Integer page, Integer rows)
    {
//        设置每页的记录数
        PageHelper.startPage(page, rows);
//        获取所有好友信息
        List<Friend> list = friendService.selectAll();
//        封装查询结果
        PageInfo<Friend> pageInfo = new PageInfo<>(list);
//        获取总记录数
        long total = pageInfo.getTotal();
//        获取当前页数据列表
        List<Friend> friendList = pageInfo.getList();
//        存储对象数据
        result.put("total", total);
        result.put("rows", friendList);

        return result;
    }

    /**
     * @description: 添加好友信息
     * @param friend 学生信息
     * @return
     */
    @RequestMapping("/submitForm")
    @ResponseBody
    public Map<String, Object> submitForm(Friend friend)
    {
        try
        {
            friendService.save(friend);
            result.put("success", true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", "服务端发生异常！");
        }
        return result;
    }

    /**
     * 表单回显功能：通过 id 查询好友信息
     * @param id
     * @return
     */
    @RequestMapping("/editFriend")
    @ResponseBody
    public Friend editFriend(Integer id)
    {
        return friendService.selectById(id);
    }

    /**
     * 根据 id 删除指定好友信息
     * @param ids
     * @return
     */
    @RequestMapping("deleteFriend")
    @ResponseBody
    public Map<String, Object> deleteFriend(Integer[] ids)
    {
        try
        {
            friendService.deleteByIds(ids);
            result.put("success", true);
        }
        catch (Exception e)
        {
            result.put("success", false);
            result.put("msg", "服务端发生异常！");
            e.printStackTrace();
        }

        return result;
    }
}
