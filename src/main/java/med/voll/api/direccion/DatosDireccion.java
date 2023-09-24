//DTO
//Aqui se deben generar las validaciones

package med.voll.api.direccion;

import jakarta.validation.constraints.NotBlank;

public record DatosDireccion(

        @NotBlank //Validacion de que no debe de ser null ni llegar en blanco
        String calle,
        @NotBlank
        String distrito,
        @NotBlank
        String ciudad,
        @NotBlank
        String numero,
        @NotBlank
        String complemento ) {
}
