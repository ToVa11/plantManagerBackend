package net.ddns.tvan11.plants.service;

import net.ddns.tvan11.plants.domain.Plant;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static net.ddns.tvan11.plants.constant.FileConstant.FORWARD_SLASH;
import static net.ddns.tvan11.plants.constant.FileConstant.HOME_FOLDER;

@Service
public class ImageService {
    public void copyImage(Plant plant, MultipartFile headerImage, String type) throws IOException {
        Path plantHeaderFolder = Paths.get(HOME_FOLDER + FORWARD_SLASH + plant.getFamily().getName().replaceAll("\\s","") + FORWARD_SLASH + plant.getName().replaceAll("\\s","") + FORWARD_SLASH + type).toAbsolutePath().normalize();
        if(!Files.exists(plantHeaderFolder)) {
            Files.createDirectories(plantHeaderFolder);
        }
        Files.copy(headerImage.getInputStream(), plantHeaderFolder.resolve(plant.getName().replaceAll("\\s","") + headerImage.getOriginalFilename()),REPLACE_EXISTING);

    }
//
//    private void saveResizedImage(BufferedImage image) {
//
//    }
//
//    private MultipartFile resizeImage(MultipartFile image, int targetWidth, int targetHeight) throws IOException {
//        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(image.getBytes()));
//        Image resultingImage = bufferedImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
//        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
//        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
//        return image;
//    }
}
