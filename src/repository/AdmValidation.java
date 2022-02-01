// Declaração da classe AdmValidation com o método validation
package repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Está classe é responsável por realizar uma verificação se o usuário é um
 * administrador.
 */
public class AdmValidation {

    // Constante que armazena o caminho do arquivo
    private final static String FILENAME_ADMS = "src/database/adms.txt";

    /**
     * Método que realiza uma verificação no CPF passado como parâmetro,
     * comparando com os registros no arquivo adms.txt
     * 
     * @param cpf Cpf que será utilizado na verificação se o usuário é um adm.
     * @return Retorna <code>True</code> se é um adm e <code>False</code> caso
     *         contrário.
     */
    public static boolean validation(String cpf) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME_ADMS))) {
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");
                String cpfCliente = dados[1];

                if (cpf.equals(cpfCliente)) {
                    return true;
                }

                line = br.readLine();
            }

            return false;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
