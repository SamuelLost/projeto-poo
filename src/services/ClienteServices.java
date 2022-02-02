package services;

import java.util.List;
import java.util.Scanner;

import exceptions.ClienteServicesException;
import models.Cliente;
import models.Estudante;
import repository.IClienteRepository;
import repository.IEstudanteRepository;
import utils.Console;
/**
 * Classe de serviços relacionados a entidade de cliente
 */
public class ClienteServices {

    Scanner sc = new Scanner(System.in);

    private IClienteRepository clientesRepository;
    private IEstudanteRepository estudantesRepository;

    /**
     * Construtor ClienteServices
     * @param clientesRepository repositório de clientes
     * @param estudantesRepository repositório de estudantes
     */
    public ClienteServices(
            IClienteRepository clientesRepository,
            IEstudanteRepository estudantesRepository) {
        this.clientesRepository = clientesRepository;
        this.estudantesRepository = estudantesRepository;
    }

    /**
     * Método de serviço que obtém os dados do usuário e repassa para o repositório
     * 
     * @see repository.IClienteRepository#findByCpf(String)
     * @see repository.IClienteRepository#addCliente(Cliente)
     * @see repository.IEstudanteRepository#findAllSiglas()
     * @see repository.IEstudanteRepository#addEstudante(Estudante)
     * 
     * @throws ClienteServicesException Exceção no caso de tentar adicionar 
     * um cliente com um CPF já existente na base de dados.
     */
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
    
    /**
     * Método de serviço responsável por obter todos os clientes do repositório
     * 
     * @see repository.IClienteRepository#getAllClientes()
     */
    public void getAllClientes() {
        List<Cliente> clientes = clientesRepository.getAllClientes();
        // clientes.sort(null);
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    /**
     * Método de serviço responsável por chamar a operação de remoção no repositório
     * passando o cpf do cliente a ser removido.
     * 
     * @see repository.IClienteRepository#removeCliente(String)
     */
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

    /**
     * Método de serviço para atualização das informações de um cliente
     * 
     * @see #getAllClientes()
     * @see repository.IClienteRepository#findByCpf(String)
     * @see repository.IClienteRepository#updateCliente(Cliente)
     * 
     * 
     * @throws ClienteServicesException Exceção no caso de atualizar um cliente com o CPF inexistente.
     */
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
                Estudante a = (Estudante) cliente;
                a.setMatricula(valueOp);
                updatedSuccessCliente = this.clientesRepository.updateCliente(a);
                this.statusUpdatedCliente(updatedSuccessCliente, "Matrícula");
                break;
            }
            case 5: {
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

    /**
     * Método utilizado para imprimir uma mensagem de sucesso ou fala de acordo com 
     * a informação que foi atualizada do cliente
     * 
     * @param updatedSuccessCliente Será <code>True<code> se a atualização foi concluída com sucesso
     * e caso contrário será <code>False<code>
     * @param field O campo que foi atualizado
     * @return Retorna uma mensagem de sucesso ou falha formatada
     */
    private String statusUpdatedCliente(Boolean updatedSuccessCliente, String field) {
        if (updatedSuccessCliente) {
            return "O campo " + field + " foi atualizado com sucesso!";
        } else {
            return "Não foi possível atualizar o campo " + field + "!";
        }
    }

    /**
     * Método que realiza o mapeamento da opção escolhida pelo usuário
     * relacionado ao compo que será atualizado
     * @param op Opção escolhida pelo usuário
     * @return Retorna uma mensagem
     */
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
