package com.cdg.Foro.infraestructura.security;

import com.cdg.Foro.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de seguridad, encargado de recuperar el token que se encuentra en el header de la peticion.
 * El token se encuentra en el header de la peticion dentro de la clave "Authorization".
 * El token se forma de la siguiente manera: "Bearer <token>".
 * Si no se encuentra el token en el header, lanza una excepcion.
 */
@Component

public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepository usuarioRepository;
    //inyectamos el tokenService para poder usarlo en el filtro de seguridad
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //recibir el token,que viene en el header de la peticion dentro de authorization
        var tokenJWT = recuperarToken(request);//recuperar el token de la peticion
        System.out.println("Token: " + tokenJWT);
        if (tokenJWT != null) { //si el token es diferente de null pidemos saber el subject
            var subject = tokenService.getSubject(tokenJWT);
            //forzar la autenticacion con el subject
            var usuario = usuarioRepository.findByNombre(subject);
            //Autenticacion
            var authentication = new UsernamePasswordAuthenticationToken(
                    usuario,
                    null,
                    usuario.getAuthorities()//roles
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Usuario logeado!");



        }
        filterChain.doFilter(request, response);//call the next filter
        //validacion del token

    }

    /**
     * Recupera el token de la peticion.
     * @param request peticion http
     * @return el token de la peticion
     */
    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }else{
            return null;//spring se encarga de hacer la autorizacion
        }
    }
}
