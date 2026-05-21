import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Зовнішній клас: Художня виставка
class ArtExhibition {
    private String exhibitionName;
    private String place;
    private String time;
    private List<Painting> paintings; // Наповнення виставки (список об'єктів внутрішнього класу)

    public ArtExhibition(String exhibitionName, String place, String time) {
        this.exhibitionName = exhibitionName;
        this.place = place;
        this.time = time;
        this.paintings = new ArrayList<>();
    }

    // Метод для додавання картини (об'єкта внутрішнього класу)
    public void addPainting(String title, String author, String style) {
        // Створення об'єкта внутрішнього класу потребує контексту зовнішнього класу
        Painting newPainting = new Painting(title, author, style);
        this.paintings.add(newPainting);
        System.out.println("[ЛОГ] Об'єкт внутрішнього класу Painting успішно створено та додано до виставки.");
    }

    // Метод для виведення всієї структурованої інформації
    public void displayExhibitionInfo() {
        System.out.println("\n============= СТРУКТУРОВАНА ІНФОРМАЦІЯ =============");
        System.out.println("ВИСТАВКА:         " + exhibitionName);
        System.out.println("МІСЦЕ ПРОВЕДЕННЯ: " + place);
        System.out.println("ЧАС ПРОВЕДЕННЯ:   " + time);
        System.out.println("НАПОЛНЕННЯ (Список картин):");

        if (paintings.isEmpty()) {
            System.out.println("  На виставці поки немає картин.");
        } else {
            for (Painting painting : paintings) {
                painting.displayPaintingInfo();
            }
        }
        System.out.println("====================================================");
    }

    // Метод для демонстрації пошуку інформації
    public void searchByAuthor(String author) {
        System.out.println("\n[ЛОГ] Запуск алгоритму пошуку за автором: '" + author + "'");
        boolean isFound = false;

        for (Painting painting : paintings) {
            if (painting.getAuthor().equalsIgnoreCase(author.trim())) {
                System.out.print("  [ЗНАЙДЕНО] ");
                painting.displayPaintingInfo();
                isFound = true;
            }
        }

        if (!isFound) {
            System.out.println(">>> Результат: Картин автора '" + author + "' на цій виставці не знайдено.");
        }
    }

    // ==========================================================
    // ВНУТРІШНІЙ КЛАС: Картина
    // ==========================================================
    public class Painting {
        private String title;
        private String author;
        private String style; // Направлення

        public Painting(String title, String author, String style) {
            this.title = title;
            this.author = author;
            this.style = style;
        }

        public String getAuthor() {
            return author;
        }

        // Демонстрація доступу внутрішнього класу до полів зовнішнього
        public void displayPaintingInfo() {
            System.out.printf("Картина: \"%s\" | Автор: %s | Направлення: %s [Експонат виставки: %s]%n",
                    title, author, style, ArtExhibition.this.exhibitionName);
        }
    }
}

// Головний клас програми
public class Lab5_Variant19 {
    public static void main(String[] args) {
        System.out.println("Розробник: [Священко С.В.]");
        System.out.println("--------------------------------------------------");

        Scanner scanner = new Scanner(System.in);

        // 1. Введення даних про зовнішній клас з клавіатури
        System.out.println("[ЛОГ] Очікування введення загальних даних про виставку.");
        System.out.print("Введіть назву художньої виставки: ");
        String name = scanner.nextLine();

        System.out.print("Введіть місце її проведення: ");
        String place = scanner.nextLine();

        System.out.print("Введіть час проведення (наприклад, 10:00-19:00): ");
        String time = scanner.nextLine();

        System.out.println("[ЛОГ] Ініціалізація об'єкта зовнішнього класу ArtExhibition...");
        ArtExhibition exhibition = new ArtExhibition(name, place, time);
        System.out.println("[ЛОГ] Об'єкт виставки успішно створено.");

        // 2. Введення даних для внутрішнього класу
        System.out.print("\nСкільки картин ви бажаєте додати до виставки? ");
        int count = 0;
        try {
            count = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("[ЛОГ] Помилка формату. Буде додано за замовчуванням 2 картини.");
            count = 2;
        }

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Введення даних для картини №" + (i + 1) + " ---");
            System.out.print("Назва картини: ");
            String title = scanner.nextLine();

            System.out.print("Автор картини: ");
            String author = scanner.nextLine();

            System.out.print("Направлення (напр., Імпресіонізм, Кубізм): ");
            String style = scanner.nextLine();

            System.out.println("[ЛОГ] Звернення до методу додавання картини...");
            exhibition.addPainting(title, author, style);
        }

        // 3. Виведення структурованих даних
        exhibition.displayExhibitionInfo();

        // 4. Виконання пошуку
        System.out.println("\n--- Взаємодія з інтерфейсом пошуку ---");
        System.out.print("Введіть ім'я автора для пошуку його картин: ");
        String searchAuthor = scanner.nextLine();

        exhibition.searchByAuthor(searchAuthor);

        scanner.close();
        System.out.println("\n[ЛОГ] Роботу програми завершено.");
    }
}