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
            IEstudanteRepository estudantesRepository) {
        this.clientesRepository = clientesRepository;
        this.estudantesRepository = estudantesRepository;
    }

    public void addCliente() throws ClienteServicesException {
        String cpf = "";
        do {
            System.out.print("Digite o CPF do cliente: ");
            cpf = sc.nextLine();
            if (cpf.length() != 11)
                System.out.println("CPF inválido, faltando caracteres");
        } while (cpf.length() != 11);

        // Buscar cliente pelo cpf
        Cliente clienteAlreadyExist = clientesRepository.findByCpf(cpf);

        if (clienteAlreadyExist != null) {
            throw new ClienteServicesException("O cliente com cpf " + cpf + " já existe.");
        }

        System.out.print("Digite o nome do cliente: ");
        String nome = sc.nextLine();

        System.out.print("Digite o idade do cliente: ");
        String idade = sc.nextLine();

        System.out.print("Você é estudante? (s/n): ");
        String isEstudante = sc.nextLine();

        if (isEstudante.toLowerCase().equals("s")) {
            System.out.print("Digite a sua matricula: ");
            String matricula = sc.nextLine();

            List<String> siglas = this.estudantesRepository.findAllSiglas();
            System.out.println("==========================");

            for (String sigla : siglas) {
                System.out.println("- " + sigla);
            }

            System.out.print("Digite a sigla da sua universidade: ");
            String sigla = sc.nextLine();

            Estudante estudante = new Estudante(nome, cpf, Short.parseShort(idade), matricula, sigla.toUpperCase());

            boolean successCreatedEstudante = this.estudantesRepository.addEstudante(estudante);

            if (successCreatedEstudante) {
                System.out.println("Estudante adicionado com sucesso!");
            } else {
                System.out.println("Não foi possível adicionar o estudante!");
            }

        } else {

            Cliente cliente = new Cliente(nome, cpf, Short.parseShort(idade));

            boolean successCreatedCliente = this.clientesRepository.addCliente(cliente);

            if (successCreatedCliente) {
                System.out.println("Cliente adicionado com sucesso!");
            } else {
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

        if (removedCliente) {
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Não foi possível remover o cliente!");
        }
    }

    public void updateCliente() throws ClienteServicesException {
        Cliente cliente = null;
        boolean updatedSuccessCliente = false;

        this.getAllClientes();

        System.out.println("Digite o CPF do cliente: ");
        String cpfCliente = sc.nextLine();

        cliente = this.clientesRepository.findByCpf(cpfCliente);

        if (cliente == null) {
            throw new ClienteServicesException("Cliente não encontrado!");
        }
        
        Console.clear();
        if(cliente instanceof Estudante) System.out.println("Uiiii");
        System.out.println("Qual informação deseja alterar?");
        System.out.println("1 - Nome do cliente");
        System.out.println("2 - Idade do cliente");
        System.out.println("3 - CPF do cliente");
        System.out.println("4 - Matrícula do estudante");
        System.out.println("5 - Faculdade do estudante");
        String opcao = sc.nextLine();

        System.out.print(mapUpdatedClienteMessage(Integer.parseInt(opcao)));
        String valueOp = sc.nextLine();

        switch (Integer.parseInt(opcao)) {
            case 1:
                cliente.setNome(valueOp);
                updatedSuccessCliente = this.clientesRepository.updateCliente(cliente);
                this.statusUpdatedCliente(updatedSuccessCliente, "Nome");
                break;
            case 2:
                cliente.setIdade(Short.parseShort(valueOp));
                updatedSuccessCliente = this.clientesRepository.updateCliente(cliente);
                this.statusUpdatedCliente(updatedSuccessCliente, "Idade");
                break;
            case 3:
                cliente.setCpf(valueOp);
                updatedSuccessCliente = this.clientesRepository.updateCliente(cliente);
                this.statusUpdatedCliente(updatedSuccessCliente, "CPF");
                break;
            case 4: {
                // Olhar isso aqui
                Estudante a = (Estudante) cliente;
                a.setMatricula(valueOp);
                updatedSuccessCliente = this.clientesRepository.updateCliente(a);
                this.statusUpdatedCliente(updatedSuccessCliente, "Matrícula");
                break;
            }
            case 5: {
                // Olhar isso aqui
                Estudante a = (Estudante) cliente;
                a.setSiglaFaculdade(valueOp.toUpperCase());
                updatedSuccessCliente = this.clientesRepository.updateCliente(a);
                this.statusUpdatedCliente(updatedSuccessCliente, "Sigla");
                break;
            }
            default:
                break;
        }
    }

    private String statusUpdatedCliente(Boolean updatedSuccessCliente, String field) {
        if (updatedSuccessCliente) {
            return "O campo " + field + " foi atualizado com sucesso!";
        } else {
            return "Não foi possível atualizar o campo " + field + "!";
        }
    }

    private String mapUpdatedClienteMessage(int op) {
        if (op == 1) {
            return "Digite o novo nome do cliente: ";
        } else if (op == 2) {
            return "Digite a nova idade do cliente: ";
        } else if (op == 3) {
            return "Digite o novo CPF: ";
        } else if (op == 4) {
            return "Digite a nova matrícula: ";
        } else {
            return "Digite a nova faculdade: ";
        }
    }
}
