package com.birdsnail.accouting.manager;

import com.birdsnail.accouting.model.common.RecordCommon;

/**
 * @author BirdSnail
 * @date 2020/5/7
 */
public interface RecordManager {


    /**
     * create a new record.
     * @param record 前端传入的Record
     * @return record in common
     */
    RecordCommon createRecord(RecordCommon record);

    /**
     * 对指定recordId进行查找并返回
     * @param recordId record id
     * @return record in common
     */
    RecordCommon getRecordByRecordId(Long recordId);

}
