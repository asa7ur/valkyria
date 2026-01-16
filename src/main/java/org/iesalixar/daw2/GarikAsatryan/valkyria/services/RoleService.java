package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Role;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio de gestión de roles del sistema.
 * Proporciona acceso a los roles disponibles para asignación a usuarios.
 * <p>
 * Roles típicos del sistema:
 * - USER: Usuario estándar del festival (compra entradas, ve programa)
 * - ADMIN: Administrador con acceso completo al panel de control
 * - MANAGER: Organizador con permisos de gestión del evento
 * <p>
 * Casos de uso:
 * - Asignación de roles durante el registro (rol USER por defecto)
 * - Gestión de permisos en panel de administración
 * - Listado de roles disponibles en formularios de usuarios
 * <p>
 * NOTA: Los roles son datos maestros del sistema, típicamente precargados
 * en la base de datos durante la inicialización (data.sql o migrations).
 * Este servicio proporciona principalmente operaciones de lectura.
 */
@Service
@RequiredArgsConstructor
public class RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    // Inyección de dependencias mediante constructor (Lombok @RequiredArgsConstructor)
    private final RoleRepository roleRepository;

    /**
     * Obtiene la lista completa de todos los roles disponibles en el sistema.
     * No tiene paginación ya que el número de roles es típicamente muy pequeño (2-5 roles).
     * <p>
     * Utilizado principalmente para:
     * - Poblar selectores en formularios de gestión de usuarios
     * - Mostrar roles disponibles en panel de administración
     * - Validar roles durante asignaciones
     *
     * @return Lista de todas las entidades Role del sistema
     */
    public List<Role> getAllRoles() {
        logger.info("Recuperando lista completa de roles del sistema");

        // Obtener todos los roles de la base de datos
        List<Role> roles = roleRepository.findAll();

        logger.debug("Total de roles recuperados: {}", roles.size());

        // Log de los roles encontrados (útil para verificar configuración inicial)
        if (logger.isDebugEnabled()) {
            roles.forEach(role ->
                    logger.debug("Rol encontrado: ID={}, Nombre={}", role.getId(), role.getName())
            );
        }

        // Advertencia si no hay roles (posible problema de configuración)
        if (roles.isEmpty()) {
            logger.warn("⚠ ADVERTENCIA: No se encontraron roles en el sistema. " +
                    "Esto puede indicar que los datos iniciales no se han cargado correctamente.");
        }

        return roles;
    }
}