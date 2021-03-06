package net.ddns.tvan11.plants.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String email;
    private String profileImageUrl;

    @ManyToMany
    @JoinTable(name="user_wishList",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name="plantId")}
    )
    private List<Plant> wishList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="user_ownList",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name="plantId")}
    )
    private List<Plant> ownList = new ArrayList<>();

    private String[] roles;
    private String[] authorities;

    public User() {
    }

    public User(Long id, String firstName, String lastName, String username, String password, String email, String profileImageUrl, String[] roles, String[] authorities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.roles = roles;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public List<Plant> getWishList() {
        return wishList;
    }

    public void addPlantToWishList(Plant plant) {
        this.wishList.add(plant);
    }

    public void removePlantFromWishList(Plant plant) {
        this.wishList.remove(plant);
    }

    public List<Plant> getOwnList() {
        return this.ownList;
    }

    public void addPlantToOwnList(Plant plant) {
        this.ownList.add(plant);
    }

    public void removePlantFromOwnList(Plant plant) {
        this.ownList.remove(plant);
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }
}
