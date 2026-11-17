package org.sistema.triagem.Database.Model;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.sistema.triagem.Enums.Profissao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuncionarioEntity implements UserDetails {

    @Id
    private String matricula;

    @OneToMany(mappedBy = "funcionario_id")
    private List<EnderecoFuncEntity> endereco;


    @OneToOne
    @JoinColumn(name = "usuario")
    private UsuarioEntity usuario;


    @OneToMany(mappedBy = "funcionario")
    private Set<MedicoEntity> medicos;


    @Enumerated(EnumType.STRING)
    private Profissao profissao;

    @Column
    private String nome;

    @Column
    private Integer idade;

    @Column(name = "data_nasciemnto", nullable = true)
    private LocalDate dataNascimento;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_FUNCIONARIO"));
    }

    @Override
    public @Nullable String getPassword() {
        return usuario != null ? usuario.getSenha() : null;
    }

    @Override
    public String getUsername() {
        return matricula;
    }
}
