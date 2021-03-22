package net.ddns.tvan11.plants.resource;

import net.ddns.tvan11.plants.domain.Family;
import net.ddns.tvan11.plants.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/family")
public class FamilyResource {

    @Autowired
    private FamilyRepository familyRepository;

    @GetMapping("/list")
    public ResponseEntity<List<Family>> getAllFamilies() {
        return new ResponseEntity<>(familyRepository.findAll(), OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Family> addFamily(@RequestBody Family family) {
        Family newFamily = familyRepository.save(family);

        return new ResponseEntity<>(newFamily, OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Family> updateFamily(@RequestBody Family family) {
        Optional<Family> oldFamily = familyRepository.findById(family.getId());
        if(oldFamily.isPresent()) {
            Family updatedFamily = familyRepository.save(family);
            return new ResponseEntity<>(updatedFamily, OK);
        }

        return new ResponseEntity<>(family, NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteFamily(@RequestBody Family family) {
        Optional<Family> oldFamily = familyRepository.findById(family.getId());
        if(oldFamily.isPresent()) {
            familyRepository.delete(family);
            return new ResponseEntity<>(OK);
        }

        return new ResponseEntity<>(NOT_FOUND);
    }

}
