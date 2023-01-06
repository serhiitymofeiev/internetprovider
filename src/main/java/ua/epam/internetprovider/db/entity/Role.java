package ua.epam.internetprovider.db.entity;

/**
 * Enumeration of roles
 * ADMIN - administrator, CLIENT - ordinary client-user
 */
public enum Role {
    ADMIN, CLIENT;

    /**
     * The procedure for obtaining a user role
     * @param user - a user of type User
     * @return user role of type Role
     */
    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[--roleId];
    }

    /**
     * The procedure for obtaining the name of a user's role in lower case
     * @return role name in lower case
     */

    public String getName() {
        return name().toLowerCase();
    }
}