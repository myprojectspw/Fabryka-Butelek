import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
        //Glowna klasa
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("What operation? ");
            System.out.println("1 - Start machine system of creating buttles ");
            System.out.println("2 - Claendar with cucamber tests ");
            int k = scanner.nextInt();
            switch(k)
            {
                case 1:
                    startMachineCreatingPetButtles();
                    break;
                case 2:
                    System.out.println("Calendar system");
                    break;
            }

        }


    public static void startMachineCreatingPetButtles() {
        Skrzynka skrzynka = new Skrzynka();

        Lock lock = new ReentrantLock();
        Condition oczekiwanie = lock.newCondition();

        MaszynaProdukujacaButelki maszyna1 = new MaszynaProdukujacaButelki(skrzynka, lock, oczekiwanie);
        MaszynaZmieniajacaButelki maszyna2 = new MaszynaZmieniajacaButelki(skrzynka, lock, oczekiwanie);

        Thread produkcja = new Thread(maszyna1, "producent");
        Thread zmieniasz = new Thread(maszyna2, "zmieniasz");

        produkcja.start();
        zmieniasz.start();
    }
}
class MaszynaZmieniajacaButelki implements Runnable {

        Skrzynka skrzynka;
        private Lock lock;
        private Condition condition;

        public MaszynaZmieniajacaButelki(Skrzynka skrzynka, Lock lock, Condition condition) {
            this.skrzynka = skrzynka;
            this.lock = lock;
            this.condition = condition;
        }

        // Watek czeka az maszyna1 wyprodukuje butelki

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread()
                        .getName()
                        + "Zaczynam przygotowanie do zmiany skrzynki");
                while (true) {
                    while (!skrzynka.jestPelna()) {
                        try {
                            condition.await();
                            System.out.println(Thread.currentThread()
                                    .getName()
                                    + "Czekam na zapelnienie");
                        } catch (InterruptedException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    System.out.println(Thread.currentThread()
                            .getName()
                            + "Zamieniam butelki");
                    skrzynka.pobierzIleButelek();
                    skrzynka.zamiana();
                    skrzynka.pobierzIleButelek();
                    System.out.println(Thread.currentThread()
                            .getName()
                            + "Skonczylem zamieniac");
                    this.condition.signalAll();
                    System.out.println(Thread.currentThread()
                            .getName()
                            + "Czekam na nowa partie");
                }
            }
            finally {
                lock.unlock();
            }
        }

    }

    class MaszynaProdukujacaButelki implements Runnable {

        private Skrzynka skrzynka;
        private Lock lock;
        private Condition condition;
        private int i = 0;

        public MaszynaProdukujacaButelki(Skrzynka skrzynka, Lock lock, Condition condition) {
            this.skrzynka = skrzynka;
            this.lock = lock;
            this.condition = condition;
        }

        // Jak wyprodukuje 10 butelek musi poczekac chwile az maszyna zmieni butelki

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread()
                        .getName()
                        + "Zaczynam produkowac butelki");
                while (true) {
                    while (skrzynka.jestPelna()) // Nie mozemy produkowac, skrzynka nam czeka
                    {
                        try {
                            System.out.println(Thread.currentThread()
                                    .getName()
                                    + "Czeba wymienic skrzynke, bo pelna");
                            condition.await();
                            System.out.println(Thread.currentThread()
                                    .getName()
                                    + "Wracamy do produkcji butelek");
                        } catch (InterruptedException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    System.out.println(Thread.currentThread()
                            .getName()
                            + "Wyprodukowalem: "
                            + (++i)
                            + " buteleczke");
                    skrzynka.dodaj(new Butelka());

                    condition.signalAll();
                }
            }
            finally {
                lock.unlock();
            }
        }

    }

    class Skrzynka {

        private final int POJEMNOSC = 10;
        private ArrayList lista = new ArrayList(POJEMNOSC);

        public synchronized boolean jestPelna() {
            if (lista.size() == POJEMNOSC) {
                return true;
            } else {
                return false;
            }
        }

        public synchronized void dodaj(Butelka butelka) {
            lista.add(butelka);

        }

        public synchronized int pobierzIleButelek() {
            System.out.println(Thread.currentThread()
                    .getName()
                    + ": Aktualnie jest "
                    + this.lista.size());
            return this.lista.size();
        }

        public synchronized void zamiana() {
            System.out.println("Zamieniam skrzynki");
            lista.clear();
        }

    }
    class Butelka {

    }
