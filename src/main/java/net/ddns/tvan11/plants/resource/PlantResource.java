package net.ddns.tvan11.plants.resource;

import net.ddns.tvan11.plants.domain.Plant;
import net.ddns.tvan11.plants.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/plant")
public class PlantResource {

    @Autowired
    PlantRepository plantRepository;

    @GetMapping("/list")
    public ResponseEntity<List<Plant>> getPlants() {
        return new ResponseEntity<>(plantRepository.findAll(), HttpStatus.OK);
    }
}
