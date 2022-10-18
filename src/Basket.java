import java.io.*;


public class Basket {
    private String[] products;
    private int[] prices;
    private static int[] basket;
    private static String[] addedProd;

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
                System.out.println(getAddedProd()[i] + " " + getBasket()[i] + " шт, на сумму: " + (getBasket()[i] * getPrices()[i]) + " $");
                productSum = productSum + (getBasket()[i] * getPrices()[i]);
            }
        }
        System.out.println("Итого: " + productSum + " $");
    }

    public void saveTxt(File textFile) throws IOException {

        try (PrintWriter out = new PrintWriter(textFile);) {
            for (int i = 0; i < getBasket().length; i++) {
                if (getBasket()[i] != 0) {
                    out.print(getAddedProd()[i] + " ");
                } else {
                    break;
                }
            }
            out.println();
            for (Integer e : getBasket()) {
                if (e != 0) {
                    out.print(e + " ");
                } else {
                    break;
                }
            }
        }
    }

    public static void loadFromTxtFile(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String[] addedProdTxt = reader.readLine().split(" ");
            for (int i = 0; i < addedProdTxt.length; i++) {
                addedProd[i] = addedProdTxt[i];
                System.out.print(addedProd[i] + " ");
            }
            System.out.println();
            String[] addedProd = reader.readLine().split(" ");
            for (int i = 0; i < addedProd.length; i++) {
                basket[i] = Integer.parseInt(addedProd[i]);
                System.out.print(basket[i] + " ");
            }
            return;
        }
    }
}