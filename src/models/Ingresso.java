package models;

public class Ingresso {
    private int codigo;
    private Cliente cliente;
    private Sala sala;

    public Ingresso(int codigo, Cliente cliente, Sala sala) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.sala = sala;
    }
 
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public String toString(){
        return null;
    }
    
}
