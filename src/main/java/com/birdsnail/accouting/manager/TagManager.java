package com.birdsnail.accouting.manager;

import com.birdsnail.accouting.model.common.TagCommon;
import com.birdsnail.accouting.model.service.TagView;

import java.util.List;

/**
 * @author BirdSnail
 */
public interface TagManager {

    /**
     * 创建 tag，存入数据库
     *
     * @param description 标签描述内容
     * @param userId 创建tag的id
     * @return tag对象
     */
    TagCommon createTag(String description, long userId);

    /**
     * get tag by tag id
     *
     * @param tagId special tag id
     * @return {@link TagView}
     */
    TagCommon getTagByTagId(long tagId);


    /**
     * 更新已有的tag
     *
     * @param tagCommon 更新的tag
     * @return 更新后的tag
     */
    TagCommon updateTag(TagCommon tagCommon);

    /**
     * 通过pageNumSize分页查询tag
     *
     * @param pageNum num of page
     * @param pageSize limit size
     * @return list of {@link TagCommon}
     */
    List<TagCommon> getTags(int pageNum, int pageSize);
}
