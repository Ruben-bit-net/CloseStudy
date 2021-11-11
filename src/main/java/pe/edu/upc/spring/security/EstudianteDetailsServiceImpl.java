package pe.edu.upc.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.edu.upc.spring.model.Arrendador;
import pe.edu.upc.spring.model.Estudiante;
import pe.edu.upc.spring.repository.IArrendadorRepository;
import pe.edu.upc.spring.repository.IEstudianteRepository;

@Service
public class EstudianteDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private IEstudianteRepository eEstudiante;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Estudiante estudiante = eEstudiante.findByUsername(username);
		/*Arrendador arrendador = aArrendador.findByUsername(username);*/
		UserBuilder builder = null;
		
		if(estudiante != null) {
			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(estudiante.getPassword());
			builder.authorities(new SimpleGrantedAuthority("ROLE_ESTUDIANTE"));
		}
		/*if(arrendador != null) {
			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(arrendador.getPassword());
			builder.authorities(new SimpleGrantedAuthority("ROLE_ARRENDADOR"));
		}*/
		else {
			throw new UsernameNotFoundException("El usuario no existe");
		}
		return builder.build();
	}

}
