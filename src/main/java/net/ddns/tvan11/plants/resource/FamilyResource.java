package net.ddns.tvan11.plants.resource;

import net.ddns.tvan11.plants.domain.Family;
import net.ddns.tvan11.plants.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/family")
public class FamilyResource {

    @Autowired
    private FamilyRepository familyRepository;

    @GetMapping("/list")
    public ResponseEntity<List<Family>> getAllFamilies() {
        return new ResponseEntity<>(familyRepository.findAll(), HttpStatus.OK);
    }

}
