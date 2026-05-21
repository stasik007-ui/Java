import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Lab4_Variant19 {

    public static void main(String[] args) {
        System.out.println("Розробник: [Священко С.В.]");
        System.out.println("--------------------------------------------------");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть шлях та ім'я вхідного файлу (наприклад, input.txt): ");
        String inputFilePath = scanner.nextLine();

        System.out.print("Введіть шлях та ім'я результуючого файлу (наприклад, output.txt): ");
        String outputFilePath = scanner.nextLine();

        System.out.print("Введіть підрядок для пошуку на початку рядків: ");
        String searchPrefix = scanner.nextLine();

        System.out.println("\n--- Початок обробки файлу ---");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            System.out.println("Файл " + inputFilePath + " успішно відкрито для читання.");
            System.out.println("Файл " + outputFilePath + " успішно відкрито для запису.");

            String currentLine;
            int lineNumber = 1;      // Лічильник поточного рядка
            int matchCount = 0;      // Лічильник знайдених збігів


            while ((currentLine = reader.readLine()) != null) {

                // Перевіряємо, чи починається поточний рядок із заданого підрядка
                // Використовуємо startsWith() згідно з умовою 19 варіанту
                if (currentLine.startsWith(searchPrefix)) {
                    // Формуємо рядок для запису (номер рядка)
                    String result = "Рядок №" + lineNumber;

                    writer.write(result);
                    writer.newLine();

                    // Виводимо інформаційне повідомлення в консоль
                    System.out.println("Знайдено збіг! " + result + " починається з '" + searchPrefix + "'");
                    matchCount++;
                }
                lineNumber++;
            }

            System.out.println("--------------------------------------------------");
            System.out.println("Обробку завершено.");
            System.out.println("Усього перевірено рядків: " + (lineNumber - 1));

            if (matchCount > 0) {
                System.out.println("Номери рядків (" + matchCount + " шт.) успішно записано у файл: " + outputFilePath);
            } else {
                System.out.println("Рядків, що починаються з '" + searchPrefix + "', не знайдено. Вихідний файл порожній.");
            }

        } catch (IOException e) {
            // Обробка помилок файлового введення/виведення (напр., файл не знайдено)
            System.err.println("\nПОМИЛКА РОБОТИ З ФАЙЛОМ!");
            System.err.println("Причина: " + e.getMessage());
            System.err.println("Перевірте правильність введених шляхів та наявність вхідного файлу.");
        } finally {
            scanner.close();
        }
    }
}