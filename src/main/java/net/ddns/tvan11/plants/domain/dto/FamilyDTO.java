package net.ddns.tvan11.plants.domain.dto;

import net.ddns.tvan11.plants.domain.Plant;

import java.util.ArrayList;
import java.util.List;

public class FamilyDTO {

    private Long id;
    private String name;
    private List<Plant> plants = new ArrayList<>();

    public FamilyDTO() {
    }

    public FamilyDTO(Long id, String name, List<Plant> plants) {
        this.id = id;
        this.name = name;
        this.plants = plants;
    }

    public FamilyDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
}
