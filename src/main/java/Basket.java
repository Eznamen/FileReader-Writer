import org.json.simple.JSONObject;

import java.io.*;
import java.util.Arrays;


public class Basket {
    private String[] products;
    private int[] prices;
    private int[] basket;
    private String[] addedProd;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        this.addedProd = new String[products.length];
        this.basket = new int[products.length];
    }

    public String[] getProducts() {
        return products;
    }

    public String[] getAddedProd() {
        return products;
    }

    public int[] getPrices() {
        return prices;
    }

    public void addToCart(int productNum, int amount) {
        if ((productNum + 1) != 0) {
            if (basket[productNum] == 0) {
                basket[productNum] = amount;
                addedProd[productNum] = products[productNum];
            } else {
                basket[productNum] = amount + basket[productNum];
            }
        }
    }

    public int[] getBasket() {
        return basket;
    }

    public void printCart() {
        int productSum = 0;
        System.out.println("Ваша корзина:");
        for (int i = 0; i < getBasket().length; i++) {
            if (getBasket()[i] > 0) {
                System.out.println(getAddedProd()[i] + " " + getBasket()[i] + " шт., по цене: " + (getBasket()[i] * getPrices()[i]) + " $");
                productSum = productSum + (getBasket()[i] * getPrices()[i]);
            }
        }
        System.out.println("Итого:" + productSum + " $");
    }


    public void saveTxt(File textFile) throws IOException {
        try (PrintWriter out = new PrintWriter(textFile)) {
            for (String prod :
                    getProducts()) {
                out.print(prod + " ");
            }
            out.println();
            for (int price :
                    getPrices()) {
                out.print(price + " ");
            }
            out.println();
            for (int i = 0; i < addedProd.length; i++) {
                if (addedProd[i] == null) {
                    continue;
                } else {
                    out.print(addedProd[i] + " ");
                }
            }
            out.println();

            for (int i = 0; i < basket.length; i++) {
                if (basket[i] == 0) {
                    continue;
                } else {
                    out.print(basket[i] + " ");
                }
            }
        }
    }

    public static Basket loadFromTxtFile(File file) throws IOException, NullPointerException {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String[] prodTxt = reader.readLine().split(" ");
            for (int k = 0; k < prodTxt.length; k++) {
                System.out.print(prodTxt[k] + " ");
            }
            System.out.println();

            String[] priceTxt = reader.readLine().split(" ");
            int[] pr = new int[priceTxt.length];
            for (int k = 0; k < priceTxt.length; k++) {
                pr[k] = Integer.parseInt(priceTxt[k]);
                System.out.print(pr[k] + " ");
            }
            System.out.println();
            Basket basket1 = new Basket(prodTxt, pr);

            String[] addedProdTxt = reader.readLine().split(" ");
            for (int i = 0; i < addedProdTxt.length; i++) {
                basket1.addedProd[i] = addedProdTxt[i];
                System.out.print(basket1.addedProd[i] + " ");
            }
            System.out.println();
            String[] basketTxt = reader.readLine().split(" ");
            for (int i = 0; i < basketTxt.length; i++) {
                basket1.basket[i] = Integer.parseInt(basketTxt[i]);
                System.out.print(basket1.basket[i] + " ");
            }
            return basket1;
        }
    }

    public void saveJson(File jsonFile) throws IOException{
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("amount", Arrays.toString(this.getBasket()));
        jsonObject.put("added products", Arrays.toString(this.getAddedProd()));
        jsonObject.put("prices", Arrays.toString(this.getPrices()));
        jsonObject.put("products", Arrays.toString(this.getProducts()));

        try (FileWriter fileJ = new FileWriter(jsonFile)) {
            fileJ.write(jsonObject.toJSONString());
        }
    }
}