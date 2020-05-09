package com.birdsnail.accouting.controller;

import com.birdsnail.accouting.converter.c2s.RecordCommon2ServerConverter;
import com.birdsnail.accouting.manager.RecordManager;
import com.birdsnail.accouting.model.common.RecordCommon;
import com.birdsnail.accouting.model.service.RecordView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.Objects;

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
@RequestMapping("v1.0/record")
public class RecordController {

    private RecordManager recordManager;
    private RecordCommon2ServerConverter c2sConverter;

    @Autowired
    public RecordController(RecordManager recordManager) {
        this.recordManager = recordManager;
        c2sConverter = new RecordCommon2ServerConverter();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public RecordView createRecord(@RequestBody RecordView record) {
        if (checkRecord(record)) {
            throw new InvalidParameterException("要被创建的record是无效的");
        }

        RecordCommon recordCommon = c2sConverter.reverse().convert(record);
        RecordCommon resource = recordManager.createRecord(recordCommon);
        return c2sConverter.convert(resource);
    }

    @GetMapping("/{id}")
    public RecordView getRecordByRecordId(@PathVariable("id") Long recordId) {
        Objects.requireNonNull(recordId);
        if (recordId <= 0) {
            throw new InvalidParameterException(String.format("The record id:[%s] is invalid.", recordId));
        }

        RecordCommon recordCommon = recordManager.getRecordByRecordId(recordId);
        return c2sConverter.convert(recordCommon);
    }

    @PutMapping
    public RecordView updateRecord(@RequestBody RecordView record) {
        throw new UnsupportedOperationException();
    }

    private boolean checkRecord(RecordView record) {
        return record.getAmount() == null
                || record.getUserId() == null
                || record.getCategory() == null
                || record.getTagList() == null;
    }
}
