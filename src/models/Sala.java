package models;

import java.util.Map;

public class Sala {
  private int id;
  private int capacidade;
  private Filme filme;
  private Map<Integer, Cliente> cadeiras;

  public Sala(int id, int capacidade, Filme filme, Map<Integer, Cliente> cadeiras) {
    this.id = id;
    this.capacidade = capacidade;
    this.filme = filme;
    this.cadeiras = cadeiras;
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

  public Map<Integer, Cliente> getCadeiras() {
    return this.cadeiras;
  }

  @Override
  public String toString() {
    return null;
  }

}
