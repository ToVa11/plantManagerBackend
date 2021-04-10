package net.ddns.tvan11.plants.resource;

import net.ddns.tvan11.plants.domain.Plant;
import net.ddns.tvan11.plants.domain.dto.UserOwnListDTO;
import net.ddns.tvan11.plants.domain.dto.UserWishListDTO;
import net.ddns.tvan11.plants.service.UserService;
import net.ddns.tvan11.plants.utility.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ownlist")
public class OwnListResource {

    @Autowired
    UserService userService;

    @Autowired
    JWTTokenProvider jwtTokenProvider;

    @GetMapping("get")
    public ResponseEntity<UserOwnListDTO> getOwnList(@RequestHeader(name="Authorization") String authorizationHeader) {
        String username = this.getUsernameFromToken(authorizationHeader);
        return new ResponseEntity<>(userService.getOwnList(username), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<UserOwnListDTO> addPlantToOwnList(@RequestBody Plant plant, @RequestHeader(name="Authorization") String authorizationHeader) throws Exception {
        String username = this.getUsernameFromToken(authorizationHeader);
        return new ResponseEntity<>(userService.addPlantToOwnList(username,plant), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<UserOwnListDTO> removePlantFromWishList(@RequestBody Plant plant, @RequestHeader(name="Authorization") String authorizationHeader) throws Exception {
        String username = this.getUsernameFromToken(authorizationHeader);
        return new ResponseEntity<>(userService.removePlantFromOwnList(username, plant), HttpStatus.OK);
    }

    private String getUsernameFromToken(String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length());
        return jwtTokenProvider.getSubject(token);
    }
}
