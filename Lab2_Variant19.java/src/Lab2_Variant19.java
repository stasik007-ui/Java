import java.util.Random;

public class Lab2_Variant19 {

    public static void main(String[] args) {
        System.out.println("Розробник: [Священко С.В.]");
        System.out.println("--------------------------------------------------");

        int amountToGenerate = 100;
        int[] randomNumbers = new int[amountToGenerate];
        Random random = new Random();

        System.out.println("Згенеровані випадкові 5-значні числа:");
        for (int i = 0; i < amountToGenerate; i++) {

            randomNumbers[i] = 10000 + random.nextInt(90000);
            System.out.print(randomNumbers[i] + " ");
            if ((i + 1) % 10 == 0) System.out.println();
        }
        System.out.println("--------------------------------------------------");

        System.out.println("Знайдені числа, у яких ТІЛЬКИ три однакові цифри:");
        int matchCount = 0;

        for (int number : randomNumbers) {
            String strNumber = String.valueOf(number);

            if (hasExactlyThreeIdenticalDigits(strNumber)) {
                System.out.print(strNumber + " ");
                matchCount++;
            }
        }

        System.out.println("\n--------------------------------------------------");
        if (matchCount == 0) {
            System.out.println("Таких чисел серед згенерованих не виявлено.");
        } else {
            System.out.println("Усього знайдено таких чисел: " + matchCount);
        }
    }

    public static boolean hasExactlyThreeIdenticalDigits(String str) {
        boolean hasThree = false;
        boolean hasPair = false;

        for (char c = '0'; c <= '9'; c++) {
            String digit = String.valueOf(c);

            String tempStr = str.replace(digit, "");

            int count = str.length() - tempStr.length();

            if (count == 3) {
                hasThree = true;
            } else if (count == 2) {
                hasPair = true;
            }
        }
        return hasThree && !hasPair;
    }
}