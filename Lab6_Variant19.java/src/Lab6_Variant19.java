import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lab6_Variant19 {

    public static void main(String[] args) {
        System.out.println("Розробник: [Священко С.В.]");
        System.out.println("------------------------------------------------------------------");

        // Розмірність списків не менше 10 згідно з умовою (візьмемо 10 вимірів)
        int size = 10;

        // Створення колекцій за допомогою Java Collections Framework (List та ArrayList)
        List<Double> currentList = new ArrayList<>();  // Список вимірів струму (I, Ампери)
        List<Double> voltageList = new ArrayList<>();  // Список вимірів напруги (U, Вольти)
        List<Double> resistanceList = new ArrayList<>(); // Список обчислених опорів (R, Оми)

        Random random = new Random();

        // 1. Формування випадкових значень вимірів
        for (int i = 0; i < size; i++) {
            // Струм: від 0.5 до 5.5 Ампер
            double current = 0.5 + (random.nextDouble() * 5.0);
            currentList.add(current);

            // Напруга: від 10.0 до 230.0 Вольт
            double voltage = 10.0 + (random.nextDouble() * 220.0);
            voltageList.add(voltage);
        }

        // 2. Виведення початкових колекцій у структурованому табличному вигляді
        System.out.println("ЗГЕНЕРОВАНІ ДАНІ ВИМІРІВ:");
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-9s | %-16s | %-16s |%n", "№ виміру", "Струм I, А", "Напруга U, В");
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < size; i++) {
            System.out.printf("| %-9d | %-16.3f | %-16.3f |%n", (i + 1), currentList.get(i), voltageList.get(i));
        }
        System.out.println("------------------------------------------------------------------");

        // 3. Обчислення опору для кожної пари за законом Ома: R = U / I
        double sumResistance = 0.0;
        int validMeasurementsCount = 0;

        for (int i = 0; i < size; i++) {
            double I = currentList.get(i);
            double U = voltageList.get(i);

            // Захист від критичної помилки ділення на нуль
            if (I != 0) {
                double R = U / I;
                resistanceList.add(R);
                sumResistance += R;
                validMeasurementsCount++;
            }
        }

        // 4. Виведення результатів індивідуального розрахунку опорів
        System.out.println("\nОБЧИСЛЕНІ ЗНАЧЕННЯ ОПОРУ ДЛЯ КОЖНОЇ ПАРИ:");
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-9s | %-16s | %-16s | %-16s |%n", "№ виміру", "Струм I, А", "Напруга U, В", "Опір R, Ом");
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < resistanceList.size(); i++) {
            System.out.printf("| %-9d | %-16.3f | %-16.3f | %-16.3f |%n",
                    (i + 1), currentList.get(i), voltageList.get(i), resistanceList.get(i));
        }
        System.out.println("------------------------------------------------------------------");

        // 5. Знаходження середнього значення опору
        if (validMeasurementsCount > 0) {
            double averageResistance = sumResistance / validMeasurementsCount;
            System.out.println("\nРЕЗУЛЬТАТ АНАЛІЗУ КОЛЕКЦІЇ:");
            System.out.printf("Середнє значення опору кола: %.3f Ом%n", averageResistance);
        } else {
            System.out.println("\nНе вдалося обчислити середній опір (немає коректних даних).");
        }
    }
}