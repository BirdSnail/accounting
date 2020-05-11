package com.birdsnail.accouting.dao;

import com.birdsnail.accouting.model.common.RecordCommon;
import com.birdsnail.accouting.model.persistent.RecordPersistent;

/**
 * interface of record dao.
 * @author BirdSnail
 */
public interface RecordDao {

    /**
     * 插入一条record记录。
     * @param record {@link RecordPersistent}
     */
    void insertRecord(RecordPersistent record);

    /**
     * 查询record
     * @param recordId record id
     * @return {@link RecordCommon}
     */
    RecordPersistent getRecordByRecordId(Long recordId);

    /**
     * 更新Record
     * @param updateRecord {@link RecordPersistent}
     */
    void updateRecord(RecordPersistent updateRecord);

}
