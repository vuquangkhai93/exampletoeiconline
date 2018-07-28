package vn.myclass.core.utils;

import vn.myclass.core.dto.ListenGuidelineDTO;
import vn.myclass.core.persistence.entity.ListenGuidelineEntity;

public class ListenGuidelineBeanUtil {
    public static ListenGuidelineDTO entity2Dto(ListenGuidelineEntity entity) {
        ListenGuidelineDTO dto = new ListenGuidelineDTO();
        dto.setListenGuidelineId(entity.getListenGuidelineId());
        dto.setContent(entity.getContent());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreatedDay(entity.getCreatedDay());
        dto.setModifiedDay(entity.getModifiedDay());
        return dto;
    }
    public static ListenGuidelineEntity dto2Entity(ListenGuidelineDTO dto) {
        ListenGuidelineEntity entity = new ListenGuidelineEntity();
        entity.setListenGuidelineId(dto.getListenGuidelineId());
        entity.setContent(dto.getContent());
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreatedDay(dto.getCreatedDay());
        entity.setModifiedDay(dto.getModifiedDay());
        return entity;
    }
}
