import java.util.*;

public class ContactDirectory {

    Map<String, List<String>> directory;

    public ContactDirectory() {
        directory = new HashMap<>();
    }

    public void add(String lastName, String phoneNumber) {
        if (directory.containsKey(lastName)) {
            directory.get(lastName).add(phoneNumber);
        } else {
            directory.put(lastName, new ArrayList<>(Arrays.asList(phoneNumber)));
        }
    }

    public List<String> get(String lastName) {
        return directory.getOrDefault(lastName, new ArrayList<>());
    }

    public void printAll() {
        for (Map.Entry<String, List<String>> entry : directory.entrySet()) {
            System.out.println("Фамилия: " + entry.getKey() + " -> Номера телефонов: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        ContactDirectory contactDirectory = new ContactDirectory();

        contactDirectory.add("Смирнов", "123-789");
        contactDirectory.add("Смирнов", "987-654");
        contactDirectory.add("Кузнецов", "555-123");
        contactDirectory.add("Попов", "777-444");

        System.out.println("Телефоны Смирнова: " + contactDirectory.get("Смирнов"));
        System.out.println("Телефоны Кузнецова: " + contactDirectory.get("Кузнецов"));
        System.out.println("Телефоны Попова: " + contactDirectory.get("Попов"));

        contactDirectory.printAll();
    }
}
