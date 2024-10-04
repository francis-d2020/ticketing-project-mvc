package com.cydeo.dto;


import com.cydeo.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

 @NotBlank
 //can not be space only, empty string, null
 @Size(max=15,min = 2)
    private String firstName;
    @NotBlank
    @Size(max = 15, min = 2)
    private String lastName;

    @NotBlank
    @Email
    private String userName;

    @NotBlank
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,}")
    //special chars, capital and small letters, nums,4+ letters
    private String passWord;

    @NotNull
    private String confirmPassWord;

    private boolean enabled;

    @NotBlank
    @Pattern(regexp = "^\\d{10}$")
    //ten chars and only numbers
    private String phone;

    @NotNull
    //notnull is for objects, notblank/notEmpty is for strings
    private RoleDTO role;

    @NotNull
    private Gender gender;

}