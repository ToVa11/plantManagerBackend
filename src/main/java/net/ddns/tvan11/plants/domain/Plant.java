package net.ddns.tvan11.plants.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Plant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String amountOfWater;

    private String amountOfLight;

    private boolean needsSpraying;

    @Column(columnDefinition = "TEXT")
    private String remarks;

    @ManyToOne
    @JsonBackReference
    private Family family;

    private String headerImageUrl;

    private String profileImageUrl;

    public Plant() {
    }

    public Plant(String name, String amountOfWater, String amountOfLight, boolean needsSpraying, String remarks, Family family) {
        this.name = name;
        this.amountOfWater = amountOfWater;
        this.amountOfLight = amountOfLight;
        this.needsSpraying = needsSpraying;
        this.remarks = remarks;
        this.family = family;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return id.equals(plant.id) && name.equals(plant.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
