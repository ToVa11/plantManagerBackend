package net.ddns.tvan11.plants.domain.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import net.ddns.tvan11.plants.domain.Family;

import javax.persistence.*;

public class PlantDto {

    private Long id;
    private String name;
    private String amountOfWater;
    private String amountOfLight;
    private boolean needsSpraying;
    private String remarks;
    private Family family;
    private String headerImageUrl;
    private String profileImageUrl;

    public PlantDto() {
    }

    public PlantDto(Long id, String name, String amountOfWater, String amountOfLight, boolean needsSpraying, String remarks, Family family, String headerImageUrl, String profileImageUrl) {
        this.id = id;
        this.name = name;
        this.amountOfWater = amountOfWater;
        this.amountOfLight = amountOfLight;
        this.needsSpraying = needsSpraying;
        this.remarks = remarks;
        this.family = family;
        this.headerImageUrl = headerImageUrl;
        this.profileImageUrl = profileImageUrl;
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

    public String getAmountOfWater() {
        return amountOfWater;
    }

    public void setAmountOfWater(String amountOfWater) {
        this.amountOfWater = amountOfWater;
    }

    public String getAmountOfLight() {
        return amountOfLight;
    }

    public void setAmountOfLight(String amountOfLight) {
        this.amountOfLight = amountOfLight;
    }

    public boolean isNeedsSpraying() {
        return needsSpraying;
    }

    public void setNeedsSpraying(boolean needsSpraying) {
        this.needsSpraying = needsSpraying;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public String getHeaderImageUrl() {
        return headerImageUrl;
    }

    public void setHeaderImageUrl(String headerImageUrl) {
        this.headerImageUrl = headerImageUrl;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
