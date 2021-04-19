package net.ddns.tvan11.plants.service;

import net.ddns.tvan11.plants.domain.Plant;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static net.ddns.tvan11.plants.constant.FileConstant.*;

@Service
public class ImageService {
    public void copyImage(Plant plant, MultipartFile headerImage, String type) throws IOException {
        Path plantHeaderFolder = Paths.get(HOME_FOLDER + FORWARD_SLASH + plant.getFamily().getName().replaceAll("\\s","") + FORWARD_SLASH + plant.getName().replaceAll("\\s","") + FORWARD_SLASH + type).toAbsolutePath().normalize();
        if(!Files.exists(plantHeaderFolder)) {
            Files.createDirectories(plantHeaderFolder);
        }
        Files.copy(headerImage.getInputStream(), plantHeaderFolder.resolve(plant.getName().replaceAll("\\s","") + headerImage.getOriginalFilename()),REPLACE_EXISTING);

    }

    public String copyProfileImage(String username, MultipartFile profileImage) throws IOException {
        Path profileImageFolder = Paths.get(HOME_FOLDER+FORWARD_SLASH+IMAGE_FOLDER+FORWARD_SLASH+PROFILE_IMAGES_FOLDER+FORWARD_SLASH+username).toAbsolutePath().normalize();
        if(!Files.exists(profileImageFolder)) {
            Files.createDirectories(profileImageFolder);
        }
        String extension = this.getExtension(profileImage.getOriginalFilename()).orElse("jpg");
        Files.copy(profileImage.getInputStream(), profileImageFolder.resolve(username + "." + extension), REPLACE_EXISTING);
        return username + "." + extension;
    }

    private Optional<String> getExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}
