package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController //Anotacion que indica que es un controller
@RequestMapping("/medicos") //Atotacion que indica la ruta
public class MedicoController {

    @Autowired //para fines didacticos
    private MedicoRepository medicoRepository; //atributo que indica la interfaz

    @PostMapping //recibe un mapeo de post va recibir un post envío de datos
    //metodo para registrar un medico recibe como parametro los datos (record registro medico)
    public ResponseEntity<DatosRespuestaMedico> registrarMedico (@RequestBody @Valid DatosRegistroMedico datosRegistrosMedico ,
                                                                 UriComponentsBuilder uriComponentsBuilder){ //Se manda llamar el record como parametro pero pasandolo como objeto
        Medico medico = medicoRepository.save(new Medico(datosRegistrosMedico)); //la interfaz medico repository accede al metodo save y crea una instancia nueva de medico que recibe como parametro los datos del medico
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(), medico.getEspecialidad().toString(),
                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento()));

        URI url= uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();//Crea la url como variable indicandole que el id es dinamico y no fijo
        return ResponseEntity.created(url).body(datosRespuestaMedico); //retorna creado pero con la url correspondiente

    }

    //Metodo para listar todos medicos
    @GetMapping //Va a obtener un mapeo de datos
    public ResponseEntity<Page<DatosListadoMedico>>  listadoMedico (Pageable paginacion){ //Con uso de pageable tiene diferentes metodos que se pueden colocar en el parametro para lo que se requiere
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new)); //Lista solo los medicos que se encuentren activos

    }

    //Metodo para actualizar un medico(es necesario saber el ID)
    @PutMapping //metodo put actualiza/modifica el recurso
    @Transactional //al hacer una actualizacion entra la anotacion transactional y al terminar hace un commit para actualizar
    public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id()); //Se crea una instancia de medico que va a ser igual a medico repository donde el cliente esta enviando el id
        medico.actualizarDatos(datosActualizarMedico); //de la instancia se tiene que crear el metodo actualizar datos que son los datos que se van a actualizar
        return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(), medico.getEspecialidad().toString(),

                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento())));
    }


    //Metodo para eliminar medico pero solo del listado
    @DeleteMapping("/{id}") //Mapea el elemento que se quiere eliminar
    @Transactional //Para que se tengan efecto los cambios en la base de datos
    public ResponseEntity eliminarMedico(@PathVariable Long id){ //Para manipular la respuesta http Se utiliza ResponseEntity y se retorna la repsuesta que se quiere mostrar
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

    //Metodo que retorna los datos de un medico en especial
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> retornaDatosMedico(@PathVariable Long id){ //Para manipular la respuesta http Se utiliza ResponseEntity y se retorna la repsuesta que se quiere mostrar
        Medico medico = medicoRepository.getReferenceById(id); //Instancia de medico para traer el Id de la base de datos
        //Variable que guarda los datos del medico
        var datosMedico = (new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(), medico.getEspecialidad().toString(),
                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento())));

        return ResponseEntity.ok(datosMedico); //Retorna un ok con los datos del médico
    }



    //Metodo para eliminar medico de la base de datos

    //public void eliminarMedico(@PathVariable Long id){
    //    Medico medico = medicoRepository.getReferenceById(id); //Se hace una instancia de medico y se accede al repositorio y se obtiene el id
    //    medicoRepository.delete(medico); //ya en el repositorio se accede al metodo propio delete y se elimina al medico de la base de datos
    //}


}
