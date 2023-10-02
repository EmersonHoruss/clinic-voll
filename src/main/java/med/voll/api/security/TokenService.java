package med.voll.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import med.voll.api.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("voll")
                    .withSubject(usuario.getLogin())
                    .withClaim("id",usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException(exception);
        }
    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubject(String token){
        String subject = null;
        try{
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            DecodedJWT verifier=JWT.require(algorithm)
                    .withIssuer("voll")
                    .build()
                    .verify(token);
            subject = verifier.getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException(e);
        }
        if(subject == null) {
            throw new RuntimeException("Fallo por el subject en la validación del token(verifier.)");
        }
        return subject;
    }
}
