// Ønskeliste:
// 1. Slette (og oprette brugere) = OK
// 2. Login og logout = OK
// 3. Interface/menu = Struktur OK
// 4. Penge overfør og modtage = OK
// 5. Hæv penge = OK
// 6. Valuta = OK
// 7. Se saldo = OK
// 8. Sæt ind = OK
// 9. fejlmeddelelse
// 10. Ingen overtræk
// Nice to have:
// 1. Veksling.
// 2. Aktiespil
// 3. opretBruger = OK
// 4. Lav tests for brugerinput - man skal ikke kunne skrive bogstaver hvis den spørger om pinkode etc.
// 5. Bedre navngivning af variable
// 6. Skjul pinkode
// 7. Underskud på kontoen låser sletning af den
package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import static com.company.Interface.interface2;



// ****************** SPØRGSMÅL TIL ANDRAS: *********************************
// 1. hvordan kalder man main() fra en anden klasse?
// 2. hvordan clearer man terminal/run-window eller hvad det hedder?
// 3. hvornår skal man lukke scannere og readfile objekter bag sig?
// 4.
public class Main {
    public static void main(String[] args) throws IOException {
        //Login skærm:
        Bruger bruger = new Bruger();
        bruger = Login();
        // Hvis der nu findes en bruger startes interface2:
        if (bruger != null) {
            interface2(bruger);
        } else {
            // Ellers loop tilbage til start af main og prøv igen:
            main(null);
        }
        // Når bruger afslutter sit login, genstarter vi main:
        main(null);
    }

    public static void pengeoverførsel(Bruger bruger) throws IOException {
        int kontonummer = 0;
        int beløb = 0;
        // Scan for kontonummer:
        System.out.println("Hvilket kontonummer vil du overføre til: ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            kontonummer = scanner.nextInt();
        } else {
            System.out.println("Du SKAL indtaste et heltal! Prøv igen.");
        }

        // Returner nyt bruger-objekt fundet via kontonummer:
        Bruger targetBruger = new Bruger();
        targetBruger = findBrugerViaKontonummer(kontonummer);

        // Loop interface indtil beløb er modtaget:
        boolean harBeløb = false;
        myLABEL:
        while (!harBeløb) {
            System.out.println("Hvor mange penge vil du overføre?:");
            Scanner scan_beløb = new Scanner(System.in);

            // Test scan_beløb for input type:
            if (scan_beløb.hasNextInt()) {
                // Korrigér konti i Bruger Class:
                harBeløb = true;
                beløb = scan_beløb.nextInt();
                beløb = Math.abs(beløb);
                targetBruger.setSaldo(targetBruger.getSaldo() + beløb);
                bruger.setSaldo(bruger.getSaldo() - beløb);

                // Save til fil (Korrigér konti i txt filer):
                // Opret indhold af filer og sæt fil-stier
                String targetbruger_fil = targetBruger.getKontonummer() + "\n" + targetBruger.getPinkode() + "\n" + targetBruger.getSaldo();
                String bruger_fil = bruger.getKontonummer() + "\n" + bruger.getPinkode() + "\n" + bruger.getSaldo();
                String targetbruger_fil_adresse = "src/resources/" + targetBruger.getBrugernavn();
                String bruger_fil_adresse = "src/resources/" + bruger.getBrugernavn();

                // Overskriv gamle filer med nye konti:
                FileWriter myWriter = new FileWriter(bruger_fil_adresse);
                myWriter.write(bruger_fil);
                myWriter.close();
                FileWriter myWriter2 = new FileWriter(targetbruger_fil_adresse);
                myWriter2.write(targetbruger_fil);
                myWriter2.close();
            } else {
                // Fejlhåndtering - gå til label (restart spørgsmål) ved fejl:
                System.out.println("Du SKAL indtaste et heltal! Prøv igen.");
                harBeløb = false;
                continue myLABEL;
            }
            System.out.println("Din saldo er nu på: " + bruger.getSaldo());
        }
    }

