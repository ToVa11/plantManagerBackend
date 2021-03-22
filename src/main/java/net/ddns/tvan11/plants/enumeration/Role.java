package net.ddns.tvan11.plants.enumeration;

import static net.ddns.tvan11.plants.constant.Authority.*;

public enum Role {

    ROLE_READ(READ_ALL_AUTHORITIES),
    ROLE_PLANT_WRITE(PLANT_WRITE_AUTHORITIES),
    ROLE_FAMILY_WRITE(FAMILY_WRITE_AUTHORITIES),
    ROLE_PLANT_DELETE(PLANT_DELETE_AUTHORITIES),
    ROLE_FAMILY_DELETE(FAMILY_DELETE_AUTHORITIES),
    ROLE_SUPER_ADMIN(SUPER_ADMIN_AUTHORITIES);

    private String[] authorities;


    Role(String[] authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
