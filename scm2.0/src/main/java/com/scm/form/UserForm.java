package com.scm.form;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserForm {
    @NotBlank(message="Username is required")
    @Size(min = 3,message="Minimum 3 character")
    private String name;
    @Email(message="Invalid email address")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min=6,message = "Password with minimum 6 character")
    private String password;
    @NotBlank(message="Phone number is required")
    @Size(min=8,max = 10,message = "Minimum 8 and Maximum 10 is required")
    private String phonenumber;
    @NotBlank(message = "About is required")
    private String about;
}
