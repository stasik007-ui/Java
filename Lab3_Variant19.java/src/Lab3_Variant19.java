import java.time.Year;
import java.util.Scanner;

class Car {
    private int id;
    private String brand;
    private String model;
    private int year;
    private String color;
    private String regNumber;
    private double price;

    public Car(int id, String brand, String model, int year, String color, String regNumber, double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.regNumber = regNumber;
        this.price = price;
    }

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public double getPrice() { return price; }

    public void printRow() {
        System.out.printf("| %-4d | %-12s | %-12s | %-4d | %-10s | %-10s | %-9.2f |%n",
                id, brand, model, year, color, regNumber, price);
    }
}

public class Lab3_Variant19 {

    public static void main(String[] args) {
        System.out.println("Розробник: [Священко С.В.]");
        System.out.println("--------------------------------------------------");

        Car[] cars = {
                new Car(1, "Toyota", "Camry", 2015, "Чорний", "АА1234ВВ", 15000.0),
                new Car(2, "BMW", "X5", 2020, "Білий", "ВХ5555СН", 45000.0),
                new Car(3, "Toyota", "Corolla", 2018, "Сірий", "КА7777АА", 12000.0),
                new Car(4, "Audi", "A6", 2015, "Синій", "ВС4321ІІ", 18000.0),
                new Car(5, "Toyota", "Camry", 2022, "Червоний", "АІ9999КК", 28000.0),
                new Car(6, "Honda", "Civic", 2010, "Чорний", "АА0001ОО", 8000.0)
        };

        Scanner scanner = new Scanner(System.in);
        int currentYear = Year.now().getValue();

        System.out.println("\n--- ПОЧАТКОВИЙ СПИСОК АВТОМОБІЛІВ ---");
        printTable(cars);

        // =========================================================
        // ЗАВДАННЯ 1: Список автомобілів заданої марки
        // =========================================================
        System.out.println("\n--- ПОШУК 1: За маркою ---");
        System.out.print("Введіть марку автомобіля для пошуку (напр. Toyota): ");
        String searchBrand = scanner.nextLine();

        Car[] carsByBrand = getCarsByBrand(cars, searchBrand);
        printTable(carsByBrand);

        // =========================================================
        // ЗАВДАННЯ 2: Список автомобілів заданої моделі, які експлуатуються більше N років
        // =========================================================
        System.out.println("\n--- ПОШУК 2: За моделлю та терміном експлуатації ---");
        System.out.print("Введіть модель автомобіля (напр. Camry): ");
        String searchModel = scanner.nextLine();

        int nYears = getValidIntInput(scanner, "Введіть кількість років експлуатації (N): ");

        Car[] carsByModelAndAge = getCarsByModelAndAge(cars, searchModel, nYears, currentYear);
        printTable(carsByModelAndAge);

        // =========================================================
        // ЗАВДАННЯ 3: Список автомобілів заданого року випуску, ціна яких більше заданої
        // =========================================================
        System.out.println("\n--- ПОШУК 3: За роком випуску та мінімальною ціною ---");

        int searchYear = getValidIntInput(scanner, "Введіть рік випуску автомобіля (напр. 2015): ");
        double minPrice = getValidDoubleInput(scanner, "Введіть мінімальну ціну: ");

        Car[] carsByYearAndPrice = getCarsByYearAndPrice(cars, searchYear, minPrice);
        printTable(carsByYearAndPrice);

        scanner.close();
    }

    // ================= МЕТОДИ ОБРОБКИ МАСИВУ =================

    // 1. Метод отримання автомобілів заданої марки
    public static Car[] getCarsByBrand(Car[] cars, String brand) {
        int count = 0;
        for (Car car : cars) {
            if (car.getBrand().equalsIgnoreCase(brand)) count++;
        }

        Car[] result = new Car[count];
        int index = 0;
        for (Car car : cars) {
            if (car.getBrand().equalsIgnoreCase(brand)) {
                result[index++] = car;
            }
        }
        return result;
    }

    // 2. Метод отримання автомобілів заданої моделі старше N років
    public static Car[] getCarsByModelAndAge(Car[] cars, String model, int nYears, int currentYear) {
        int count = 0;
        for (Car car : cars) {
            if (car.getModel().equalsIgnoreCase(model) && (currentYear - car.getYear() > nYears)) {
                count++;
            }
        }

        Car[] result = new Car[count];
        int index = 0;
        for (Car car : cars) {
            if (car.getModel().equalsIgnoreCase(model) && (currentYear - car.getYear() > nYears)) {
                result[index++] = car;
            }
        }
        return result;
    }

    // 3. Метод отримання автомобілів заданого року випуску дорожче заданої ціни
    public static Car[] getCarsByYearAndPrice(Car[] cars, int year, double minPrice) {
        int count = 0;
        for (Car car : cars) {
            if (car.getYear() == year && car.getPrice() > minPrice) count++;
        }

        Car[] result = new Car[count];
        int index = 0;
        for (Car car : cars) {
            if (car.getYear() == year && car.getPrice() > minPrice) {
                result[index++] = car;
            }
        }
        return result;
    }

    // ================= МЕТОДИ ВИВЕДЕННЯ ТА ОБРОБКИ ПОМИЛОК =================

    // Метод для виведення масиву автомобілів у вигляді таблиці
    public static void printTable(Car[] cars) {
        if (cars == null || cars.length == 0) {
            System.out.println(">>> Результат: За заданим критерієм автомобілів не знайдено.");
            return;
        }

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-12s | %-12s | %-4s | %-10s | %-10s | %-9s |%n",
                "ID", "Марка", "Модель", "Рік", "Колір", "Реєстр. №", "Ціна ($)");
        System.out.println("---------------------------------------------------------------------------------------");
        for (Car car : cars) {
            car.printRow();
        }
        System.out.println("---------------------------------------------------------------------------------------");
    }

    // Метод для безпечного введення цілих чисел (Обробка виключень)
    public static int getValidIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value < 0) {
                    System.out.println("Помилка: Число не може бути від'ємним. Спробуйте ще раз.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Помилка введення: Очікується ціле число. Спробуйте ще раз.");
            }
        }
    }

    // Метод для безпечного введення дробових чисел (Обробка виключень)
    public static double getValidDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim().replace(",", ".");
                double value = Double.parseDouble(input);
                if (value < 0) {
                    System.out.println("Помилка: Значення не може бути від'ємним. Спробуйте ще раз.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Помилка введення: Очікується число (наприклад, 15000 або 15000.50). Спробуйте ще раз.");
            }
        }
    }
}