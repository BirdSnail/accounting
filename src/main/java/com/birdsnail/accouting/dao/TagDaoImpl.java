package com.birdsnail.accouting.dao;

import com.birdsnail.accouting.dao.mapper.TagMapper;
import com.birdsnail.accouting.model.persistent.TagPersistent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author BirdSnail
 * @date 2020/4/20
 */
@Component
@Slf4j
public class TagDaoImpl implements TagDao {

    private TagMapper tagMapper;

    @Autowired
    public TagDaoImpl(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Override
    public void createNewTag(TagPersistent newTag) {
        tagMapper.insertTag(newTag);
        log.debug("插入的tag in persistent：{}", newTag);
    }

    @Override
    public void updateTag(TagPersistent tag) {
        tagMapper.updateTag(tag);
    }

    @Override
    public TagPersistent getTagByDescriptionAndUserId(String description, long userId) {
        TagPersistent tag = tagMapper.getTagByDescriptionAndUserId(description, userId);
        log.debug("tag in persistent:{}", tag);
        return tag;
    }

    @Override
    public TagPersistent getTagByTagId(long tagId) {
        TagPersistent tag = tagMapper.getTagByTagId(tagId);
        log.debug("tag in persistent:{}", tag);
        return tag;
    }

    @Override
    public List<TagPersistent> getTagListByIds(List<Long> ids) {
        return tagMapper.getTagListByIds(ids);
    }
}
