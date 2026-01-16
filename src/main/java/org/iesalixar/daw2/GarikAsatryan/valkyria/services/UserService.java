package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.UserDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.UserRegistrationDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.PasswordChangeDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Role;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.User;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.mappers.UserMapper;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.RoleRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public Page<UserDTO> getAllUsers(String searchTerm, Pageable pageable) {
        Page<User> userPage = (searchTerm != null && !searchTerm.trim().isEmpty())
                ? userRepository.searchUsers(searchTerm, pageable)
                : userRepository.findAll(pageable);
        return userPage.map(userMapper::toDTO);
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::toDTO);
    }

    /**
     * Busca un usuario por email y devuelve la ENTIDAD.
     * Útil para procesos internos como la creación de pedidos.
     */
    public User getUserByEmailEntity(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("msg.error.user-not-found", email));
    }

    /**
     * UPDATE: Actualiza un usuario existente.
     * No solemos actualizar la contraseña aquí (se hace en un flujo de "cambiar password").
     */
    @Transactional
    public UserDTO updateUser(Long id, UserRegistrationDTO dto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new AppException("msg.error.user-not-found", id));

        if (!existingUser.getEmail().equals(dto.getEmail()) && userRepository.existsByEmail(dto.getEmail())) {
            throw new AppException("msg.register.error.email-exists", dto.getEmail());
        }

        // Actualizamos campos básicos
        userMapper.updateEntityFromDTO(dto, existingUser);

        // 1. Sincronizar el estado de activación
        existingUser.setEnabled(dto.isEnabled());

        // 2. Gestionar roles si vienen en el DTO
        if (dto.getRoles() != null) {
            List<Role> roles = dto.getRoles().stream()
                    .map(roleName -> roleRepository.findByName(roleName)
                            .orElseThrow(() -> new AppException("msg.error.role-not-found", roleName)))
                    .collect(Collectors.toList());
            existingUser.setRoles(roles);
        }

        User updated = userRepository.save(existingUser);
        logger.info("Usuario con ID {} actualizado (Email: {}, Enabled: {}, Roles: {})",
                id, updated.getEmail(), updated.isEnabled(), dto.getRoles());
        return userMapper.toDTO(updated);
    }

    /**
     * CREATE (Admin): Crea un usuario directamente desde la administración.
     * A diferencia del registro público, aquí el admin podría marcarlo como enabled.
     */
    @Transactional
    public UserDTO createUser(UserRegistrationDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new AppException("msg.register.error.email-exists", dto.getEmail());
        }

        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(true); // El admin crea usuarios ya activos

        // Asignar USER por defecto o según lógica de admin
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new AppException("msg.error.role-not-found", "USER"));
        user.setRoles(List.of(userRole));

        return userMapper.toDTO(userRepository.save(user));
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new AppException("msg.error.user-not-found", id);
        }
        userRepository.deleteById(id);
    }

    // Este método lo mantenemos solo para el proceso de confirmación de token
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    /**
     * Cambia la contraseña de un usuario tras verificar la contraseña actual.
     */
    @Transactional
    public void changePassword(Long id, PasswordChangeDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException("msg.error.user-not-found", id));

        // 1. Verificar que la contraseña actual es correcta
        if (!passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())) {
            throw new AppException("msg.error.invalid-current-password");
        }

        // 2. Cifrar y guardar la nueva contraseña
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user);

        logger.info("Contraseña actualizada para el usuario con ID {}", id);
    }
}