package net.ddns.tvan11.plants.resource;

import net.ddns.tvan11.plants.domain.Plant;
import net.ddns.tvan11.plants.domain.dto.PlantDto;
import net.ddns.tvan11.plants.repository.PlantRepository;
import net.ddns.tvan11.plants.service.PlantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static net.ddns.tvan11.plants.constant.FileConstant.HOME_FOLDER;
import static net.ddns.tvan11.plants.constant.FileConstant.IMAGE_FOLDER;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@RestController
@RequestMapping("/plant")
public class PlantResource {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PlantRepository plantRepository;

    @Autowired
    PlantService plantService;

    @GetMapping("/list")
    public ResponseEntity<List<Plant>> getPlants() {
        return new ResponseEntity<>(plantRepository.findAll(), OK);
    }

//
    @PostMapping("/add")
    public ResponseEntity<PlantDto> addPlant(@RequestParam("plant") String plant, @RequestParam("plantHeaderImage") MultipartFile plantHeaderImage, @RequestParam("plantProfileImage") MultipartFile plantProfileImage) throws IOException {
        return new ResponseEntity<>(plantService.addPlant(plant, plantHeaderImage, plantProfileImage), OK);
    }


}
