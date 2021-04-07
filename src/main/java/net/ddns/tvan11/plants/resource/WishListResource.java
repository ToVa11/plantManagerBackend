package net.ddns.tvan11.plants.resource;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import net.ddns.tvan11.plants.domain.Plant;
import net.ddns.tvan11.plants.domain.dto.UserWishListDTO;
import net.ddns.tvan11.plants.service.UserService;
import net.ddns.tvan11.plants.utility.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/wishlist")
public class WishListResource {

    @Autowired
    UserService userService;

    @Autowired
    JWTTokenProvider jwtTokenProvider;

    @PostMapping("/add")
    public ResponseEntity<UserWishListDTO> addPlantToWishList(@RequestBody Plant plant, @RequestHeader(name="Authorization") String authorizationHeader) throws Exception {
        String username = this.getUsernameFromToken(authorizationHeader);
        return new ResponseEntity<>(userService.addPlantToWishList(username,plant), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<UserWishListDTO> removePlantFromWishList(@RequestBody Plant plant, @RequestHeader(name="Authorization") String authorizationHeader) throws Exception {
        String username = this.getUsernameFromToken(authorizationHeader);
        return new ResponseEntity<>(userService.removePlantFromWishList(username, plant), HttpStatus.OK);
    }

    private String getUsernameFromToken(String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length());
        return jwtTokenProvider.getSubject(token);
    }
}
