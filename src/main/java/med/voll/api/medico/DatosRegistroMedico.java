//DTO
//Es aquí donde se deben crear las validaciones
package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.direccion.DatosDireccion;

public record DatosRegistroMedico(

        @NotBlank //Que no sea null y no llegue en blanco
        String nombre,
        @NotBlank //Que no sea null y no llegue en blanco
        @Email  //Que acepte formato email
        String email,
        @NotNull
        String telefono,
        @NotBlank //Que no sea null y no llegue en blanco
        @Pattern(regexp = "\\d{4,6}") //Acepta de 4 a 6 caracteres
        String documento,
        @NotNull //Que no sea null y que no llegue en blanco
        Especialidad especialidad,
        @NotNull //Como es un objeto la validacion debe de ser no null
        @Valid //Como es un objeto se le inidica que tod0 lo que contiene debe debe de ser validado
        DatosDireccion direccion)  {


}
