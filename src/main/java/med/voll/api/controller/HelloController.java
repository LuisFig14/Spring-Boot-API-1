package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Anotacion Indica que es un controlador
@RequestMapping("/hello") //Indica la ruta
public class HelloController {

    @GetMapping //Obtiene un mapeo y ejecuta
    public String helloWorld (){
        return "Hello World! from USA";
    }

}
