package exceptions;
/**
 * Exceção utilizada pelos serviços da entidade cliente
 */
public class ClienteServicesException extends Exception{
    /**
     * Exceção de serviço
     * @param message Mensagem de erro que será disparada no console
     */
    public ClienteServicesException(String message){
        super(message);
    }
}