package vn.myclass.core.utils;

import vn.myclass.core.persistence.entity.RoleEntity;

public class RoleBeanUtil {
    public static RoleDTO2 entity2Dto(RoleEntity entity){
        RoleDTO2 dto = new RoleDTO2();
        dto.setRoleId(entity.getRoleId());
        dto.setName(entity.getName());
        return dto;
    }

    public static RoleEntity dto2Entity(RoleDTO2 dto){
        RoleEntity entity = new RoleEntity();
        entity.setRoleId(dto.getRoleId());
        entity.setName(dto.getName());
        return entity;
    }
}
