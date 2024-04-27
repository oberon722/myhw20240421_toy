import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Запись для представления участника розыгрыша
record Participant(String name, String surname, String phoneNumber) {}

// Класс для представления детского магазина
class ToyStore {
    private final List<Participant> participants;
    private final List<String> toys;
    private final Scanner scanner;

    public ToyStore() {
        this.participants = generateParticipants();
        this.toys = generateToys();
        this.scanner = new Scanner(System.in);
    }

    // Генерация списка участников розыгрыша
    private List<Participant> generateParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(new Participant("Иван", "Иванов", "12345678901"));
        participants.add(new Participant("Петр", "Петров", "23456789012"));
        participants.add(new Participant("Анна", "Сидорова", "34567890123"));
        participants.add(new Participant("Елена", "Смирнова", "45678901234"));
        participants.add(new Participant("Сергей", "Кузнецов", "56789012345"));
        return participants;
    }

    // Генерация списка разыгрываемых игрушек
    private List<String> generateToys() {
        return new ArrayList<>(List.of("Мяч", "Кукла", "Конструктор", "Машинка", "Кубики",
                "Игровая площадка", "Кукольный домик", "Пазл",
                "Робот", "Кукольный театр"));
    }

    // Регистрация участника розыгрыша
    public void registerParticipant() {
        System.out.println("Введите имя участника:");
        String name = scanner.nextLine();

        System.out.println("Введите фамилию участника:");
        String surname = scanner.nextLine();

        System.out.println("Введите номер телефона (11 цифр):");
        String phoneNumber = scanner.nextLine();

        if (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("Ошибка: Некорректный номер телефона.");
            return;
        }

        participants.add(new Participant(name, surname, phoneNumber));

        System.out.println("Вопрос: Четыре четырки, две растопырки, один вертун! Кто это?");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("собака")) {
            System.out.println("Вы зарегистрированы. Ожидайте розыгрыша!");
        } else {
            System.out.println("Ответ неверный. Вы не зарегистрированы.");
        }

        if (participants.size() == 5) {
            System.out.println("Зарегистрировано 5 участников! Начинаем розыгрыш!");
            conductLottery();
        }
    }

    // Проведение розыгрыша
    public void conductLottery() {
        if (participants.isEmpty() || toys.isEmpty()) {
            System.out.println("Недостаточно участников или игрушек для проведения розыгрыша.");
            return;
        }

        Random random = new Random();
        Participant winner = participants.get(random.nextInt(participants.size()));
        String toyWon = toys.get(random.nextInt(toys.size()));
        toys.remove(toyWon);

        System.out.println("Победитель розыгрыша: " + winner.surname() + " " + winner.name() + ", " +
                "Телефон: " + winner.phoneNumber() + ", Выиграл игрушку: " + toyWon);

        if (toys.isEmpty()) {
            System.out.println("Игрушек доступных к розыгрышу больше нет. СТОП ИГРА!!!");
        }
    }

    // Проверка номера телефона на соответствие формату
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{11}");
    }

    // Вывод списка участников розыгрыша
    public void listParticipants() {
        System.out.println("Список участников розыгрыша:");
        for (Participant participant : participants) {
            System.out.println("Имя: " + participant.name() + ", Фамилия: " + participant.surname() + ", Телефон: " + participant.phoneNumber());
        }
    }

    // Вывод списка разыгрываемых игрушек
    public void listToys() {
        System.out.println("Разыгрываемые игрушки:");
        for (String toy : toys) {
            System.out.println(toy);
        }
    }

    // Вывод стартового меню лотереи
    public void showMenu() {
        System.out.println("Стартовое меню лотереи:");
        System.out.println("1 - Регистрация участников");
        System.out.println("2 - Начать розыгрыш");
        System.out.println("3 - Перечень участников розыгрыша");
        System.out.println("4 - Вывести список разыгрываемых игрушек");
        System.out.println("0 - Выход");
    }
}

public class Main {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();
        toyStore.showMenu();

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Введите ваш выбор:");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    toyStore.registerParticipant();
                    break;
                case 2:
                    toyStore.conductLottery();
                    break;
                case 3:
                    toyStore.listParticipants();
                    break;
                case 4:
                    toyStore.listToys();
                    break;
                case 0:
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
                    break;
            }
        } while (choice != 0);
    }
}