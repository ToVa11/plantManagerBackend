package net.ddns.tvan11.plants.resource;

import net.ddns.tvan11.plants.domain.User;
import net.ddns.tvan11.plants.service.JwtService;
import net.ddns.tvan11.plants.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user, @RequestHeader("Authorization") String authHeader){
        String authUser = jwtService.getUsernameFromToken(authHeader);
        if(authUser.equals(user.getUsername())) {
            User updatedUser = userService.update(user);
            return new ResponseEntity<>(updatedUser, OK);
        }
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/updateProfileImage")
    public ResponseEntity<User> updateProfileImage(@RequestParam("profileImage") MultipartFile profileImage, @RequestParam("username") String username ,@RequestHeader("Authorization") String authHeader) throws IOException {
        String authUser = jwtService.getUsernameFromToken(authHeader);
        if(authUser.equals(username)) {
            User updatedUser = userService.updateProfileImage(username, profileImage);
            return new ResponseEntity<>(updatedUser, OK);
        }
        return new ResponseEntity<>(new User(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/authorities/update")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<User> updateRolesAndAuthorities(@RequestBody User user) {
        return new ResponseEntity<>(user, OK);
    }
}
