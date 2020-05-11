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
    public static final String DISABLE = "DISABLE";
    public static final String INCOME = "INCOME";
    public static final String OUTCOME = "OUTCOME";

    private TagPersistent2CommonConvert tagP2C_Convert;

    public RecordPersistent2CommonConverter() {
        this.tagP2C_Convert = new TagPersistent2CommonConvert();
    }


    @Override
    protected RecordCommon doForward(RecordPersistent recordPersistent) {
        final RecordCommon recordCommon = RecordCommon.builder()
                .id(recordPersistent.getId())
                .status(recordPersistent.getStatus() == 1 ? ENABLE : DISABLE)
                .userId(recordPersistent.getUserId())
                .category(recordPersistent.getCategory() == 1 ? INCOME : OUTCOME)
                .amount(recordPersistent.getAmount())
                .note(recordPersistent.getNote())
                .build();
        if (recordPersistent.getTagList() != null) {
            recordCommon.setTagList(ImmutableList.copyOf(tagP2C_Convert.convertAll(recordPersistent.getTagList())));
        }
        return recordCommon;
    }

    @Override
    protected RecordPersistent doBackward(RecordCommon recordCommon) {
        RecordPersistent recordPersistent = RecordPersistent.builder()
                .id(recordCommon.getId())
                .userId(recordCommon.getUserId())
                .category(INCOME.equals(recordCommon.getCategory()) ? 1 : 0)
                .amount(recordCommon.getAmount())
                .note(recordCommon.getNote())
                .build();

        if (recordCommon.getStatus() != null) {
            recordPersistent.setStatus(ENABLE.equals(recordCommon.getStatus()) ? 1 : 0);
        }

        if (recordCommon.getTagList() != null) {
            recordPersistent.setTagList(
                    ImmutableList.copyOf(tagP2C_Convert.reverse().convertAll(recordCommon.getTagList())));
        }
        return recordPersistent;
    }
}
