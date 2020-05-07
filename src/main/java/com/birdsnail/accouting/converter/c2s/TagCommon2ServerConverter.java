package com.birdsnail.accouting.converter.c2s;


import com.birdsnail.accouting.controller.TagController;
import com.birdsnail.accouting.model.common.TagCommon;
import com.birdsnail.accouting.model.service.TagView;
import com.google.common.base.Converter;
import org.springframework.stereotype.Component;

/**
 * @author BirdSnail
 * @date 2020/4/20
 */
@Component
public class TagCommon2ServerConverter extends Converter<TagCommon, TagView> {

    @Override
    protected TagView doForward(TagCommon tagCommon) {
        return TagView.builder()
                .id(tagCommon.getId())
                .description(tagCommon.getDescription())
                .userId(tagCommon.getUserId())
                .status(tagCommon.getStatus() == 1 ? TagController.ENABLE : TagController.DISABLE)
                .build();
    }

    @Override
    protected TagCommon doBackward(TagView tagView) {
        final TagCommon tagCommon = TagCommon.builder()
                .id(tagView.getId())
                .userId(tagView.getUserId())
                .status(1)
                .description(tagView.getDescription())
                .build();

        if (tagView.getStatus() != null) {
            tagCommon.setStatus(tagView.getStatus().equals(TagController.DISABLE) ? 0 : 1);
        }
        return tagCommon;
    }
}
