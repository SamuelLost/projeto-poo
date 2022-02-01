package exceptions;
/**
 * Exceção utilizada pelos serviços da entidade filme
 */
public class FilmeServicesException extends Exception {
    /**
     * Exceção de serviço
     * @param message Mensagem de erro que será disparada no console
     */
    public FilmeServicesException(String message){
        super(message);
    }
}