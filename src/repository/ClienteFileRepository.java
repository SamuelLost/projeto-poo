package repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Cliente;

public class ClienteFileRepository implements IClienteRepository{

    public static final String FILENAME = "src/database/clientes.txt";

    @Override
    public boolean addCliente(Cliente cliente) {
        try(
            FileWriter filmeFile = new FileWriter(FILENAME, true);
            PrintWriter filmeWriter = new PrintWriter(filmeFile);
        ) {
            String linha = String.format("%s,%s,%d", cliente.getNome(), cliente.getCpf(), cliente.getIdade());
            filmeWriter.println(linha);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        
    }

    @Override
    public List<Cliente> getAllClientes() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            List<Cliente> clientes = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");
                String nome = dados[0];
                String cpf = dados[1];
                short idade = Short.parseShort(dados[2]);

                clientes.add(new Cliente(nome, cpf, idade));
                line = br.readLine();
            }
            Collections.sort(clientes);
            return clientes;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean updateCliente(Cliente Cliente) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeCliente(String cpf) {
        // TODO Auto-generated method stub
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            List<Cliente> clientes = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");
                String nome = dados[0];
                String cpfAux = dados[1];
                String idade = dados[2];
                if(!cpfAux.equals(cpf)) {
                    clientes.add(new Cliente(nome, cpfAux, Short.parseShort(idade)));
                }
                
                line = br.readLine();
            }

            File file = new File(FILENAME);

            Boolean deletedFile = file.delete();

            if(deletedFile){
                for(Cliente cliente: clientes){
                    this.addCliente(cliente);
                }
            }else {
                System.out.println("Não foi possível deletar o filme");
            }
            return true;

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Cliente findByCpf(String cpf) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");

                String cpfCliente = dados[1];

                if(cpf.equals(cpfCliente)){
                    String nome = dados[0];
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
