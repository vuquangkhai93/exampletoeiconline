package vn.myclass.core.service;

import vn.myclass.core.dto.UserDTO;

public interface UserService {
    UserDTO isUserExist(UserDTO dto);
    UserDTO findRoleByUser(UserDTO dto);
}
