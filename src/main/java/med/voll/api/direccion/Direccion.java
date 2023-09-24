//Entidad direccion
package med.voll.api.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Con ayuda de lombok
@Getter //Crea los getters
@NoArgsConstructor//crea un constructor sin argumentos
@AllArgsConstructor //crea un constructor con todos los argumentos
@Embeddable //Indica que esta clase puede ser embebida
public class Direccion {

    private String calle;
    private String numero;
    private String complemento;
    private String distrito;
    private String ciudad;


    public Direccion(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.numero = direccion.numero();
        this.complemento = direccion.complemento();
        this.distrito = direccion.distrito();
        this.ciudad = direccion.ciudad();
    }

    public Direccion actualizarDatos(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.numero = direccion.numero();
        this.complemento = direccion.complemento();
        this.distrito = direccion.distrito();
        this.ciudad = direccion.ciudad();

        return this;
    }
}
