package net.ddns.tvan11.plants.domain;

import javax.persistence.*;

@Entity
public class Plant {

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
    private Family family;

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
}
