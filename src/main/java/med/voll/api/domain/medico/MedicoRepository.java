package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//extends de JPA Repository <Objeto a guardar - tipo de objeto de ID>
public interface MedicoRepository extends JpaRepository<Medico , Long>{

    Page<Medico> findByActivoTrue(Pageable paginacion); //Lista los medicos que se encuentran activos (los elimina de manera logica pero no de la base de datos)
}
