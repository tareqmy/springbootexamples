package com.tareqmy.springbootexamples.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "users")
public class User implements UserDetails {

    private static final long serialVersionUID = 3597192375595257027L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, updatable = false, unique = true)
    private String username;

    @Column(name = "first_name", length = 25)
    private String firstName;

    @Column(name = "last_name", length = 25)
    private String lastName;

    @Column(length = 60, nullable = false)
    @ToString.Exclude
    private String password;

    @Column(name = "role", length = 25, nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "api_key", length = 36)
    private String apiKey;

    // ----- userDetails methods -------

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(role.name());
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // ----- userDetails methods -------

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean isSystemAdmin() {
        return role.equals(Role.ROLE_SYSTEM_ADMIN);
    }

    public boolean isAdmin() {
        return role.equals(Role.ROLE_ADMIN);
    }

    public boolean isUser() {
        return role.equals(Role.ROLE_USER);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder().append(getUsername(), user.getUsername()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getUsername()).toHashCode();
    }
}

