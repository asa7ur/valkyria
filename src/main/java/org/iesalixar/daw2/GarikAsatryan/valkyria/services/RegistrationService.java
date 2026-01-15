package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.UserRegistrationDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Role;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.User;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.mappers.UserMapper;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.RoleRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenService verificationTokenService;
    private final EmailService emailService;

    @Transactional
    public void registerUser(UserRegistrationDTO registrationDTO) {
        // 1. Verificar si el email ya existe
        if (userRepository.existsByEmail(registrationDTO.getEmail())) {
            throw new AppException("msg.register.error.email-exists", registrationDTO.getEmail());
        }

        // 2. Mapear DTO a Entidad
        User user = userMapper.toEntity(registrationDTO);

        // 3. Cifrar la contraseña
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        // 4. Asignar rol por defecto
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new AppException("msg.error.role-not-found", "USER"));
        user.setRoles(Collections.singletonList(userRole));

        // 5. El usuario empieza desactivado hasta que confirme el email
        user.setEnabled(false);

        userRepository.save(user);

        // Generamos el token de verificación asociado a este usuario
        String token = verificationTokenService.createVerificationToken(user);

        // Enviamos el email
        emailService.sendRegistrationConfirmationEmail(user.getEmail(), user.getFirstName(), token);
    }
}