package med.voll.api.domain.paciente;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;


@Getter //Crea los getters
@EqualsAndHashCode(of = "id") //
@NoArgsConstructor //Crea un constructor sin argumentos
@AllArgsConstructor //Crea un constructor con todos los campos y parametros

@Entity(name= "Paciente") //Nombre de la entidad
@Table(name ="pacientes") //Nombre de la tabla de la BD

public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //Estas anotaciones se utilizan para definir y configurar la clave primaria de una entidad en una base de datos relacional.
    private Long id;

    private String nombre;
    private String email;
    private String documentoIdentidad;
    private String telefono;

    @Embedded // Indica que direccion va a ser embebida en esta entidad
    private Direccion direccion;

    //Creacion de constructor
    public Paciente (DatosRegistroPaciente datos){
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.documentoIdentidad = datos.documentoIdentidad();
        this.direccion = new Direccion(datos.direccion());
    }




}
