package net.ddns.tvan11.plants.service;

import net.ddns.tvan11.plants.domain.Family;
import net.ddns.tvan11.plants.domain.Plant;
import net.ddns.tvan11.plants.domain.dto.FamilyDTO;
import net.ddns.tvan11.plants.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    public List<FamilyDTO> getFamilies() {
        List<Family> families = familyRepository.findAll();
        List<FamilyDTO> familiesDto = new ArrayList<>();
        for (Family family: families) {
            FamilyDTO familyDTO = new FamilyDTO(family.getId(), family.getName());
            familiesDto.add(familyDTO);
        }
        return familiesDto;
    }

    public List<FamilyDTO> getFamiliesWithPlants() {
        List<Family> families = familyRepository.findAll();
        List<FamilyDTO> familiesDto = new ArrayList<>();

        for (Family family: families ) {
            FamilyDTO familyDTO = new FamilyDTO(
                    family.getId(),
                    family.getName(),
                    family.getPlants().stream().sorted(Comparator.comparing(Plant::getName)).collect(Collectors.toList())
            );
            familiesDto.add(familyDTO);
        }
        return familiesDto;
    }
}
