public class Park {
    private String name;
    private String location;

    public Park(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public void info() {
        System.out.println("Название парка: " + name + "; Местоположение: " + location);
    }

    public class Attraction {
        private String attractionName;
        private String workingHours;
        private int price;
        private boolean status;

        public Attraction(String attractionName, String workingHours, int price, boolean status) {
            this.attractionName = attractionName;
            this.workingHours = workingHours;
            this.price = price;
            this.status = status;
        }

        public void info() {
            System.out.println("Аттракцион: " + attractionName + "; Время работы: " + workingHours +
                    "; Стоимость: " + price + " руб.; Доступность: " + status);
        }
    }

    public static void main(String[] args) {
        Park[] parksArray = new Park[3];
        parksArray[0] = new Park("Зеленая Долина", "Москва");
        parksArray[1] = new Park("Лесное Чудо", "Санкт-Петербург");
        parksArray[2] = new Park("Сказочный Оазис", "Краснодар");

        Park.Attraction attraction1 = parksArray[0].new Attraction("Летающий корабль", "09:00 - 21:00", 550, true);
        Park.Attraction attraction2 = parksArray[1].new Attraction("Водная рапида", "10:00 - 19:00", 650, false);

        System.out.println("Список парков:");
        for (Park park : parksArray) {
            park.info();
        }

        System.out.println("Информация об аттракционах:");
        attraction1.info();
        attraction2.info();
    }
}