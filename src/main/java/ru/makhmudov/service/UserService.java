package ru.makhmudov.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.makhmudov.entity.UserReg;
import ru.makhmudov.entity.enums.ERole;
import ru.makhmudov.exceptions.UserExistException;
import ru.makhmudov.payload.request.SignupRequest;
import ru.makhmudov.repository.UserRepository;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserReg createUser(SignupRequest userIn) {
        UserReg user = new UserReg();
        user.setEmail(userIn.getEmail());
        user.setName(userIn.getFirstname());
        user.setLastname(userIn.getLastname());
        user.setUsername(userIn.getUsername());

        /*Перед установкой пароля, кодируем его. Этот User сохранится в БД*/
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRoles().add(ERole.ROLE_USER);


        /*Может возникнуть ошибка, при регистрации, если под некоторыми данными пользователь уже зарегистрировался*/
        try {
            LOG.info("Saving User {}", userIn.getEmail());
            return userRepository.save(user);
        } catch (Exception e) {
            LOG.error("Error during registration. {}", e.getMessage());

            /*Кастомное исключение*/

            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials");
        }
    }
//
//    public User updateUser(UserDTO userDTO, Principal principal) {
//        User user = getUserByPrincipal(principal);
//        user.setName(userDTO.getFirstname());
//        user.setLastname(userDTO.getLastname());
//        user.setBio(userDTO.getBio());
//
//        return userRepository.save(user);
//    }
//
//    public User getCurrentUser(Principal principal) {
//        return getUserByPrincipal(principal);
//    }
//
//    private User getUserByPrincipal(Principal principal) {
//        String username = principal.getName();
//        return userRepository.findUserByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
//
//    }
//
//    public User getUserById(Long id) {
//        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
}
