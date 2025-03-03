public class Products {
    private String name;
    private String productionDate;
    private String manufacturer;
    private String country;
    private int price;
    private boolean status;

    public Products(String name, String productionDate, String manufacturer, String country, int price, boolean status) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.country = country;
        this.price = price;
        this.status = status;
    }

    public void info() {
        System.out.println("Название товара: " + name + "; Дата производства: " + productionDate +
                "; Производитель: " + manufacturer + "; Страна происхождения: " + country +
                "; Цена: " + price + "; Состояние бронирования покупателем: " + status);
    }

    public static void main(String[] args) {
        Products[] productsArray = new Products[5];
        productsArray[0] = new Products("Samsung S25 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 5599, true);
        productsArray[1] = new Products("iPhone 15 Pro Max", "10.09.2024", "Apple Inc.", "USA", 6999, false);
        productsArray[2] = new Products("Xiaomi Mi 14", "15.03.2025", "Xiaomi Corp.", "China", 4299, false);
        productsArray[3] = new Products("Sony PlayStation 6", "20.11.2026", "Sony", "Japan", 7999, true);
        productsArray[4] = new Products("Dell XPS 17", "05.06.2024", "Dell", "USA", 8999, true);

        System.out.println("Список товаров:");
        for (Products product : productsArray) {
            product.info();
        }
    }
}