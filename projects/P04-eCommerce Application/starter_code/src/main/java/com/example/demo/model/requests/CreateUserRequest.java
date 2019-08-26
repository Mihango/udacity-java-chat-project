package com.example.demo.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateUserRequest {

    @NotNull
    private String username;

    @NotNull
    @JsonProperty
    private String password;

    @NotNull
    @JsonProperty
    private String confirmPassword;
}
