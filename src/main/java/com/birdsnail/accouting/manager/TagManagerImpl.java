package com.birdsnail.accouting.manager;

import com.birdsnail.accouting.converter.p2c.TagPersistent2CommonConvert;
import com.birdsnail.accouting.dao.TagDao;
import com.birdsnail.accouting.exception.ResourceNotFoundException;
import com.birdsnail.accouting.model.common.TagCommon;
import com.birdsnail.accouting.model.persistent.TagPersistent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Optional;

/**
 * @author BirdSnail
 * @date 2020/4/20
 */
@Service
@Slf4j
public class TagManagerImpl implements TagManager {

    private TagDao tagDao;
    private TagPersistent2CommonConvert tagPersistent2CommonConvert;
    private static final int ENABLE_STATUS = 1;

    public TagManagerImpl(TagDao tagDao, TagPersistent2CommonConvert tagPersistent2CommonConvert) {
        this.tagDao = tagDao;
        this.tagPersistent2CommonConvert = tagPersistent2CommonConvert;
    }

    @Override
    public TagCommon createTag(String description, long userId) {
        // 同一个用户不能创建相同的tag
        Optional.ofNullable(tagDao.getTagByDescriptionAndUserId(description, userId))
                .ifPresent(tag -> {
                    throw new InvalidParameterException(
                            String.format("The tag with description [%s] has been created", description));
                });

        TagPersistent tagPersistent = TagPersistent.builder()
                .description(description)
                .userId(userId)
                .status(ENABLE_STATUS)
                .build();
        tagDao.createNewTag(tagPersistent);
        return tagPersistent2CommonConvert.convert(tagPersistent);
    }

    @Override
    public TagCommon getTagByTagId(long tagId) {
        return Optional.ofNullable(tagDao.getTagByTagId(tagId))
                .map(tagPersistent2CommonConvert::convert)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("The related tag id [%s] was not found.", tagId)));

    }

    @Override
    public TagCommon updateTag(TagCommon tagCommon) {
        TagPersistent updateTag = tagPersistent2CommonConvert.reverse().convert(tagCommon);
        TagPersistent tagInDb = Optional.ofNullable(tagDao.getTagByTagId(tagCommon.getId()))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("The related tag id [%s] was not found.", tagCommon.getId())
                ));
        log.debug("before update tag in persistent:{}", tagInDb);

        if (!tagCommon.getUserId().equals(tagInDb.getUserId())) {
            throw new InvalidParameterException(
                    String.format("The tag id [%s] doesn't belong to user id: %s",
                            tagCommon.getId(), tagInDb.getUserId()));
        }

        tagDao.updateTag(updateTag);
        log.debug("after update tag:{}", updateTag);
        return tagPersistent2CommonConvert.convert(updateTag);
    }
}
