package com.birdsnail.accouting.converter.p2c;

import com.birdsnail.accouting.model.common.TagCommon;
import com.birdsnail.accouting.model.persistent.TagPersistent;
import com.google.common.base.Converter;
import org.springframework.stereotype.Component;

/**
 * @author BirdSnail
 * @date 2020/4/21
 */
@Component
public class TagPersistent2CommonConvert extends Converter<TagPersistent, TagCommon> {

    @Override
    protected TagCommon doForward(TagPersistent tagPersistent) {
        return TagCommon.builder()
                .id(tagPersistent.getId())
                .description(tagPersistent.getDescription())
                .userId(tagPersistent.getUserId())
                .status(tagPersistent.getStatus())
                .build();
    }

    @Override
    protected TagPersistent doBackward(TagCommon tagCommon) {
        return TagPersistent.builder()
                .userId(tagCommon.getUserId())
                .description(tagCommon.getDescription())
                .id(tagCommon.getId())
                .status(tagCommon.getStatus())
                .build();
    }

}
