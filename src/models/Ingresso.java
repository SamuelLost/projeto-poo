package models;

public class Ingresso implements Comparable<Ingresso> {
    private int codigo; // código do ingresso
    private float valor; // valor
    private Cliente cliente; // cliente a quem o ingresso pertence
    private Sala sala; // sala que o ingresso pertence

    /**
     * Construtor para a classe Ingresso
     * @param codigo - código do ingresso
     * @param cliente - cliente a quem corresponde o ingresso
     * @param sala - sala do ingresso
     * @param preco - preco do ingresso
     */
    public Ingresso(int codigo, Cliente cliente, Sala sala, float valor) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.sala = sala;
        this.setValor(valor);
    }

    /**
     * GETTERS e SETTERS
     */
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

    /**
     * Override do método compareTo() da interface Comparable.
     * Esse método é sobrescrito para que possa ser possível a ordenação
     * de objetos Ingresso. A comparação e ordenação é feita a partir 
     * de seus códigos usando o próprio compare() da classe <code>Integer<code>.
     *  
     * @param o - objeto ingresso
     * @return um <code>integer<code> representando a comparação. 
     * 0 - se for igual;
     * menor que 0 se o código do objeto for menor numericamente que o do objeto passado por argumento;
     * maior que 0 se o código do objeto for maior numericamente que o do objeto passado por argumento.
     */
    @Override
    public int compareTo(Ingresso o) {
        // TODO Auto-generated method stub
        return Integer.compare(this.codigo, o.getCodigo());
    }

}
