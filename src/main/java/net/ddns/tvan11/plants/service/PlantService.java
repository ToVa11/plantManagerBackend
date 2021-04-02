package net.ddns.tvan11.plants.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.ddns.tvan11.plants.domain.Plant;
import net.ddns.tvan11.plants.domain.dto.PlantDto;
import net.ddns.tvan11.plants.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

import static net.ddns.tvan11.plants.constant.FileConstant.FORWARD_SLASH;

@Service
@Transactional
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private ImageService imageService;

    public PlantDto addPlant(String plantString, MultipartFile plantHeaderImage, MultipartFile plantProfileImage) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Plant plant = mapper.readValue(plantString, Plant.class);
        Plant plantSaved = plantRepository.save(plant);
        savePlantImage(plantSaved, plantHeaderImage, "header");
        savePlantImage(plantSaved, plantProfileImage, "profile");

        return new PlantDto(plantSaved.getId(),plantSaved.getName(),plantSaved.getAmountOfWater(),
        plantSaved.getAmountOfLight(),plantSaved.isNeedsSpraying(),plantSaved.getRemarks(),plantSaved.getFamily(),plantSaved.getHeaderImageUrl(),
                plantSaved.getProfileImageUrl());
    }

    private void savePlantImage(Plant plant, MultipartFile image, String type) throws IOException {
        if(image != null) {
            imageService.copyImage(plant, image,type);
            if(type.equals("header")) {
                plant.setHeaderImageUrl(setPlantImageUrl(plant, image.getOriginalFilename(), type));
            }
            else if (type.equals("profile")) {
                plant.setProfileImageUrl(setPlantImageUrl(plant, image.getOriginalFilename(), type));
            }
            plantRepository.save(plant);
        }
    }

    private String setPlantImageUrl(Plant plant, String originalFileName, String type) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(
                "/images/" +  plant.getFamily().getName() + FORWARD_SLASH + plant.getName().replaceAll("\\s","")  + FORWARD_SLASH +
                        type + FORWARD_SLASH + plant.getName().replaceAll("\\s","") + originalFileName
        ).toUriString();
    }

    public PlantDto getPlant(Long plantId) {
        Optional<Plant> plantOptional = plantRepository.findById(plantId);
        PlantDto plantDto;
        if(plantOptional.isPresent()) {
            Plant plant = plantOptional.get();
            plantDto = new PlantDto(plant.getId(),plant.getName(),plant.getAmountOfWater(),
                    plant.getAmountOfLight(),plant.isNeedsSpraying(),plant.getRemarks(),plant.getFamily(),plant.getHeaderImageUrl(),
                    plant.getProfileImageUrl());
            return plantDto;
        }
        plantDto= new PlantDto();
        return plantDto;
    }
}
