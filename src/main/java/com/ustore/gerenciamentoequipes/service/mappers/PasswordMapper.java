package com.ustore.gerenciamentoequipes.service.mappers;

import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordMapper {
    private final PasswordEncoder passwordEncoder;
    public PasswordMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Named("encode")
    public String encode(String raw) {
        return raw == null ? null : passwordEncoder.encode(raw);
    }
}
