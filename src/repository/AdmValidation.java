package repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class AdmValidation {
    private final static String FILENAME_ADMS = "src/database/adms.txt";

    public static boolean validation(String cpf) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME_ADMS))) {
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");
                String cpfCliente = dados[1];

                if(cpf.equals(cpfCliente)){
                    return true;
                }

                line = br.readLine();
            }

            return false;
        }catch (FileNotFoundException e) {
            return false;
        }catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
