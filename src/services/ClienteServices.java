package services;

import java.util.List;
import java.util.Scanner;

import exceptions.ClienteServicesException;
import models.Cliente;
import models.Estudante;
import repository.IClienteRepository;
import repository.IEstudanteRepository;
import utils.Console;

public class ClienteServices {

    Scanner sc = new Scanner(System.in);

    IClienteRepository clientesRepository;
    IEstudanteRepository estudantesRepository;

    public ClienteServices(
        IClienteRepository clientesRepository,
        IEstudanteRepository estudantesRepository
    ) {
        this.clientesRepository = clientesRepository;
        this.estudantesRepository = estudantesRepository;
    }

    public void addCliente() throws ClienteServicesException{
        System.out.print("Digite o cpf do cliente: ");
        String cpf = sc.nextLine();

        // Buscar cliente pelo cpf
        Cliente clienteAlreadyExist = clientesRepository.findByCpf(cpf);

        if (clienteAlreadyExist != null) {
            throw new ClienteServicesException("O cliente com cpf "+ cpf +" já existe.");
        }

        System.out.print("Digite o nome do cliente: ");
        String nome = sc.nextLine();

        System.out.print("Digite o idade do cliente: ");
        String idade = sc.nextLine();

        System.out.print("Você é estudante? (s/n): ");
        String isEstudante = sc.nextLine();

        if(isEstudante.toLowerCase().equals("s")){
            System.out.print("Digite a sua matricula: ");
            String matricula = sc.nextLine();

            List<String> siglas = this.estudantesRepository.findAllSiglas();
            System.out.println("==========================");
            
            for(String sigla : siglas){
                System.out.println("- "+ sigla);
            }

            System.out.print("Digite a sigla da sua universidade: ");
            String sigla = sc.nextLine();

            Estudante estudante = new Estudante(nome,cpf, Short.parseShort(idade), matricula, sigla.toUpperCase());
          
            boolean successCreatedEstudante = this.estudantesRepository.addEstudante(estudante);
    
            if(successCreatedEstudante){
                System.out.println("Estudante adicionado com sucesso!");
            }else {
                System.out.println("Não foi possível adicionar o estudante!");
            }

        }else {

            Cliente cliente = new Cliente(nome, cpf, Short.parseShort(idade));

            boolean successCreatedCliente = this.clientesRepository.addCliente(cliente);

            if(successCreatedCliente){
                System.out.println("Cliente adicionado com sucesso!");
            }else {
                System.out.println("Não foi possível adicionar o cliente!");
            }
        }

    }

    public void getAllClientes() {
        List<Cliente> clientes = clientesRepository.getAllClientes();
        // clientes.sort(null);
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    public void removeCliente() {
        this.getAllClientes();
        System.out.print("\nDigite o CPF do cliente que deseja remover: ");
        String cpfCliente = sc.nextLine();

        boolean removedCliente = this.clientesRepository.removeCliente(cpfCliente);

        if(removedCliente){
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Não foi possível remover o cliente!");
        }
    }

    public void updateCliente() throws ClienteServicesException{
        Cliente cliente = null;
        boolean updatedSuccessCliente = false;

        this.getAllClientes();
        
        System.out.println("Digite o CPF do cliente: ");
        String cpfCliente = sc.nextLine();

        cliente = this.clientesRepository.findByCpf(cpfCliente);
        
        if(cliente == null){
            throw new ClienteServicesException("Filme não encontrado!");
        }

        Console.clear();
        System.out.println("Qual informação deseja alterar?");
        System.out.println("1 - Nome do cliente");
        System.out.println("2 - Idade do cliente");
        String opcao = sc.nextLine();

        System.out.print(mapUpdatedClienteMessage(Integer.parseInt(opcao)));
        String valueOp = sc.nextLine();
    
        switch (Integer.parseInt(opcao)) {
            case 1:
                cliente.setNome(valueOp);
                updatedSuccessCliente = this.clientesRepository.updateCliente(cliente);
                this.statusUpdatedFilme(updatedSuccessCliente, "Nome");
                break;
            case 2:
                cliente.setIdade(Short.parseShort(valueOp));
                updatedSuccessCliente = this.clientesRepository.updateCliente(cliente);
                this.statusUpdatedFilme(updatedSuccessCliente, "Idade");
                break;
            default:
                break;
        }
    }

    private String statusUpdatedFilme(Boolean updatedSuccessFilme, String field){
        if(updatedSuccessFilme){
            return "O campo " + field + " foi atualizado com sucesso!";
        } else {
            return "Não foi possível atualizar o campo " + field + "!";
        }
    }

    private String mapUpdatedClienteMessage(int op){
        if(op == 1){
            return "Digite o novo nome do cliente: ";
        }else{
            return "Digite a nova idade do cliente: ";
        }
    }
}
