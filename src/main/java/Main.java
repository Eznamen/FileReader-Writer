
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, ParserConfigurationException, SAXException {
        File file = new File("basket.txt");
        File txtFile = new File("log.csv");
        File jsonFile = new File("basket.json");
        File xmlFile = new File("shop.xml");
        Scanner scanner = new Scanner(System.in);
        Basket market = new Basket(new String[]{"Milk", "eggs", "bread", "cheese"}, new int[]{90, 140, 60, 300});
        ClientLog clientLog = new ClientLog();


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(String.valueOf(xmlFile)));
        Node node = doc.getDocumentElement();

        Node root = doc.getDocumentElement();

        Config load = new Config(doc.getElementsByTagName("load").item(0));

        if (load.enabled == true) {
            if (load.format.equals("json")) {
                jsonRead(new File(load.fileName));
            } else {
                Basket.loadFromTxtFile(new File(load.fileName));
            }
        } else {
            System.out.println("Нет работы для load");
        }

        Config save = new Config(doc.getElementsByTagName("save").item(0));
        if (save.enabled == true) {
            if (save.format.equals("json")) {
                market.saveJson(new File(save.fileName));
            } else if (save.format.equals("txt")) {
                market.saveTxt(new File(save.fileName));
            }
        } else {
            System.out.println("Нет работы для save");
        }

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

        market.saveJson(jsonFile);

        market.printCart();
        market.saveTxt(file);
        clientLog.exportAsCSV(txtFile);
        Config log = new Config(doc.getElementsByTagName("log").item(0));
        if (log.enabled == true) {
            clientLog.exportAsCSV(new File(log.fileName));
        }
    }


    public static void jsonRead(File fileJ) {
        JSONParser jsonParser = new JSONParser();
        Object obj = null;
        try {
            obj = jsonParser.parse(new FileReader(fileJ));
        } catch (IOException e) {
            System.out.println("file was not found ");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println(obj);
    }
}
