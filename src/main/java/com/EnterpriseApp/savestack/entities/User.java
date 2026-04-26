package com.EnterpriseApp.savestack.entities;

/**
 * Represents a SaveStack user.
 */
public class User {

    private Long id;
    private String name;
    private String email;

    /**
     * Creates a user.
     *
     * @param id unique user ID
     * @param name user's name
     * @param email user's email address
     */
    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() { return id; }
}