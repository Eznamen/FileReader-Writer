import au.com.bytecode.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientLog {

    private int productNum;
    private int amount;
    private List<int[]> logg;

    public ClientLog() {
        this.productNum = productNum;
        this.amount = amount;
        this.logg = new ArrayList<>();
    }

    public void log(int productNum, int amount) {
        logg.add(new int[]{productNum, amount});
    }

    public void exportAsCSV(File txtFile) throws IOException {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(txtFile), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_ESCAPE_CHARACTER)) {
            csvWriter.writeNext(new String[]{"productNum, amount"});
            for (int[] el : logg) {
                String rrr = Arrays.toString(el).replaceAll("^\\[|\\]$", "");
                csvWriter.writeNext(rrr.split(","));
            }
        }
    }
}