    public static Bruger findBrugerViaKontonummer(int kontonummer) throws FileNotFoundException {
        // Opret array af alle brugere:
        File folder = new File("src/resources/");
        ArrayList<Bruger> mineKunder;
        mineKunder = KundeKatalog.listFilesForFolder(folder);
        // Loop alle brugere og returnér det fundne bruger-objekt:
        for (Bruger bruger : mineKunder) {
            if (bruger.getKontonummer() == kontonummer) {
                return bruger;
            }
        }
        // Ellers return ingen bruger:
        return null;
    }

    public static void hæv(Bruger bruger) throws IOException {
        // Scan beløb:
        int beløb = 0;
        Scanner scanner_beløb = new Scanner(System.in);
        // Konverér beløb til positivt tal og træk beløb fra konto:
        if (scanner_beløb.hasNextInt()) {
            beløb = Math.abs(scanner_beløb.nextInt());
            bruger.setSaldo(bruger.getSaldo() - beløb);
            System.out.println("Du har hævet: " + beløb + "\nDin saldo er nu: " + bruger.getSaldo());
        } else {
            System.out.println("Du SKAL indtaste et heltal! Prøv igen.");
            hæv(bruger);
        }
        interface2(bruger);
    }

    public static void indsæt(Bruger bruger) throws IOException {
        // Scan beløb:
        int beløb = 0;
        Scanner scanner_beløb_tilføj = new Scanner(System.in);
        // Konverér beløb til positivt tal og tilføj beløb til konto:
        if (scanner_beløb_tilføj.hasNextInt()) {
            beløb = Math.abs(scanner_beløb_tilføj.nextInt());
            bruger.setSaldo(bruger.getSaldo() + beløb);
            System.out.println("Du har indsat: " + beløb + "\nDin saldo er nu: " + bruger.getSaldo());
        } else {
            System.out.println("Du SKAL indtaste et heltal! Prøv igen.");
            indsæt(bruger);
        }
        interface2(bruger);
    }

    public static void sletBruger(Bruger bruger) {
        // Sæt filnavn = brugernavn:
        File myFile = new File("src/resources/" + bruger.getBrugernavn());
        // System.out.println(myFile.getAbsolutePath());
        // Slet bruger-fil hvis konto er >0:
        if (bruger.getSaldo() >= 0) {
            try {
                // myFile.deleteOnExit();
                if (myFile.delete()) {
                    System.out.println("Slettede filen: " + myFile.getName());
                } else {
                    System.out.println("Kunne ikke slette filen.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Du kan ikke slette din konto, sålænge saldoen er negativ! FY!");
        }
    }
    public static Bruger Login() throws FileNotFoundException {
        // Login skærm her
        System.out.println("\n**************************************");
        System.out.println("*             DAT BANKEN             *");
        System.out.println("*            LOGIN SCREEN            *");
        System.out.println("**************************************\n");
        System.out.println("Indtast dit brugernavn og tryk på Enter");

        // Modtag scanner input som brugernavn og pinkode:
        Scanner scanner = new Scanner(System.in);
        String brugernavn = scanner.nextLine();
        System.out.println("Indtast din kode og tryk på Enter");
        Scanner scanner1 = new Scanner(System.in);
        int pinkode = scanner1.nextInt();
        scanner1.nextLine();

        //Opret array af alle brugere:
        File folder = new File("src/resources/");
        ArrayList<Bruger> mineKunder;
        mineKunder = KundeKatalog.listFilesForFolder(folder);

        // Loop gennem alle brugere og validér brugernavn og pinkode:
        // Returnerer den fundne valide bruger
        for (Bruger bruger : mineKunder) {
            if (bruger.getBrugernavn().equals(brugernavn) && bruger.getPinkode() == pinkode) {
                System.out.println("Succes! Du er nu logget på som: " + brugernavn + ".\n"); // + "Din pinkode er: " + pinkode);
//                scanner.close();
//                scanner1.close();
                return bruger;
            }
        }
        System.out.println("Noget gik galt. Prøv igen");
//        scanner.close();
//        scanner1.close();
        return null;
    }
}
