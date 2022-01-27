package models;

public class Sala implements Comparable<Sala> {
    private int id;
    private int capacidade;
    private Filme filme;

    public Sala(int id, int capacidade, Filme filme) {
        this.id = id;
        this.capacidade = capacidade;
        this.filme = filme;
    }

    public Sala(int id, int capacidade) {
        this.id = id;
        this.capacidade = capacidade;
    }

    public int getId() {
        return id;
    }

    public int getCapacidade() {
        return this.capacidade;
    }

    public Filme getFilme() {
        return this.filme;
    }
    
    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    @Override
    public String toString() {
        String out = "==========================\n";
        out += "Sala: " + this.id + "\n";
        out += "Capacidade: " + this.capacidade + "\n";
        
        if(this.filme != null) {
            out += "Filme: " + this.filme.getNome() + "\n";
        }else{
            out += "Filme: Sala sem filme\n";
        }

        return out;
    }

    /**
     * inteiro negativo, se o objeto atual for menor que o argumento.
     * zero, se o objeto atual for igual ao argumento.
     * inteiro positivo, se o objeto atual for maior que o argumento.
     */
    @Override
    public int compareTo(Sala o) {
        return Integer.compare(this.id, o.getId());
    }

}
