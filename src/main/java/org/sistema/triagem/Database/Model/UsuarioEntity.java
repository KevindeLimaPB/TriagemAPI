package org.sistema.triagem.Database.Model;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.sistema.triagem.Enums.EnumUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String login;

    @Column
    private String senha;

    @Column(unique = true)
    private String cpf;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private EnumUser enumUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.enumUser == EnumUser.ADMIN){
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_FUNCIONARIO"),
                    new SimpleGrantedAuthority("ROLE_PACIENTE")
            );
        }

        if (this.enumUser == EnumUser.PACIENTE){
            return List.of(
                    new SimpleGrantedAuthority("ROLE_PACIENTE")
            );
        }

        return List.of(
                new SimpleGrantedAuthority("ROLE_FUNCIONARIO")
        );
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }
}
