package com.birdsnail.accouting.dao.mapper;

import com.birdsnail.accouting.dao.provider.TagSqlProvider;
import com.birdsnail.accouting.model.persistent.TagPersistent;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author BirdSnail
 */
@Mapper
public interface TagMapper {

    /**
     * 插入一条tag
     *
     * @param tagPersistent tag in persistent
     * @return 更新的行数
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into accounting_tag(description, user_id, status)" +
            "values (#{description}, #{userId}, #{status})")
    int insertTag(TagPersistent tagPersistent);

    /**
     * 通过tag id查询
     *
     * @param tagId tag id
     * @return tag in persistent
     */
    @Select("select id, description, status, user_id, create_time, update_time FROM accounting_tag where id = #{tagId}")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "description", column = "description"),
            @Result(property = "status", column = "status"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    TagPersistent getTagByTagId(long tagId);

    /**
     * 标签内容和用户id查询tag
     *
     * @param description tag description
     * @param userId      user id
     * @return tag in persistent
     */
    @Select("select id, description, status, user_id, create_time, update_time FROM accounting_tag " +
            "where description = #{description} and user_id = #{userId}")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "description", column = "description"),
            @Result(property = "status", column = "status"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    TagPersistent getTagByDescriptionAndUserId(@Param("description") String description, @Param("userId") long userId);

    /**
     * 更新tag
     *
     * @param updateTag tag
     * @return 更新的tag
     */
    @UpdateProvider(type = TagSqlProvider.class, method = "updateTag")
    @Options(resultSets = "id, description, status, user_id, create_time, update_time")
    int updateTag(TagPersistent updateTag);

    /**
     * 批量获取tag
     *
     * @param ids list of tag id
     * @return list of tag in persistent
     */
    @SelectProvider(type = TagSqlProvider.class, method = "getTagListByIds")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "description", property = "description"),
            @Result(column = "status", property = "status"),
            @Result(column = "user_id", property = "userId"),
    })
    List<TagPersistent> getTagListByIds(List<Long> ids);
}
