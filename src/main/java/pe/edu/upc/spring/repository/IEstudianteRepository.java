package pe.edu.upc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.spring.model.Estudiante;

@Repository
public interface IEstudianteRepository extends JpaRepository<Estudiante, Integer> {
	public Estudiante findByUsername(String username);
	/*@Query("from Estudiante e where e.nomEstudiante like %:nomEstudiante%")
	List<Estudiante> buscarNombre(@Param("nomEstudiante") String nomEstudiante);*/
}
