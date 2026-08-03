package com.harsh.hospital_auth_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @GetMapping("/admin/dashboard")
    public String admin() {
        return "Welcome Admin";
    }

    @GetMapping("/doctor/dashboard")
    public String doctor() {
        return "Welcome Doctor";
    }

    @GetMapping("/patient/dashboard")
    public String patient() {
        return "Welcome Patient";
    }

    @GetMapping("/receptionist/dashboard")
    public String receptionist() {
        return "Welcome Receptionist";
    }

}
