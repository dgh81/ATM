package com.company;
import java.io.IOException;
import java.util.Scanner;

import static com.company.Main.*;
import static com.company.Main.*;
public class Interface {
    public static void interface2(Bruger bruger) throws IOException {
        Scanner scanInter = new Scanner(System.in);
        System.out.printf("%4s%19s%25s%29s\n%s%18s%27s%38s\n",
                "1==saldo",
                "2==hæv penge",
                "3==sæt penge",
                "4==overføre",
                "5==valuta",
                "6==ny bruger",
                "7==slet bruger",
                "afslut==gem og afslut\n"/*,"help==commandlist"*/);
        System.out.println("Vælg et nummer og tryk på Enter");
        String command= scanInter.nextLine();

        //saldo
        if ("1".equals(command)) {
            System.out.println("Din saldo er på: " + bruger.getSaldo());
            interface2(bruger);
        }

        //hæv penge
        else if ("2".equals(command)) {
            System.out.println("Hvor mange penge vil du hæve?");
            hæv(bruger);
        }

        //sæt penge ind
        else if ("3".equals(command)) {
            System.out.println("Hvor meget vil du indsætte?");
            indsæt(bruger);
        }
        //overføre penge
        else if ("4".equals(command)) {
            pengeoverførsel(bruger);
            interface2(bruger);
        }
        //valuta
        else if ("5".equals(command)) {
            int beløb_dollar = 0;
            beløb_dollar = bruger.getSaldo() / 7;
            System.out.println("Beløb i dollars: " + beløb_dollar + "\n");
            interface2(bruger);
        }
        //new bruger
        else if ("6".equals(command)) {
            System.out.println("Du kan desværre ikke oprette en ny bruger ved automaten. \nGå ind i banken, så hjælper vi dig gerne.\n");
            interface2(bruger);
        }
        //delete bruger

        else if ("7".equals(command)) {
            int i=0;
            while (i<5) {
                System.out.println("Er du sikker på du vil slette? ja eller nej");
                String command7 = scanInter.nextLine();
                if ("ja".equals(command7)) {
                    i = 5;
                    sletBruger(bruger);
                } else if ("nej".equals(command7)) {

                    System.out.println("Jeg registrerede at du skrev NEJ!");

                    i = 5;
                    interface2(bruger);
                } else {
                    i = 0;
                    System.out.println("Du SKAL skrive ja eller nej! Prøv igen.");
                }
            }
        }
        //save and close
        else if ("afslut".equals(command)) {
            System.out.println("er du sikker på vil afslutte\n ja eller nej");

            //mangler link to log in
            // command eller command?
            String command9 = scanInter.nextLine();
            if ("ja".equals(command9)) {
                System.out.println("Du logger nu af.");
                main(null);
            } else if("nej".equals(command9)) {
                interface2(bruger);
            }
        } else {
            System.out.println("Kommandoen " + command + " findes ikke!");
            interface2(bruger);
        }
    }

}
/*
         1         2         3         4         6
12345678901234567890123456789012345678901234567890
 */