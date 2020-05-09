package com.birdsnail.accouting.converter.p2c;

import com.birdsnail.accouting.model.common.RecordCommon;
import com.birdsnail.accouting.model.persistent.RecordPersistent;
import com.google.common.base.Converter;
import com.google.common.collect.ImmutableList;


/**
 * @author BirdSnail
 * @date 2020/5/7
 */
public class RecordPersistent2CommonConverter extends Converter<RecordPersistent, RecordCommon> {

    public static final String ENABLE = "ENABLE";
    public static final String INCOME = "INCOME";

    private TagPersistent2CommonConvert tagP2CConvert;

    public RecordPersistent2CommonConverter() {
        this.tagP2CConvert = new TagPersistent2CommonConvert();
    }


    @Override
    protected RecordCommon doForward(RecordPersistent recordPersistent) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected RecordPersistent doBackward(RecordCommon recordCommon) {
        RecordPersistent recordPersistent = RecordPersistent.builder()
                .userId(recordCommon.getUserId())
                .status(ENABLE.equals(recordCommon.getStatus()) ? 1 : 0)
                .category(INCOME.equals(recordCommon.getCategory()) ? 1 : 0)
                .amount(recordCommon.getAmount())
                .note(recordCommon.getNote())
                .build();

        if (recordCommon.getTagList() != null) {
            recordPersistent.setTagList(
                    ImmutableList.copyOf(tagP2CConvert.reverse().convertAll(recordCommon.getTagList())));
        }
        return recordPersistent;
    }
}
