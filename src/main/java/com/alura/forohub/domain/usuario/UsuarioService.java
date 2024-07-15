package com.alura.forohub.domain.usuario;

import com.alura.forohub.domain.perfil.Perfil;
import com.alura.forohub.domain.perfil.PerfilRepository;
import com.alura.forohub.infra.errores.ValidacionDeIntegridad;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PerfilRepository perfilRepository;

    public List<UsuarioDatosCrear> obenerListaUsuarios(){
        return usuarioRepository.findAll().stream()
                .map(u -> new UsuarioDatosCrear(u.getId(), u.getNombre(), u.getNombresPerfiles()))
                .toList();
    }

    public UsuarioDatosCrear obtenerUsuarios(UsuarioDTO usuarioDTO){
        List<Perfil> perfiles = usuarioDTO.perfiles().stream()
                .map(id -> perfilRepository.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (perfiles.isEmpty()) {
            perfilRepository.findById(2).ifPresent(perfiles::add);
        }

        Usuario usuario = new Usuario(usuarioDTO, perfiles);
        usuarioRepository.save(usuario);
        return new UsuarioDatosCrear(usuario.getId(), usuario.getNombre(),usuario.getNombresPerfiles());
    }

    public UsuarioDatosCrear obtenerUsuario(int id){
        Usuario user = usuarioRepository.getReferenceById(id);
        if(!user.isActivo()){
            throw new ValidacionDeIntegridad("el usuario no existe");
        }
        return new UsuarioDatosCrear(user.getId(), user.getNombre(), user.getNombresPerfiles());
    }
}
