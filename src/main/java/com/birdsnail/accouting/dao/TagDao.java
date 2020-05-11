package com.birdsnail.accouting.dao;

import com.birdsnail.accouting.model.persistent.TagPersistent;

import java.util.List;

/**
 * @author BirdSnail
 */
public interface TagDao {

    /**
     * 创建一个tag
     *
     * @param newTag tag in persistent
     */
    void createNewTag(TagPersistent newTag);

    /**
     * 更新tag
     *
     * @param tag 要更新的tag实体
     */
    void updateTag(TagPersistent tag);

    /**
     * 根据description 和 用户id查询 tag.
     *
     * @param description tag 内容
     * @param userId      用户id
     * @return tag in persistent
     */
    TagPersistent getTagByDescriptionAndUserId(String description, long userId);

    /**
     * tag id 查询tag
     *
     * @param tagId 指定的tag id
     * @return tag in persistent
     */
    TagPersistent getTagByTagId(long tagId);

    /**
     * 对tagId进行批量查询
     *
     * @param ids tag id集合
     * @return tag in persistent
     */
    List<TagPersistent> getTagListByIds(List<Long> ids);

    /**
     * 对tag分页查询
     *
     * @param pageNum  page number
     * @param pageSize limit size
     * @return {@link TagPersistent}
     */
    List<TagPersistent> getTagsByPageNumSize(int pageNum, int pageSize);
}
