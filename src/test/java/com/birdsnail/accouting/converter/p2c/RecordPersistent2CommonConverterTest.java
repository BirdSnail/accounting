package com.birdsnail.accouting.converter.p2c;

import com.birdsnail.accouting.model.common.RecordCommon;
import com.birdsnail.accouting.model.common.TagCommon;
import com.birdsnail.accouting.model.persistent.RecordPersistent;
import com.birdsnail.accouting.model.persistent.TagPersistent;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.core.AllOf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class RecordPersistent2CommonConverterTest {

    RecordPersistent2CommonConverter recordPersistent2CommonConverter;

    @BeforeEach
    void setUp() {
        recordPersistent2CommonConverter = new RecordPersistent2CommonConverter();
    }

    @Test
    void testForward() {

    }

    @Test
    void testBackward() {
        BigDecimal amount = new BigDecimal("3.6");
        String category = "OUTCOME";
        Long userId = 123456L;
        String note = "hahaha";
        String status = "ENABLE";
        RecordCommon recordCommon = RecordCommon.builder()
                .amount(amount)
                .userId(userId)
                .category(category)
                .note(note)
                .status(status)
                .tagList(createTagList())
                .build();
        RecordPersistent recordPersistent = recordPersistent2CommonConverter.reverse().convert(recordCommon);

        assertNotNull(recordPersistent);
        MatcherAssert.assertThat(recordPersistent, AllOf.allOf(
                hasProperty("category", is(0)),
                hasProperty("note", is(note)),
                hasProperty("amount", is(amount)),
                hasProperty("status", is(1)),
                hasProperty("userId", is(userId)),
                hasProperty("tagList", notNullValue())
        ));
        assertEquals(recordPersistent.getTagList(),
                Collections.singletonList(TagPersistent.builder()
                        .id(2L)
                        .status(1)
                        .userId(123456L)
                        .description("playing")
                        .build()));
    }

    private List<TagCommon> createTagList() {
        return Collections.singletonList(TagCommon.builder()
                .id(2L)
                .status("ENABLE")
                .userId(123456L)
                .description("playing")
                .build());
    }
}