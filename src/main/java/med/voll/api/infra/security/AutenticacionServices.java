package med.voll.api.infra.security;


import med.voll.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //Se indica que es un servicio
//nombre de la clase que implementa de de una interfaz
public class AutenticacionServices implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository; //Se instancia la intefaz repository

    @Override //se sobre escribe el metodo
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username); //retorna el usuario por nombre
    }



}
