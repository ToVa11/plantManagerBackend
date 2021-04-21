package net.ddns.tvan11.plants.service;

import net.ddns.tvan11.plants.domain.Plant;
import net.ddns.tvan11.plants.domain.User;
import net.ddns.tvan11.plants.domain.UserPrincipal;
import net.ddns.tvan11.plants.domain.dto.UserOwnListDTO;
import net.ddns.tvan11.plants.domain.dto.UserWishListDTO;
import net.ddns.tvan11.plants.enumeration.Role;
import net.ddns.tvan11.plants.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static net.ddns.tvan11.plants.constant.FileConstant.*;
import static net.ddns.tvan11.plants.enumeration.Role.ROLE_SUPER_ADMIN;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserPrincipal(userRepository.findUserByUsername(username));
    }

    public User update(User user) {
        User userToUpdate = userRepository.findUserByUsername(user.getUsername());
        this.checkAndUpdateFields(user, userToUpdate);
        return userRepository.save(userToUpdate);
    }

    public UserWishListDTO addPlantToWishList(String username, Plant plant) throws Exception {
        User user = userRepository.findUserByUsername(username);

        if(user.getId() <= 0) {
            throw new Exception("User not found");
        }
        user.addPlantToWishList(plant);
        userRepository.save(user);

        return new UserWishListDTO(user.getId(), user.getWishList().stream().map(Plant::getId).collect(Collectors.toList()));
    }

    public UserWishListDTO removePlantFromWishList(String username, Plant plant) throws Exception {
        User user = userRepository.findUserByUsername(username);

        if(user.getId() <= 0) {
            throw new Exception("User not found");
        }
        user.removePlantFromWishList(plant);
        userRepository.save(user);

        return new UserWishListDTO(user.getId(), user.getWishList().stream().map(Plant::getId).collect(Collectors.toList()));
    }

    public UserWishListDTO getWishlist(String username) {
        User user = userRepository.findUserByUsername(username);
        return new UserWishListDTO(user.getId(), user.getWishList().stream().map(Plant::getId).collect(Collectors.toList()));
    }

    public UserOwnListDTO getOwnList(String username) {
        User user = userRepository.findUserByUsername(username);
        return new UserOwnListDTO(user.getId(), user.getOwnList().stream().map(Plant::getId).collect(Collectors.toList()));
    }

    public UserOwnListDTO addPlantToOwnList(String username, Plant plant) throws Exception {
        User user = userRepository.findUserByUsername(username);

        if(user.getId() <= 0) {
            throw new Exception("User not found");
        }
        user.addPlantToOwnList(plant);
        userRepository.save(user);

        return new UserOwnListDTO(user.getId(), user.getOwnList().stream().map(Plant::getId).collect(Collectors.toList()));
    }

    public UserOwnListDTO removePlantFromOwnList(String username, Plant plant) throws Exception {
        User user = userRepository.findUserByUsername(username);

        if(user.getId() <= 0) {
            throw new Exception("User not found");
        }
        user.removePlantFromOwnList(plant);
        userRepository.save(user);

        return new UserOwnListDTO(user.getId(), user.getOwnList().stream().map(Plant::getId).collect(Collectors.toList()));
    }

    public User updateProfileImage(String username, MultipartFile profileImage) throws IOException {
        String imageName = imageService.copyProfileImage(username,profileImage);
        User user = userRepository.findUserByUsername(username);
        user.setProfileImageUrl(this.getProfileImageUrl(username, imageName));
        userRepository.save(user);
        return user;
    }

    public User register(User user) {
        setAuthorities(user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateRolesAndAuthorities(User user) {
        User originalUser = userRepository.findUserByUsername(user.getUsername());
        checkAndUpdateRolesAndAuthorities(user, originalUser);
        return originalUser;
    }

    private void checkAndUpdateFields(User userWithUpdatedFields, User originalUser) {
        if(userWithUpdatedFields.getEmail() != null && !userWithUpdatedFields.getEmail().equals(originalUser.getEmail())) {
            originalUser.setEmail(userWithUpdatedFields.getEmail());
        }
        if(userWithUpdatedFields.getFirstName() != null && !userWithUpdatedFields.getFirstName().equals(originalUser.getFirstName())) {
            originalUser.setFirstName(userWithUpdatedFields.getFirstName());
        }
        if(userWithUpdatedFields.getLastName() != null && !userWithUpdatedFields.getLastName().equals(originalUser.getLastName())) {
            originalUser.setLastName(userWithUpdatedFields.getLastName());
        }
        if(userWithUpdatedFields.getPassword() != null) {
            originalUser.setPassword(bCryptPasswordEncoder.encode(userWithUpdatedFields.getPassword()));
        }

    }

    private void checkAndUpdateRolesAndAuthorities(User userWithUpdatedFields, User originalUser) {
        if(userWithUpdatedFields.getRoles() != null && userWithUpdatedFields.getRoles() != originalUser.getRoles()) {
            if(Arrays.stream(userWithUpdatedFields.getRoles()).anyMatch(role -> role.equals(Role.ROLE_SUPER_ADMIN.toString()))) {
                originalUser.setRoles(new String[]{ROLE_SUPER_ADMIN.name()});
                originalUser.setAuthorities(ROLE_SUPER_ADMIN.getAuthorities());
            }
            else {
                originalUser.setRoles(userWithUpdatedFields.getRoles());
                ArrayList<String> authorities = new ArrayList<>();
                for (String role:
                        originalUser.getRoles()
                ) {
                    authorities.addAll(Arrays.asList(Role.valueOf(role).getAuthorities()));
                }
                originalUser.setAuthorities(authorities.toArray(new String[0]));
            }
        }
    }

    private void setAuthorities(User user) {
        ArrayList<String> authorities = new ArrayList<>();
        for(String role: user.getRoles()) {
            authorities.addAll(Arrays.asList(Role.valueOf(role).getAuthorities()));
        }
        user.setAuthorities(authorities.toArray(new String[0]));
    }

    private String getProfileImageUrl(String username, String imageName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(
                IMAGE_FOLDER +  PROFILE_IMAGES_FOLDER  + FORWARD_SLASH +
                        username + FORWARD_SLASH + imageName
        ).toUriString();
    }
}
