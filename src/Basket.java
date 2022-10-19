import java.io.*;


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
        Basket basket1 = new Basket(new String[]{"Milk", "eggs", "bread", "cheese"}, new int[]{90, 140, 60, 300});
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
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
}