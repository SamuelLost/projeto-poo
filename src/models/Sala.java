package models;

public class Sala implements Comparable<Sala> {

    private int id; // id da sala
    private final int CAPACIDADE = 80; // capacidade
    private Filme filme; // filme que a sala vai passar

    /**
     * Construtor para a classe sala
     * @param id - id da sala
     * @param filme - filme da sala
     */
    public Sala(int id, Filme filme) {
        this.id = id;
        this.filme = filme;
    }

    /**
     * Sobrecarga para o construtor quando a sala não tiver filme
     * @param id - id da sala
     */
    public Sala(int id) {
        this.id = id;
    }

    /**
     * GETTERS e SETTERS
     */
    public int getId() {
        return id;
    }

    public int getCapacidade() {
        return this.CAPACIDADE;
    }

    public Filme getFilme() {
        return this.filme;
    }
    
    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    /**
     * Imprimindo as informações da sala
     */
    @Override
    public String toString() {
        String out = "==========================\n";
        out += "Sala: " + this.id + "\n";
        out += "Capacidade: " + this.CAPACIDADE + "\n";
        
        if(this.filme != null) {
            out += "Filme: " + this.filme.getNome() + "\n";
        }else{
            out += "Filme: Sala sem filme\n";
        }

        return out;
    }

    /**
     * Override do método compareTo() da interface Comparable.
     * Esse método é sobrescrito para que possa ser possível a ordenação
     * de objetos Sala. A comparação e ordenação é feita a partir 
     * de seus ids usando o próprio compare() da classe <code>Integer<code>.
     *  
     * @param o - objeto sala
     * @return um <code>integer<code> representando a comparação. 
     * 0 - se for igual;
     * menor que 0 se o id do objeto for menor numericamente que do objeto passado por argumento;
     * maior que 0 se o id do objeto for maior numericamente que do objeto passado por argumento.
     */
    @Override
    public int compareTo(Sala o) {
        return Integer.compare(this.id, o.getId());
    }

}
