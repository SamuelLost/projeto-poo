package models;

public class Ingresso implements Comparable<Ingresso> {
    private int codigo;
    private float valor;
    private Cliente cliente;
    private Sala sala;

    public Ingresso(int codigo, Cliente cliente, Sala sala, float valor) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.sala = sala;
        this.setValor(valor);
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
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
    public String toString() {
        return null;
    }

    @Override
    public int compareTo(Ingresso o) {
        // TODO Auto-generated method stub
        return Integer.compare(this.codigo, o.getCodigo());
    }

}
