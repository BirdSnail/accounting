package com.birdsnail.accouting.converter.c2s;

import com.birdsnail.accouting.model.common.TagCommon;
import com.birdsnail.accouting.model.service.TagView;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class TagCommon2ServerConverterTest {

    TagCommon2ServerConverter tagCommon2ServerConverter;
    static final String ENABLE = "ENABLE";

    @BeforeEach
    void setup() {
        tagCommon2ServerConverter = new TagCommon2ServerConverter();
    }

    @Test
    void testDoBackward() {
        long id = 1L;
        String description = "playing";
        long userId = 1L;

        final TagView tagView = TagView.builder()
                .id(id)
                .description(description)
                .userId(userId)
                .build();

        final TagCommon result = tagCommon2ServerConverter.reverse().convert(tagView);

        MatcherAssert.assertThat(result, allOf(
                is(notNullValue()),
                hasProperty("id", is(id)),
                hasProperty("description", is(description)),
                hasProperty("status", is(1)),
                hasProperty("userId", is(userId))
        ));
    }

}