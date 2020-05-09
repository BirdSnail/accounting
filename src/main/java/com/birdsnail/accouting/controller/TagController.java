package com.birdsnail.accouting.controller;

import com.birdsnail.accouting.converter.c2s.TagCommon2ServerConverter;
import com.birdsnail.accouting.manager.TagManager;
import com.birdsnail.accouting.manager.UserInfoManager;
import com.birdsnail.accouting.model.common.TagCommon;
import com.birdsnail.accouting.model.service.TagView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;

/**
 * 标签的控制访问层
 * - 新增标签
 * - 查询标签
 * - 编辑已有标签
 * - 删除已有标签
 *
 * @author BirdSnail
 * @date 2020/4/13
 */
@Slf4j
@RestController
@RequestMapping("v1.0/tags")
public class TagController {

    public static final String DISABLE = "DISABLE";
    public static final String ENABLE = "ENABLE";

    private TagManager tagManager;
    private UserInfoManager userInfoManager;
    private TagCommon2ServerConverter tagCommon2ServerConverter;

    @Autowired
    public TagController(TagManager tagManager,
                         UserInfoManager userInfoManager,
                         TagCommon2ServerConverter tagCommon2ServerConverter) {
        this.tagManager = tagManager;
        this.userInfoManager = userInfoManager;
        this.tagCommon2ServerConverter = tagCommon2ServerConverter;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public TagView createTag(@RequestBody TagView tag) {
        if (tag.getDescription() == null || tag.getDescription().isEmpty()) {
            throw new InvalidParameterException("The description must be not null or empty.");
        }

        if (tag.getUserId() <= 0) {
            throw new InvalidParameterException(String.format("The user id [%s] was invalid", tag.getUserId()));
        }

        TagCommon result = tagManager.createTag(tag.getDescription(), tag.getUserId());
        result.setStatus(ENABLE);
        log.debug("创建的tag in common:{}", result);
        return tagCommon2ServerConverter.convert(result);
    }

    @GetMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public TagView getTagByTagId(@PathVariable("id") long tagId) {
        TagCommon tagInCommon = tagManager.getTagByTagId(tagId);
        log.debug("查询到的tag in common:{}", tagInCommon);
        return tagCommon2ServerConverter.convert(tagInCommon);
    }

    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public TagView updateTag(@PathVariable("id") long tagId, @RequestBody TagView tagView) {
        String status = tagView.getStatus();

        // 避免无效的状态更新
        if (status != null && !DISABLE.equals(status) && !ENABLE.equals(status)) {
            throw new InvalidParameterException(
                    String.format("The tag status [%s] to update is invalid status.", status)
            );
        }

        if (tagView.getUserId() <= 0) {
            throw new InvalidParameterException(
                    String.format("该tag属入的用户id[%s]无效或者为空，请传入正确的userId.", tagView.getUserId()));
        }

        userInfoManager.getUserInfoByUserId(tagView.getUserId());

        TagCommon tagCommon = tagCommon2ServerConverter.reverse().convert(tagView);
        tagCommon.setId(tagId);
        log.debug("要更新的tag in common状态：{}", tagCommon);
        TagCommon resource = tagManager.updateTag(tagCommon);
        return tagCommon2ServerConverter.convert(resource);
    }
}
