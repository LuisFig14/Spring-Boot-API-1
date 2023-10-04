package med.voll.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //Intercepta las llamadas en caso de que suceda alguna exception

public class TratadorDeErrores {

    //Tratar error 404
    @ExceptionHandler(EntityNotFoundException.class)  //Con la notacion exeption se le indica que puede recibir una exception en el metodo
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    //Tratar error 400
    @ExceptionHandler(MethodArgumentNotValidException.class) //indica que va a recibir una excepcion
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){ //se le pasa como parametro la exception

        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList(); //Lista los errores de datos error validacion

        return ResponseEntity.badRequest().body(errores);
    }

    //DTO para capturar los datos del error de validacion
    private record DatosErrorValidacion(String campo, String error){
        //Constructor que tiene los errores de los campos
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }

    }


}
