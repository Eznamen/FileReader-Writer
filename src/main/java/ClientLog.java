import java.io.File;

public class ClientLog {
    int[] log;
    int productNum;
    int amount;

//    public ClientLog() {
//        this.log = new int[2];
//    }

    public int[] log(int productNum, int amount) {
        log = new int[] {productNum, amount};
        return log;
    }


    public void exportAsCSV(File txtFile) {
        try (CSVW ){

        }

    }
}

