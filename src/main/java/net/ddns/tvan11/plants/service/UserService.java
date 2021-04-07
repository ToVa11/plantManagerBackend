package net.ddns.tvan11.plants.service;

import net.ddns.tvan11.plants.domain.Plant;
import net.ddns.tvan11.plants.domain.User;
import net.ddns.tvan11.plants.domain.UserPrincipal;
import net.ddns.tvan11.plants.domain.dto.UserWishListDTO;
import net.ddns.tvan11.plants.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserPrincipal(userRepository.findUserByUsername(username));
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
}
