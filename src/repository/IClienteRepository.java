package repository;

import java.util.List;

import models.Cliente;

public interface IClienteRepository {
    void addCliente(Cliente cliente);

    List<Cliente> getAllClientes();

    boolean updateCliente(Cliente Cliente);

    boolean removeCliente(int id);

    Cliente findByCpf(String cpf);
}
