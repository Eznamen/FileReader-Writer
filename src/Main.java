import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("src/basket.txt");

        Scanner scanner = new Scanner(System.in);
        Basket market = new Basket(new String[]{"Milk", "eggs", "bread", "cheese"}, new int[]{90, 140, 60, 300});
        if (file.exists()) {
            Basket.loadFromTxtFile(file);
        } else {
            while (true) {
                System.out.println("Продукты в магазине:");
                for (int i = 0; i < market.getProducts().length; i++) {
                    System.out.println((i + 1) + " " + market.getProducts()[i] + " " + market.getPrices()[i] + " " + "$/шт");
                }
                System.out.println("Введите номер продукта и его количество через пробел; для завершения работы введите end:");
                String input = scanner.nextLine();
                if ("end".equals(input)) {
                    break;
                }

                String[] parts = input.split(" ");
                market.addToCart(Integer.parseInt(parts[0]) - 1, Integer.parseInt(parts[1]));
            }

            market.printCart();
            market.saveTxt(file);

        }
    }
}