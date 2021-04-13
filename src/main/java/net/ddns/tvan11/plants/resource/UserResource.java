package net.ddns.tvan11.plants.resource;

import net.ddns.tvan11.plants.domain.User;
import net.ddns.tvan11.plants.service.JwtService;
import net.ddns.tvan11.plants.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user, @RequestHeader("Authorization") String authHeader){
        String authUser = jwtService.getUsernameFromToken(authHeader);
        if(authUser.equals(user.getUsername())) {
            User updatedUser = userService.update(user);
            return new ResponseEntity<>(updatedUser, OK);
        }
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/authorities/update")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<User> updateRolesAndAuthorities(@RequestBody User user) {
        return new ResponseEntity<>(user, OK);
    }
}
