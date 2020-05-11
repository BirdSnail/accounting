package com.birdsnail.accouting.controller;

import com.birdsnail.accouting.converter.c2s.RecordCommon2ServerConverter;
import com.birdsnail.accouting.manager.RecordManager;
import com.birdsnail.accouting.model.common.RecordCommon;
import com.birdsnail.accouting.model.service.RecordView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.Objects;

import static com.birdsnail.accouting.converter.p2c.RecordPersistent2CommonConverter.ENABLE;

/**
 * 实现的功能:
 * 1. 创建一条新的record。
 * 2. 根据recordId查询。
 * 3. 对已有的record进行更新。
 *
 * @author BirdSnail
 * @date 2020/5/7
 */
@RestController
@RequestMapping("v1.0/records")
public class RecordController {

    private RecordManager recordManager;
    private RecordCommon2ServerConverter recordC2S_Converter;

    @Autowired
    public RecordController(RecordManager recordManager) {
        this.recordManager = recordManager;
        recordC2S_Converter = new RecordCommon2ServerConverter();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public RecordView createRecord(@RequestBody RecordView record) {
        if (checkRecord(record)) {
            throw new InvalidParameterException("要被创建的record是无效的");
        }

        RecordCommon recordCommon = recordC2S_Converter.reverse().convert(record);
        if (recordCommon == null) {
            throw new InvalidParameterException("Record不符合要求");
        }
        recordCommon.setStatus(ENABLE);
        RecordCommon resource = recordManager.createRecord(recordCommon);
        return recordC2S_Converter.convert(resource);
    }

    @GetMapping("/{id}")
    public RecordView getRecordByRecordId(@PathVariable("id") Long recordId) {
        Objects.requireNonNull(recordId);
        if (recordId <= 0) {
            throw new InvalidParameterException(String.format("The record id:[%s] is invalid.", recordId));
        }

        RecordCommon recordCommon = recordManager.getRecordByRecordId(recordId);
        return recordC2S_Converter.convert(recordCommon);
    }

    @PutMapping("/{id}")
    public RecordView updateRecord(@PathVariable("id") Long recordId, @RequestBody RecordView record) {
        if (record == null || recordId <= 0) {
            throw new InvalidParameterException("The record id must not be empty and positive.");
        }

        if (record.getUserId() == null || record.getUserId() <= 0) {
            throw new InvalidParameterException("The user id is empty or invalid.");
        }

        record.setId(recordId);
        RecordCommon recordCommon = recordC2S_Converter.reverse().convert(record);
        RecordCommon result = recordManager.updateRecord(recordCommon);
        return recordC2S_Converter.convert(result);
    }

    private boolean checkRecord(RecordView record) {
        return record.getAmount() == null
                || record.getUserId() == null
                || record.getCategory() == null
                || record.getTagList() == null;
    }
}
