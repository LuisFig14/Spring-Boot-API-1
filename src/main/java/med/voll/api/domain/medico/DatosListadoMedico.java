package med.voll.api.domain.medico;

public record DatosListadoMedico(Long id, String nombre, String especialidad, String documento, String email) {

    //constructor que recibe como parametros la entidad Medico (se va a traer de la base de datos la información)
    public DatosListadoMedico(Medico medico){
        this(medico.getId(),  medico.getNombre(), medico.getEspecialidad().toString(), medico.getDocumento(), medico.getEmail());

    }

}
