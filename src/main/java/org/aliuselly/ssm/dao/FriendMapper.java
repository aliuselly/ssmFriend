package org.aliuselly.ssm.dao;

import org.aliuselly.ssm.domain.Friend;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * dao 层：查询所有好友信息
 */
@Repository
public interface FriendMapper {

    /*
    * 注意
    * 写 sql 语句的时候呢，换行，必须要加空格
    * 不然会 tb_friendwhere
    * 这个样子的
    * */

    /**
     * 查询所有好友信息
     * @return
     */
    @Select("select id, name, gender, age, clazz, qq, wechat, telephone, address" +
            " from tb_friend")
    List<Friend> selectAll();

    /**
     * 添加好友信息
     * @param friend
     */
    @Insert("insert into tb_friend(name, gender, age, clazz, qq, wechat, telephone, address) values(#{name}, #{gender}, #{age}, #{clazz}, #{qq}, #{wechat}, #{telephone}, #{address})")
    void insert(Friend friend);

    /**
     * 查找指定好友信息
     * @param id
     * @return
     */
    @Select("select id, name, gender, age, clazz, qq, wechat, telephone, address" +
            " from tb_friend" +
            " where id = #{id}")
    Friend selectById(Integer id);

    /**
     * 修改好友信息
     * @param friend
     */
    @Update("update tb_friend set name = #{name}, gender = #{gender}, age = #{age}, clazz = #{clazz}, qq = #{qq}, wechat = #{wechat}, telephone = #{telephone}, address = #{address} where id = #{id}")
    void updateById(Friend friend);

    /**
     * 根据 id 删除指定好友
     *
     * 注意，外面的这个大括号一定要加，因为呢，有 , 号了
     * 以为你是多重的字符串了
     *
     * 还有，下面的是标签的用法
     * 同时，collection=array|arg0
     * 貌似必须使用这个参数了
     * 写成了 collection=ids 这个是不行的，错误了
     * 应该是，纯注解的写法了，这个 script 写法
     * 可能是无法识别 ids 这种参数了，只能够运用默认参数了
     * @param ids
     */
    @Delete({
            "<script>",
                "delete from tb_friend",
                " where id in ",
                "<foreach collection='array' item='id' open='(' separator=',' close=')'>",
                "#{id}",
                "</foreach>",
            "</script>"
    })
    void deleteByIds(Integer[] ids);
}
