package lavavageexplicit;

import java.util.concurrent.locks.Lock;

class Philosophe extends Thread {
    private final Lock leftFork;
    private final Lock rightFork;
    private final int id;

    Philosophe(int id, Lock leftFork, Lock rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        while (true) {
            try {
                penser();
                manger();
            } catch (InterruptedException ex) {
                break; // Le thread se termine si il est interrompu
            }
        }
        System.out.println("Philosophe " + id + " a terminé");
    }

    private void penser() throws InterruptedException {
        System.out.println("Philosophe " + id + " pense.");
        sleep(1000); // Philosophe en train de penser
    }

    private void manger() throws InterruptedException {
        // Chaque philosophe doit d'abord acquérir le verrou sur les deux fourchettes
        // L'ordre de prise des fourchettes est important pour éviter l'interblocage

        if (id % 2 == 0) {
            // Pour éviter l'interblocage, on prend d'abord la fourchette de gauche
            leftFork.lock();
            rightFork.lock();
        } else {
            // Sinon, on prend d'abord la fourchette de droite
            rightFork.lock();
            leftFork.lock();
        }

        try {
            System.out.println("Philosophe " + id + " mange.");
            sleep(2000); // Philosophe en train de manger
        } finally {
            // Relâcher les verrous une fois que le repas est terminé
            leftFork.unlock();
            rightFork.unlock();
        }
    }
}
