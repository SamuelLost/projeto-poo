package repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import models.Cliente;

public class ClienteFileRepository implements IClienteRepository{

    public static final String FILENAME = "src/database/clientes.txt";

    @Override
    public void addCliente(Cliente cliente) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Cliente> getAllClientes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean updateCliente(Cliente Cliente) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeCliente(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Cliente findByCpf(String cpf) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");

                String cpfCliente = dados[0];

                if(cpf.equals(cpfCliente)){
                    String nome = dados[1];
                    String idade = dados[2];

                    return new Cliente(nome, cpfCliente, Short.parseShort(idade));
                }

                line = br.readLine();
            }

            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}
