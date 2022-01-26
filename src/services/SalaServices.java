package services;

import java.util.Scanner;

import models.Sala;
import repository.ISalaRepository;

public class SalaServices {
    
    private ISalaRepository salasRepository;

    Scanner sc = new Scanner(System.in);

    public SalaServices(ISalaRepository salasRepository) {
        this.salasRepository = salasRepository;
    }
    
    public boolean adicionaSala() {
        // if (salas.size() == 3) {
        //     return false;
        // }

        System.out.print("Digite o id da sala: ");
        String salaId = sc.nextLine();
        System.out.print("Digite a capacidade da sala: ");
        String capacidade = sc.nextLine();
        Sala sala = new Sala(Integer.parseInt(salaId), Integer.parseInt(capacidade));
        this.salasRepository.addSala(sala);
        System.out.println("Sala adicionada com sucesso!");
        return true;
    }
}