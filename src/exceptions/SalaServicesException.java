package exceptions;
/**
 * Exceção utilizada pelos serviços da entidade Sala
 */
public class SalaServicesException extends Exception {
    /**
     * Exceção de serviço
     * @param message Mensagem de erro que será disparada no console
     */
    public SalaServicesException(String message){
        super(message);
    }
}
