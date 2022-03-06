package com.tareqmy.springbootexamples.web.dto;

import com.tareqmy.springbootexamples.data.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccountDTO {

    @NotEmpty(message = "{user.firstName.notempty}")
    @Size(min = 2, max = 25, message = "{user.firstName.size}")
    private String firstName;

    @NotEmpty(message = "{user.lastName.notempty}")
    @Size(min = 2, max = 25, message = "{user.lastName.size}")
    private String lastName;

    public AccountDTO(User user) {
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
    }
}
