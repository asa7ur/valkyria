package org.iesalixar.daw2.GarikAsatryan.valkyria.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.UserRegistrationDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.User;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.security.JwtService;
import org.iesalixar.daw2.GarikAsatryan.valkyria.services.RegistrationService;
import org.iesalixar.daw2.GarikAsatryan.valkyria.services.UserService;
import org.iesalixar.daw2.GarikAsatryan.valkyria.services.VerificationTokenService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final RegistrationService registrationService;
    private final MessageSource messageSource;
    private final VerificationTokenService verificationTokenService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request, HttpServletResponse response) {
        // 1. Autenticar credenciales
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.get("username"), request.get("password"))
        );

        // 2. Cargar usuario y generar Token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.get("username"));
        final String jwt = jwtService.generateToken(userDetails);

        // 3. Crear Cookie para Thymeleaf (Administración)
        Cookie jwtCookie = new Cookie("jwt", jwt);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(24 * 60 * 60);
        response.addCookie(jwtCookie);

        // --- NUEVA LÓGICA DE REDIRECCIÓN ---
        boolean isAdminOrManager = userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("ROLE_MANAGER"));

        // Definimos la URL de redirección: si es admin va a 8080, si no se queda en Angular
        String redirectUrl = isAdminOrManager ? "http://localhost:8080/admin/dashboard" : "http://localhost:4200/";

        // 4. Responder a Angular con el JSON esperado incluyendo la redirectUrl
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("token", jwt);
        responseBody.put("username", userDetails.getUsername());
        responseBody.put("roles", userDetails.getAuthorities());
        responseBody.put("redirectUrl", redirectUrl); // <--- Añadimos este campo

        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegistrationDTO registrationDTO) {
        try {
            registrationService.registerUser(registrationDTO);
            Map<String, String> response = new HashMap<>();
            response.put("message", messageSource.getMessage("msg.register.success", null, LocaleContextHolder.getLocale()));
            return ResponseEntity.ok(response);
        } catch (AppException e) {
            String errorMessage = messageSource.getMessage(e.getMessageKey(), e.getArgs(), LocaleContextHolder.getLocale());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", errorMessage);
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirmRegistration(@RequestParam("token") String token) {
        return verificationTokenService.getVerificationToken(token)
                .map(verificationToken -> {
                    if (verificationToken.isExpired()) {
                        Map<String, String> response = new HashMap<>();
                        response.put("error", messageSource.getMessage("msg.register.error.invalidToken", null, LocaleContextHolder.getLocale()));
                        return ResponseEntity.badRequest().body(response);
                    }

                    User user = verificationToken.getUser();
                    user.setEnabled(true);
                    userService.saveUser(user);
                    verificationTokenService.deleteToken(verificationToken);

                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Account activated");
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("error", "Invalid token");
                    return ResponseEntity.badRequest().body(response);
                });
    }
}