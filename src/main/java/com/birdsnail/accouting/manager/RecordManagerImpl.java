package com.birdsnail.accouting.manager;

import com.birdsnail.accouting.converter.p2c.RecordPersistent2CommonConverter;
import com.birdsnail.accouting.dao.RecordDao;
import com.birdsnail.accouting.dao.RecordTagMappingDao;
import com.birdsnail.accouting.dao.TagDao;
import com.birdsnail.accouting.exception.ResourceNotFoundException;
import com.birdsnail.accouting.model.common.RecordCommon;
import com.birdsnail.accouting.model.persistent.RecordPersistent;
import com.birdsnail.accouting.model.persistent.TagPersistent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author BirdSnail
 * @date 2020/5/7
 */
@Service
public class RecordManagerImpl implements RecordManager {

    private RecordDao recordDao;
    private RecordTagMappingDao recordTagMappingDao;
    private TagDao tagDao;
    private RecordPersistent2CommonConverter p2cConverter;


    @Autowired
    public RecordManagerImpl(final RecordDao recordDao,
                             final TagDao tagDao,
                             final RecordTagMappingDao recordTagMappingDao) {
        this.recordDao = recordDao;
        this.tagDao = tagDao;
        this.recordTagMappingDao = recordTagMappingDao;
        this.p2cConverter = new RecordPersistent2CommonConverter();
    }

    @Override
    public RecordCommon createRecord(RecordCommon record) {
        RecordPersistent newRecord = p2cConverter.reverse().convert(record);

        // check tag
        List<Long> tagIds = Objects.requireNonNull(newRecord)
                .getTagList()
                .stream()
                .map(TagPersistent::getId)
                .collect(Collectors.toList());
        List<TagPersistent> tagPersistentList = tagDao.getTagListByIds(tagIds);
        if (tagPersistentList.isEmpty()) {
            throw new InvalidParameterException(
                    String.format("The tag list %s are not existed.", tagIds));
        }

        tagPersistentList.forEach(tagPersistent -> {
            if (!tagPersistent.getUserId().equals(record.getUserId())) {
                throw new InvalidParameterException(
                        String.format("The tag:[id -> %s, description -> %s] is not matched for user.",
                                tagPersistent.getId(),
                                tagPersistent.getDescription()));
            }
        });

        // TODO :两次插入应该放在一个事务中
        recordDao.insertRecord(newRecord);
        // 将该条记录拥有的标签批量插入
        recordTagMappingDao.batchInsert(newRecord.getId(), tagPersistentList);
        return getRecordByRecordId(newRecord.getId());
    }

    @Override
    public RecordCommon getRecordByRecordId(Long recordId) {
        return Optional.ofNullable(recordDao.getRecordByRecordId(recordId))
                .map(record -> p2cConverter.convert(record))
                .orElseThrow(() -> new ResourceNotFoundException("The record was not found."));
    }

    @Override
    public RecordCommon updateRecord(RecordCommon recordCommon) {
        RecordPersistent existsRecord = Optional.ofNullable(recordDao.getRecordByRecordId(recordCommon.getId()))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("The record id is:[%s] was not found.", recordCommon.getId())));

        if (!recordCommon.getUserId().equals(existsRecord.getUserId())) {
            throw new InvalidParameterException(
                    String.format("The record id:[%s] doesn't belong to user id:[%s]",
                            recordCommon.getId(), recordCommon.getUserId()));
        }

        RecordPersistent updateRecord = p2cConverter.reverse().convert(recordCommon);
        assert updateRecord != null;
        if (updateTagIfNecessary(updateRecord, existsRecord)) {
            // check tag is invalid
            List<Long> tagIdList = updateRecord.getTagList()
                    .stream()
                    .map(TagPersistent::getId)
                    .collect(Collectors.toList());

            List<TagPersistent> tagList = tagDao.getTagListByIds(tagIdList);
            if (tagIdList.isEmpty()) {
                throw new InvalidParameterException(String.format("The tag list %s is not exists", tagIdList));
            }
            tagList.stream()
                    .filter(tag -> !tag.getUserId().equals(recordCommon.getUserId()))
                    .findAny()
                    .ifPresent(tag -> {
                        throw new InvalidParameterException(
                                String.format("The related tag id:[%s] doesn't belong to user id:[%s]",
                                        tag.getId(), recordCommon.getUserId()));
                    });

            // TODO 两次操作应该具有原子性
            recordTagMappingDao.deleteRecordTagMappingByRecordId(recordCommon.getId());
            recordTagMappingDao.batchInsert(recordCommon.getId(), tagList);
        }

        recordDao.updateRecord(updateRecord);
        return getRecordByRecordId(recordCommon.getId());
    }

    private boolean updateTagIfNecessary(RecordPersistent updateRecord, RecordPersistent existsRecord) {
        return updateRecord.getTagList() != null
                && !updateRecord.getTagList().equals(existsRecord.getTagList());
    }
}
