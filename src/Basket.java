import java.io.*;


public class Basket implements Serializable {
    private String[] products;
    private int[] prices;
    private int[] basket;
    private String[] addedProd;
    static final long serialVersionUID = 1L;

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
        System.out.println("���� �������:");
        for (int i = 0; i < getBasket().length; i++) {
            if (getBasket()[i] > 0) {
                System.out.println(getAddedProd()[i] + " " + getBasket()[i] + " ��, �� �����: " + (getBasket()[i] * getPrices()[i]) + " $");
                productSum = productSum + (getBasket()[i] * getPrices()[i]);
            }
        }
        System.out.println("�����: " + productSum + " $");
    }

    public void saveBin(File file) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(this);
        }
    }

    public static Basket loadFromBinFile(File file) throws IOException, ClassNotFoundException {
        Basket basket1 = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            basket1 = (Basket) in.readObject();
        }
        return basket1;
    }
}