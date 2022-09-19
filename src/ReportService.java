import java.time.Month;


public class ReportService {

    MonthlyReport[] monthlyReports;
    YearlyReport yearlyReport;


    ReportService() {
        monthlyReports = new MonthlyReport[3];
        yearlyReport = new YearlyReport();
    }

    /** вся бизнес-логика приложения описана тут:
     *
     * @param command
     * @param nameOfYear
     */
    public void action(int command, String nameOfYear) {

        if (command == 1) {

            for (int i = 0; i < monthlyReports.length; i++) {
                String numberMonth = String.format("%02d", (i + 1));
                String path = "resources/m." + nameOfYear + numberMonth + ".csv";
                monthlyReports[i] = new MonthlyReport();
                String file = monthlyReports[i].readFileContentsOrNull(path);
                monthlyReports[i].saveData(file);
                System.out.println("Успешно считано. Отчет за " + Month.of(i + 1));
            }


        } else if (command == 2) {

            String path = "resources/y." + nameOfYear + ".csv";
            yearlyReport = new YearlyReport();
            String file = yearlyReport.readFileContentsOrNull(path);
            yearlyReport.saveData(file);
            System.out.println("Успешно считано. Отчет за " + nameOfYear + " год.");


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
        }

    }
}
