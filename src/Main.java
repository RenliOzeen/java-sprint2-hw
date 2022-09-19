
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReportService reportService = new ReportService();


        System.out.println("Введите календарный год в формате (YYYY)");
        String nameOfYear = scanner.nextLine();
        printMenu();
        int command = scanner.nextInt();
        while (true) {
            if (command == 1) {
                try {
                    reportService.action(command, nameOfYear);
                } catch (java.lang.NullPointerException MonthlyReport) {
                    System.out.println("Введите календарный год в формате (YYYY)");
                    nameOfYear = scanner.next();
                }
            } else if (command == 2) {
                try {
                    reportService.action(command, nameOfYear);
                } catch (java.lang.NullPointerException MonthlyReport) {
                    System.out.println("Введите календарный год в формате (YYYY)");
                    nameOfYear = scanner.next();
                }
            } else if (command == 3) {
                reportService.action(command, nameOfYear);
            } else if (command == 4) {
                reportService.action(command, nameOfYear);
            } else if (command == 5) {
                reportService.action(command, nameOfYear);
            } else if (command == 0) {
                System.out.println("Работа программы завершена");
                return;
            } else {
                System.out.println("Неверный ввод комманды, попробуйте снова");
            }

            printMenu();
            command = scanner.nextInt();
        }
    }

    public static void printMenu() {
        System.out.println("1-Считать все месячные отчеты.");
        System.out.println("2-Считать годовой отчет.");
        System.out.println("3-Сверить отчеты.");
        System.out.println("4-Вывести информацию о всех месячных отчетах");
        System.out.println("5-Вывести информацию о годовом отчете");
        System.out.println("0-Завершить работу программы.");

    }

}


