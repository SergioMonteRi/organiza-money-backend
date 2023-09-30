package com.organizaMoney.service.user.http;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.validation.constraints.Email;

public record UserDto(Long id,
                      String firstName,
                      String lastName,
                      @Email
                      String email,
                      @JsonIgnore
                      String password) {
    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
