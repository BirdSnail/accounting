package com.birdsnail.accouting.converter.c2s;

import com.birdsnail.accouting.model.common.RecordCommon;
import com.birdsnail.accouting.model.service.RecordView;
import com.google.common.base.Converter;
import com.google.common.collect.ImmutableList;

/**
 * @author BirdSnail
 * @date 2020/5/7
 */
public class RecordCommon2ServerConverter extends Converter<RecordCommon, RecordView> {

    private TagCommon2ServerConverter tagC2SConverter;

    public RecordCommon2ServerConverter() {
        this.tagC2SConverter = new TagCommon2ServerConverter();
    }

    @Override
    protected RecordView doForward(RecordCommon recordCommon) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected RecordCommon doBackward(RecordView recordView) {
         RecordCommon recordCommon = RecordCommon.builder()
                .userId(recordView.getUserId())
                .category(recordView.getCategory())
                .amount(recordView.getAmount())
                .note(recordView.getNote())
                .build();

        if (recordView.getTagList() != null) {
            recordCommon.setTagList(
                    ImmutableList.copyOf(tagC2SConverter.reverse().convertAll(recordView.getTagList())));
        }
        return recordCommon;
    }
}