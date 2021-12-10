package com.server.bugtracker.user;

import org.springframework.context.annotation.Scope;

import javax.persistence.*;

@Scope("session")
@Entity
@Table(name = "User")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", initialValue = 1, allocationSize = 1)
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

    // TODO Remove assigned_to in database and in User class

    /**
     * No-arg constructor
     */
    public User() {}

    public User(long id, String name, String username, String password, String email, String teamRole, String organization)
    {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.teamRole = teamRole;
        this.organization = organization;
    }

    public User(String name, String username, String password, String email, String teamRole, String organization)
    {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.teamRole = teamRole;
        this.organization = organization;
    }

    /**
     * Checks if user registration data is valid
     * @return True if user is valid, false if it isn't
     */
    public boolean validUser()
    {
        if( name != null && username != null && password != null && email != null &&
                teamRole != null && organization != null )
        {
            if ( !name.isEmpty() && !username.isEmpty() && !password.isEmpty() && !email.isEmpty() &&
                !teamRole.isEmpty() && !organization.isEmpty() )
            {
                return true;
            }
        }
        return false;
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

    public void setTeam_role(String teamRole)
    {
        this.teamRole = teamRole;
    }

    public String getOrganization()
    {
        return organization;
    }

    public void setOrganization(String organization)
    {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", teamRole='" + teamRole + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }

}
