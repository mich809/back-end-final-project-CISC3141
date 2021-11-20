package com.server.bugtracker.User;

import org.springframework.context.annotation.Scope;

import javax.persistence.*;

@Scope("session")
@Entity
@Table(name = "User")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", initialValue = 1, allocationSize=1)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "user_name")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "team_role")
    private String teamRole;
    @Column(name = "organization")
    private String organization;

    /**
     * No-arg constructor
     */
    public User() {}

    /**
     * Constructor
     * @param id
     * @param name
     * @param description
     * @param user_name
     * @param password
     * @param email
     * @param team_role
     * @param organization
     */
    public User(long id, String name, String description, String user_name, String password,
                String email, String team_role, String organization)
    {

    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUser_name()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getTeam_role()
    {
        return teamRole;
    }

    public void setTeam_role(String team_role)
    {
        this.teamRole = team_role;
    }

    public String getOrganization()
    {
        return organization;
    }

    public void setOrganization(String organization)
    {
        this.organization = organization;
    }

}
