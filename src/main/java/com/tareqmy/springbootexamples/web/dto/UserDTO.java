package com.tareqmy.springbootexamples.web.dto;

import com.tareqmy.springbootexamples.data.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Created by tareqmy at 6/3/22
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {

    private Long id;

    @Email(message = "{user.email.invalid")
    @Size(min = 5, max = 50, message = "{user.email.size}")
    private String username;

    @NotEmpty(message = "{user.firstName.notempty}")
    @Size(min = 2, max = 25, message = "{user.firstName.size}")
    private String firstName;

    @NotEmpty(message = "{user.lastName.notempty}")
    @Size(min = 2, max = 25, message = "{user.lastName.size}")
    private String lastName;

    @ValidPassword
    private String password;

    @NotEmpty
    private String role;

    //note: for view only
    private boolean locked;

    public UserDTO(User user) {
        setId(user.getId());
        setUsername(user.getUsername());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setPassword(null);
        setRole(user.getRole().name());
    }
}
