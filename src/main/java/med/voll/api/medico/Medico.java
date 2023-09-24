//ENTIDAD MEDICO
package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.Direccion;

@Table(name="medicos")//nombre de la tabla
@Entity(name="Medico")//nombre de la entididad
//Con ayuda de lombok
@Getter //Crea los getters
@NoArgsConstructor//crea un constructor sin argumentos
@AllArgsConstructor //crea un constructor con todos los argumentos
@EqualsAndHashCode(of = "id")
public class Medico {

    //Creación de atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String documento;
    private boolean activo;
    private String telefono;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;


    //constructor para que cuando sea llamado el medico acepte como parametro datos registro medico
    public Medico(DatosRegistroMedico datosRegistrosMedico) {
        this.activo = true; //Al crear un nuevo medico indicará que se encuentra activo
        this.nombre= datosRegistrosMedico.nombre();
        this.email= datosRegistrosMedico.email();
        this.documento= datosRegistrosMedico.documento();
        this.telefono=datosRegistrosMedico.telefono();
        this.especialidad = datosRegistrosMedico.especialidad();
        this.direccion= new Direccion(datosRegistrosMedico.direccion());
    }

    //metodo para actualizar datos de medico
    public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {


        if(datosActualizarMedico.nombre() !=null){ //Si datos actualizar medico es diferente de null
            this.nombre = datosActualizarMedico.nombre(); // este nombre va a ser igual a datos actualizar medico nombre
        }

        if(datosActualizarMedico.documento() !=null){
            this.documento = datosActualizarMedico.documento();
        }

        if(datosActualizarMedico.direccion() != null){
            this.direccion = direccion.actualizarDatos(datosActualizarMedico.direccion());
        }

    }

    public void desactivarMedico() {
        this.activo = false;
    }
}
