package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
public class KundeKatalog extends Bruger {
    public KundeKatalog() {}

    public static ArrayList<Bruger> listFilesForFolder(final File folder) throws FileNotFoundException {
        int i = 0;
        // Opret array af alle bruger-objekter udfra bruger-filer:
        ArrayList<Bruger> alleKunder = new ArrayList<Bruger>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                // Hvis en fil i mappen er en mappe, så restart funktionen:
                listFilesForFolder(fileEntry);
            } else {

                // Hvis der findes filer, opret da bruger-objekter og tilføj dem til kataloget:

                Bruger bruger = LoadBruger(fileEntry.getName());
                alleKunder.add(i, bruger);
                i++;
            }
        }
        // Return arraylist af bruger-objekter:
        return alleKunder;
    }
}
/*
        public static void loadAlleBrugere() throws FileNotFoundException {
        // ArrayList<Brugere> alleKunder = new ArrayList<Brugere>();
        File folder = new File ("src/resources/");
        listFilesForFolder(folder);
    }
*/