package repository;

import java.util.List;

import models.Cliente;
import models.Pessoa;

public interface IClienteRepository {
    boolean addCliente(Cliente cliente);

    List<Cliente> getAllClientes();

    boolean updateCliente(Cliente Cliente);

    boolean removeCliente(String cpf);

    Cliente findByCpf(String cpf);
}
