package com.birdsnail.accouting.manager;

import com.birdsnail.accouting.converter.p2c.TagPersistent2CommonConvert;
import com.birdsnail.accouting.dao.TagDao;
import com.birdsnail.accouting.model.common.TagCommon;
import com.birdsnail.accouting.model.persistent.TagPersistent;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TagManagerImplTest {

    TagManager tagManager;
    @Mock
    TagDao tagDao;

    @BeforeEach
    void setup() {
        tagManager = new TagManagerImpl(tagDao, new TagPersistent2CommonConvert());
    }

    @Test
    void createTag() {
    }

    @Test
    void getTagByTagId() {
        long id = 1;
        String description = "eat";
        int status = 1;
        long userId = 1;
        LocalDateTime createTime = LocalDateTime.now();

        TagPersistent tagPersistent = TagPersistent.builder()
                .id(id)
                .description(description)
                .userId(userId)
                .createTime(createTime)
                .status(status)
                .build();

        Mockito.doReturn(tagPersistent).when(tagDao).getTagByTagId(id);
        final TagCommon result = tagManager.getTagByTagId(id);
        MatcherAssert.assertThat(result, allOf(
                is(notNullValue()),
                hasProperty("id", is(id)),
                hasProperty("userId", is(userId)),
                hasProperty("description", is(description)),
                hasProperty("status", is(status))
        ));
        verify(tagDao).getTagByTagId(id);

    }

    @Test
    void updateTag() {
    }
}