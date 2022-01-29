package models;

public class Sala implements Comparable<Sala> {
    private int id;
    private final int CAPACIDADE = 80;
    private Filme filme;
    // private String[][] cadeiras;
    // int aux = 1;
    public Sala(int id, Filme filme) {
        this.id = id;
        this.filme = filme;
        // this.cadeiras = new String[(CAPACIDADE/10+1)][10+1];
        // for (int i = 0; i < cadeiras.length; i++) {
        //     for (int j = 0; j < cadeiras[0].length; j++) {
        //         if(j==0 && i==0) cadeiras[i][j] = " ";
        //         else if(i==0) cadeiras[i][j] = String.valueOf(j) + " ";
        //         else if(j==0) cadeiras[i][j] = String.valueOf(i);
        //         else {
        //             if(aux < 10) {
        //                 String out = String.format("\033[32m %d\033[0m", aux++);
        //                 cadeiras[i][j] = out;
        //             } else {
        //                 String out = String.format("\033[32m%d\033[0m", aux++);
        //                 cadeiras[i][j] = out;
        //             }
        //         }
        //     }
        // }
    }

    // public String[][] getCadeiras() {
    //     return cadeiras;
    // }

    // public void setCadeiras(String[][] cadeiras) {
    //     this.cadeiras = cadeiras;
    // }

    public Sala(int id) {
        this.id = id;
    }

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

    // public boolean adicionarPessoa(Pessoa p, int assento) {
    //     if(p.getIdade() < filme.getMinIdade()) {
    //         System.out.println("Idade não permitada. Você não pode assistir o filme");
    //         return false;
    //     }
    //     String out = "\033[32m" + assento + "\033[0m";
    //     for (int i = 1; i < cadeiras.length; i++) {
    //         for (int j = 1; j < cadeiras[i].length; j++) {
    //             if(cadeiras[i][j].contains(String.valueOf(assento))) {
    //                 cadeiras[i][j] = "\033[31m x\033[0m";
    //                 // System.out.println("Assento escolhido com sucesso!");
    //                 return true;
    //             }
    //         }
    //     }

    //     // System.out.println("Assento não disponível. Escolha outro!");
    //     return false;
    // }

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
     * inteiro negativo, se o objeto atual for menor que o argumento.
     * zero, se o objeto atual for igual ao argumento.
     * inteiro positivo, se o objeto atual for maior que o argumento.
     */
    @Override
    public int compareTo(Sala o) {
        return Integer.compare(this.id, o.getId());
    }

}
