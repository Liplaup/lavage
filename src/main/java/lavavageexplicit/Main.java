package lavavageexplicit;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        // Créer un tableau de verrous pour les fourchettes
        Lock[] forks = new Lock[5];
        for (int i = 0; i < 5; i++) {
            forks[i] = new ReentrantLock();
        }

        // Créer les philosophes
        Philosophe[] philosophes = new Philosophe[5];
        for (int i = 0; i < 5; i++) {
            philosophes[i] = new Philosophe(i, forks[i], forks[(i + 1) % 5]);
            philosophes[i].start();
        }

        // Attendre la frappe d'une touche pour interrompre les threads
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Arrêter tous les philosophes
        for (int i = 0; i < 5; i++) {
            philosophes[i].interrupt();
        }
    }
}
