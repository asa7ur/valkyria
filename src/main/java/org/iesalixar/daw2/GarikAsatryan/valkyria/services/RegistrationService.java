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

        // 4. Asignar rol por defecto (asegúrate de que ROLE_USER existe en tu BD)
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new AppException("msg.error.role-not-found", "ROLE_USER"));
        user.setRoles(Collections.singletonList(userRole));

        // 5. El usuario empieza desactivado hasta que confirme el email (si usas tokens)
        user.setEnabled(false);

        userRepository.save(user);
    }
}