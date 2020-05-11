package com.birdsnail.accouting.dao;

import com.birdsnail.accouting.dao.mapper.RecordTagMappingMapper;
import com.birdsnail.accouting.model.persistent.RecordTagMapping;
import com.birdsnail.accouting.model.persistent.TagPersistent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author BirdSnail
 * @date 2020/5/7
 */
@Repository
@Slf4j
public class RecordTagMappingDaoImpl implements RecordTagMappingDao {

    private RecordTagMappingMapper recordTagMappingMapper;

    @Autowired
    public RecordTagMappingDaoImpl(RecordTagMappingMapper recordTagMappingMapper) {
        this.recordTagMappingMapper = recordTagMappingMapper;
    }

    @Override
    public void batchInsert(Long recordId, List<TagPersistent> tagPersistentList) {
        List<RecordTagMapping> recordTagMappingList = tagPersistentList.stream()
                .map(tag -> RecordTagMapping.builder()
                        .recordId(recordId)
                        .tagId(tag.getId())
                        .status(tag.getStatus())
                        .build())
                .collect(Collectors.toList());

        int rowsCount = recordTagMappingMapper.batchInsert(recordTagMappingList);
        log.debug("batch insert rows count:{}", rowsCount);
    }

    @Override
    public void deleteRecordTagMappingByRecordId(Long recordId) {
        int rows = recordTagMappingMapper.deleteRecordTagMappingByRecordId(recordId);
        log.debug("The affect rows for deleting: {}", rows);
    }
}
