package com.cdg.Foro.controller;

import com.cdg.Foro.domain.usuario.DatosAutenticacionUsuario;
import com.cdg.Foro.domain.usuario.Usuario;
import com.cdg.Foro.infraestructura.security.DatosJWTTokenDTO;
import com.cdg.Foro.infraestructura.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST que maneja las solicitudes de autenticación de usuarios.
 * Procesa las peticiones de inicio de sesión y genera tokens JWT para la autenticación.
 */
@RestController // Marca esta clase como un controlador REST
@RequestMapping("/login") // Mapea todas las peticiones que comiencen con /login a este controlador
public class AutenticacionController {
    
    // Inyección del servicio para la generación de tokens JWT
    @Autowired
    private TokenService tokenService;

    // Inyección del gestor de autenticación de Spring Security
    @Autowired
    private AuthenticationManager authenticationManager;
    
    /**
     * Maneja las solicitudes POST a /login para autenticar usuarios.
     * 
     * @param datos Objeto que contiene las credenciales del usuario (nombre y contraseña)
     * @return ResponseEntity que contiene el token JWT si la autenticación es exitosa
     */
    @PostMapping // Mapea las peticiones POST a este método
    public ResponseEntity iniciarSesion(
            @RequestBody @Valid DatosAutenticacionUsuario datos) {
        
        // 1. Crear un token de autenticación con las credenciales proporcionadas
        var autenticationToken = new UsernamePasswordAuthenticationToken(
                datos.nombre(),     // Nombre de usuario
                datos.contrasena()  // Contraseña
        );
        
        // 2. Autenticar al usuario con las credenciales proporcionadas
        //    Esto lanzará una excepción si la autenticación falla
        var autenticacion = authenticationManager.authenticate(autenticationToken);
        
        // 3. Generar un token JWT para el usuario autenticado
        //    getPrincipal() devuelve el objeto Usuario autenticado
        var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());
        
        // 4. Devolver el token JWT en la respuesta
        return ResponseEntity.ok(new DatosJWTTokenDTO(tokenJWT));
    }
}
