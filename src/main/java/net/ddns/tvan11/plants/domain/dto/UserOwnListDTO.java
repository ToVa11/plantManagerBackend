package net.ddns.tvan11.plants.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class UserOwnListDTO {

    private long userId;
    private List<Long> plantIds = new ArrayList<>();

    public UserOwnListDTO() {
    }

    public UserOwnListDTO(long userId, List<Long> plantIds) {
        this.userId = userId;
        this.plantIds = plantIds;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Long> getPlantIds() {
        return plantIds;
    }

    public void setPlantIds(List<Long> plantIds) {
        this.plantIds = plantIds;
    }

    public void addPlantToPlantIds(Long plantId){
        this.plantIds.add(plantId);
    }
}
