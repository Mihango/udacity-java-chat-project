package com.example.demo.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserRequest {

//    @NotNull
    private String username;

//    @NotNull
    @JsonProperty
    private String password;

//    @NotNull
    @JsonProperty
    private String confirmPassword;
}
