package com.birdsnail.accouting.converter.c2s;

import com.birdsnail.accouting.model.common.RecordCommon;
import com.birdsnail.accouting.model.common.TagCommon;
import com.birdsnail.accouting.model.service.RecordView;
import com.birdsnail.accouting.model.service.TagView;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class RecordCommon2ServerConverterTest {

    RecordCommon2ServerConverter common2ServerConverter;

    @BeforeEach
    void setUp() {
        common2ServerConverter = new RecordCommon2ServerConverter();
    }

    @Test
    void testRecordForward() {

    }

    @Test
    void testRecordBackward() {
        BigDecimal amount = new BigDecimal("3.6");
        String category = "OUTCOME";
        Long userId = 123456L;
        String note = "hahaha";
        RecordView recordView = RecordView.builder()
                .amount(amount)
                .userId(userId)
                .category(category)
                .note(note)
                .tagList(createTagList())
                .build();
        RecordCommon recordCommon = common2ServerConverter.reverse().convert(recordView);

        assertNotNull(recordCommon);
        MatcherAssert.assertThat(recordCommon, allOf(
                hasProperty("category", is(category)),
                hasProperty("note", is(note)),
                hasProperty("amount", is(amount)),
                hasProperty("userId", is(userId)),
                hasProperty("tagList", notNullValue())
        ));
        assertEquals(recordCommon.getTagList(),
                Collections.singletonList(TagCommon.builder()
                        .id(2L)
                        .status("ENABLE")
                        .userId(123456L)
                        .description("playing")
                        .build()));
    }

    private List<TagView> createTagList() {
        return Collections.singletonList(TagView.builder()
                .id(2L)
                .status("ENABLE")
                .userId(123456L)
                .description("playing")
                .build());
    }
}