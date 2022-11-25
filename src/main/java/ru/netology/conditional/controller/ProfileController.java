package ru.netology.conditional.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.conditional.model.SystemProfile;

@RestController
@RequestMapping("/")
public class ProfileController {
    private SystemProfile profile;

    public void setProfile(SystemProfile profile) {
        this.profile = profile;
    }

    public ProfileController(SystemProfile profile) {
        this.profile = profile;
    }

    @GetMapping("profile")
    public String getProfile() {
        return profile.getProfile();
    }
}
