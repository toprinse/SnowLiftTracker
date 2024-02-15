package fr.esisar.snowlifttracker.dto.plain;

import jakarta.validation.constraints.NotBlank;

public class DTOPlainUserSnow {
    
    public Long id;

    @NotBlank
    public String firstname;

    @NotBlank
    public String lastname;

    @NotBlank
    public String email;

    @NotBlank
    public String login;

    @NotBlank
    public String password;

    @NotBlank
    public String permission;
}
