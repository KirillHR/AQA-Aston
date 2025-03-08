class Animal {
    String name;
    int run;
    int swim;
    int eat;

    public Animal(String name, int run, int swim, int eat) {
        this.name = name;
        this.run = run;
        this.swim = swim;
        this.eat = eat;
    }

    public void run(int distance) {
        if (distance <= run) {
            System.out.println(name + " пробежал " + distance + " метров");
        } else {
            System.out.println(name + " не может пробежать " + distance + " метров");
        }
    }

    public void swim(int distance) {
        if (distance <= swim) {
            System.out.println(name + " проплыл " + distance + " метров");
        } else {
            System.out.println(name + " не может проплыть " + distance + " метров");
        }
    }
}

class Cat extends Animal {
    static int catCount = 0;
    boolean isFull;
    int foodBowl;

    public Cat(String name, int foodBowl) {
        super(name, 200, 0, 2);
        this.foodBowl = foodBowl;
        this.isFull = false;
        catCount++;
    }

    public void eat(int foodAmount) {
        if (foodAmount <= foodBowl) {
            foodBowl -= foodAmount;
            isFull = true;
            System.out.println(name + " поел и теперь сыт");
        } else {
            System.out.println(name + " не хватает еды в миске, не поел");
        }
    }

    public void swim(int distance) {
        System.out.println(name + " не умеет плавать");
    }

    public static void getCatCount() {
        System.out.println("Количество котов: " + catCount);
    }
}

class Dog extends Animal {
    static int dogCount = 0;

    public Dog(String name) {
        super(name, 500, 10, 5);
        dogCount++;
    }

    public static void getDogCount() {
        System.out.println("Количество собак: " + dogCount);
    }
}

public class AnimalApp {
    public static void main(String[] args) {

        Cat cat = new Cat("Вася", 10);
        Cat cat1 = new Cat("Катер", 5);
        Dog dog = new Dog("Шарик");
        Dog dog1 = new Dog("Бублик");

        dog.swim(130);
        dog.run(88);
        dog1.run(99);
        cat.run(165);
        cat.run(249);
        cat1.swim(100);

        cat.eat(8);

        cat1.eat(6);

        Cat.getCatCount();
        Dog.getDogCount();
    }
}