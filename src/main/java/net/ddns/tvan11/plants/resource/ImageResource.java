package net.ddns.tvan11.plants.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static net.ddns.tvan11.plants.constant.FileConstant.*;

@RestController
@RequestMapping(IMAGE_FOLDER)
public class ImageResource {

    @GetMapping(path = "/{family}/{plantName}/{type}/{filename}")
    public byte[] getProfileImage(
            @PathVariable("family") String family,
            @PathVariable("plantName") String plantName,
            @PathVariable("type") String type,
            @PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(HOME_FOLDER + FORWARD_SLASH + family + FORWARD_SLASH + plantName+ FORWARD_SLASH + type +FORWARD_SLASH + filename));
    }

    @GetMapping(path = "/profiles/{username}/{fileName}")
    public byte[] getUserProfileImage(@PathVariable("username") String username, @PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(HOME_FOLDER+IMAGE_FOLDER+PROFILE_IMAGES_FOLDER+FORWARD_SLASH+username+FORWARD_SLASH+fileName));
    }

}
