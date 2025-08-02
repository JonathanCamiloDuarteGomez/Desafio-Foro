package com.cdg.Foro.infraestructura.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cdg.Foro.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    //viene de aplicacion.properties
    @Value("${api.security.token.secret}")
    private String secret = "frase-secreta-para-generar-token";

    //para generar el token
    public String generarToken(Usuario usuario) {
        {
            try {
                var algoritmo = Algorithm.HMAC256(secret);
                return JWT.create()
                        .withIssuer("API Foro")//para identificar quien la esta firmando
                        .withSubject(usuario.getUsername())//para identificar al usuario
                        .withExpiresAt(fechaExpiracion())//para identificar el tiempo de expiracion
                        //.withClaim("rol", usuario.getId())//llave y valor
                        .sign(algoritmo);
            } catch (JWTCreationException exception) {
                throw new RuntimeException("Error al generar el token", exception);
            }
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));//para obtener la fecha de expiracion y la zona horaria
    }
    //para obtener el subject
    public String getSubject(String tokenJWT) {
        //obtener el subject si el token es valido
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API Foro")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){
            // Invalid signature/claims
            throw new RuntimeException("Token invalido o expirado", exception);
        }
    }
}
