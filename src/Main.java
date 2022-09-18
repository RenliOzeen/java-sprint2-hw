import java.time.Month;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MonthlyReport[] monthlyReports = new MonthlyReport[3];
        YearlyReport yearlyReport = new YearlyReport();

        System.out.println("Введите календарный год в формате (YYYY)");
        String nameOfYear = scanner.nextLine();
        printMenu();
        int command = scanner.nextInt();
        while (true) {
            if (command == 1) {
                try {
                    for (int i = 0; i < monthlyReports.length; i++) {
                        String numberMonth = String.format("%02d", (i + 1));
                        String path = "resources/m." + nameOfYear + numberMonth + ".csv";
                        monthlyReports[i] = new MonthlyReport(path);
                        System.out.println("Успешно считано. Отчет за " + Month.of(i+1));
                    }
                } catch (java.lang.NullPointerException MonthlyReport) {
                    System.out.println("Введите календарный год в формате (YYYY)");
                    nameOfYear = scanner.next();
                }
            } else if (command == 2) {
                try {
                    String path = "resources/y." + nameOfYear + ".csv";
                    yearlyReport = new YearlyReport(path);
                    System.out.println("Успешно считано. Отчет за " + nameOfYear + " год.");
                } catch (java.lang.NullPointerException MonthlyReport) {
                    System.out.println("Введите календарный год в формате (YYYY)");
                    nameOfYear = scanner.next();
                }
            } else if (command == 3) {

                for (int i = 0; i < monthlyReports.length; i++) {
                    try {
                        monthlyReports[i].collation(monthlyReports[i], yearlyReport, (i + 1));
                    } catch (java.lang.NullPointerException Main) {
                        i = monthlyReports.length;
                        System.out.println("Вы еще не считали отчеты, возвращайтесь когда комманды " +
                                "1 и 2 будут использованы");
                    }
                }
            } else if (command == 4) {
                for (int i = 0; i < monthlyReports.length; i++) {
                    try {
                        monthlyReports[i].infoOfReports(monthlyReports[i], (i + 1));
                    } catch (java.lang.NullPointerException Main) {
                        System.out.println("Вы еще не считали отчеты, возвращайтесь когда комманды " +
                                "1 и 2 будут использованы");
                        i = monthlyReports.length;
                    }
                }
            } else if (command == 5) {
                try {
                    yearlyReport.infoOfYear(yearlyReport, nameOfYear, monthlyReports.length);
                } catch (java.lang.NullPointerException Main) {
                    System.out.println("Вы еще не считали отчеты, возвращайтесь когда комманды " +
                            "1 и 2 будут использованы");
                }
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


