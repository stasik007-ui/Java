public class MultiThreadExample {

    public static void main(String[] args) {
        System.out.println("Запуск потоків... (Для зупинки програми натисніть Stop у вашому IDE)");
        System.out.println("------------------------------------------------------------------");

        // 1. Потік для цифри "1" із затримкою в 1 секунду
        Thread thread1 = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000); // 1000 мілісекунд = 1 секунда
                    System.out.println("1");
                }
            } catch (InterruptedException e) {
                System.out.println("Потік 1 було перервано");
            }
        });

        // 2. Потік для цифри "2" із затримкою у 2 секунди
        Thread thread2 = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(2000); // 2000 мілісекунд = 2 секунди
                    System.out.println("2");
                }
            } catch (InterruptedException e) {
                System.out.println("Потік 2 було перервано");
            }
        });

        // 3. Потік для цифри "3" із затримкою у 3 секунди
        Thread thread3 = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(3000); // 3000 мілісекунд = 3 секунди
                    System.out.println("3");
                }
            } catch (InterruptedException e) {
                System.out.println("Потік 3 було перервано");
            }
        });

        // Запуск усіх потоків одночасно
        thread1.start();
        thread2.start();
        thread3.start();
    }
}