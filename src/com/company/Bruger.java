package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Bruger {
    String brugernavn;
    int saldo;
    int pinkode;
    int kontonummer;


    public Bruger() {}

    public Bruger(String brugernavn, int saldo, int pinkode, int kontonummer) {
        this.brugernavn = brugernavn;
        this.saldo = saldo;
        this.pinkode = pinkode;
        this.kontonummer = kontonummer;
    }

    public static Bruger LoadBruger(String brugernavn) throws FileNotFoundException {
        File myObj = new File("src/resources/" + brugernavn);
        // File myObj = new File(brugernavn);
        Scanner myReader = new Scanner(myObj);
        int kontonummer = 0;
        int pinkode = 0;
        int saldo=0;

        kontonummer = myReader.nextInt();
        myReader.nextLine();
        pinkode = myReader.nextInt();
        myReader.nextLine();
        saldo = myReader.nextInt();
        myReader.close();
        Bruger nyBruger = new Bruger(brugernavn,saldo,pinkode,kontonummer);
        return nyBruger;
    }
    // Opret ny bruger funktion. Skal kunne oprette txt-filer.
    // Slet bruger funktion. Skal kunne slette txt-filer.
    // Udskriv alle brugernavne.
    // Ændre password.
    // Tror måske ikke vi har brug for kontonummer? Eller måske til når man skal overføre til anden person?

    public String getBrugernavn() {
        return brugernavn;
    }

    public void setBrugernavn(String brugernavn) {
        this.brugernavn = brugernavn;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getPinkode() {
        return pinkode;
    }

    public void setPassword(String password) {
        this.pinkode = pinkode;
    }

    public long getKontonummer() {
        return kontonummer;
    }

    public void setKontonummer(int kontonummer) {
        this.kontonummer = kontonummer;
    }

}
