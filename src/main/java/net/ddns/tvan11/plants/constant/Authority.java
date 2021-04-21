package net.ddns.tvan11.plants.constant;

public class Authority {
    public static final String PLANT_READ_AUTHORITIES = "plant:read";
    public static final String FAMILY_READ_AUTHORITIES = "family:read";
    public static final String[] READ_ALL_AUTHORITIES = {"plant:read", "family:read"};
    public static final String[] PLANT_WRITE_AUTHORITIES = {"plant:create","plant:update"};
    public static final String[] FAMILY_WRITE_AUTHORITIES = {"family:create","family:update"};
    public static final String[] PLANT_DELETE_AUTHORITIES = {"plant:delete"};
    public static final String[] FAMILY_DELETE_AUTHORITIES = {"family:delete"};
    public static final String[] PLANT_ADMIN_AUTHORITIES = {"plant:read","plant:create","plant:update", "plant:delete"};
    public static final String[] FAMILY_ADMIN_AUTHORITIES = {"family:read", "family:create","family:update", "family:delete"};
    public static final String[] SUPER_ADMIN_AUTHORITIES = {"plant:read","plant:create","plant:update", "plant:delete","family:read", "family:create","family:update", "family:delete", "user:create", "user:delete"};
}
