package com.example.utils;

// Simple configuration holder. In real projects this would read from file/env.
public final class Config {
    private Config() {}

    public static final String BASE_URL = "https://demowebshop.tricentis.com";

    // Example test user - replace with valid credentials for real runs
    public static final String VALID_EMAIL = "valid_user@example.com";
    public static final String VALID_PASSWORD = "correct_password";

    public static final String UNREGISTERED_EMAIL = "not_registered@example.com";
    public static final String INVALID_EMAIL = "invalid-email";
}
