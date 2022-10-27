
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        File file = new File("src/main/basket.txt");
        File txtFile = new File("src/main/log.csv");
        File jsonFile = new File("basket.json");
        Scanner scanner = new Scanner(System.in);
        Basket market = new Basket(new String[]{"Milk", "eggs", "bread", "cheese"}, new int[]{90, 140, 60, 300});
        ClientLog clientLog = new ClientLog();
        if (jsonFile.exists()) {
//            Basket.loadFromTxtFile(file);

            JSONParser jsonParser = new JSONParser();
            Object obj = null;
            try {
                obj = jsonParser.parse(new FileReader(jsonFile));
            } catch (IOException e) {
                System.out.println("file was not found ");
            }
            System.out.println(obj);
        } else {

            while (true) {

                System.out.println("Ассортимент");
                for (int i = 0; i < market.getProducts().length; i++) {
                    System.out.println((i + 1) + " " + market.getProducts()[i] + " " + market.getPrices()[i] + " " + "$/шт");
                }
                System.out.println("Введите номер продукта и кол-во через пробел; для окончания покупок введите end:");
                String input = scanner.nextLine();
                if ("end".equals(input)) {
                    break;
                }

                String[] parts = input.split(" ");
                int p1 = Integer.parseInt(parts[0]);
                int p2 = Integer.parseInt(parts[1]);

                clientLog.log(p1, p2);
                market.addToCart(p1 - 1, p2);
            }

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("amount", Arrays.toString(market.getBasket()));
            jsonObject.put("added products", Arrays.toString(market.getAddedProd()));
            jsonObject.put("prices", Arrays.toString(market.getPrices()));
            jsonObject.put("products", Arrays.toString(market.getProducts()));

            try (FileWriter fileJ = new FileWriter(jsonFile)) {
                fileJ.write(jsonObject.toJSONString());
            }

            market.printCart();
            market.saveTxt(file);
            clientLog.exportAsCSV(txtFile);

        }
    }
}