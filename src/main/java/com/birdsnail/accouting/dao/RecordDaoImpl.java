package com.birdsnail.accouting.dao;

import com.birdsnail.accouting.dao.mapper.RecordMapper;
import com.birdsnail.accouting.model.persistent.RecordPersistent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author BirdSnail
 * @date 2020/5/7
 */
@Repository
@Slf4j
public class RecordDaoImpl implements RecordDao{

    private RecordMapper recordMapper;

    @Autowired
    public RecordDaoImpl(RecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }

    @Override
    public void insertRecord(RecordPersistent record) {
        record.setStatus(1);
        log.debug("Record in RecordDaoImpl: {}", record);
        recordMapper.insertRecord(record);
        log.debug("Record in RecordDaoImpl after inserting: {}", record);
    }

    @Override
    public RecordPersistent getRecordByRecordId(Long recordId) {
        if (recordId > 0) {
            return recordMapper.getRecordById(recordId);
        }
        return null;
    }
}
