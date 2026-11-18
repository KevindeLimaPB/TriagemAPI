package org.sistema.triagem.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Repository.FuncionarioRepository;
import org.sistema.triagem.Database.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    private final FuncionarioRepository funcionarioRepository;

    @Value("${api.security.token.secret}")
    private String secret;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException
    {
        var token = this.recoverToken(request);

        if (token != null) {
            try {
                Algorithm algorithm = Algorithm.HMAC256(secret);
                DecodedJWT decodedJWT = JWT.require(algorithm)
                        .withIssuer("auth-api")
                        .build()
                        .verify(token);

                String subject = decodedJWT.getSubject();
                String role = decodedJWT.getClaim("role").asString(); //

                UserDetails user = null;

                if ("CLIENTE".equals(role)) {
                    user = usuarioRepository.findByCpf(subject);
                } else if ("FUNCIONARIO".equals(role)) {
                    user = funcionarioRepository.findByMatricula(subject);
                }

                if (user != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            } catch (Exception e) {

            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        String method = request.getMethod();

        // Ignora o filtro de token para requisições OPTIONS e rotas públicas de cadastro/login
        return method.equalsIgnoreCase("OPTIONS")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || (method.equalsIgnoreCase("POST") && path.equals("/users"))
                || (method.equalsIgnoreCase("POST") && path.equals("/register/login"))
                || (method.equalsIgnoreCase("POST") && path.equals("/func"))
                || (method.equalsIgnoreCase("POST") && path.equals("/pac"))
                || (method.equalsIgnoreCase("POST") && path.equals("/med"))
                || (method.equalsIgnoreCase("GET") && path.equals("/users"))
                || (method.equalsIgnoreCase("PUT") && path.equals("/users"));
    }


    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null) return null;
        return  authHeader.replace("Bearer ", "");
    }
}
