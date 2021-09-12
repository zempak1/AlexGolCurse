package ru.makhmudov.facade;


import org.springframework.stereotype.Component;
import ru.makhmudov.dto.UserDTO;
import ru.makhmudov.entity.UserReg;

@Component
public class UserFacade {

    public UserDTO userToUserDTO(UserReg user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstname(user.getName());
        userDTO.setLastname(user.getLastname());
        userDTO.setUsername(user.getUsername());
        userDTO.setBio(user.getBio());
        return userDTO;
    }

}
