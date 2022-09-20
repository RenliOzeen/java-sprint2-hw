import java.time.Month;


public class ReportService {

    MonthlyReport[] monthlyReports;
    YearlyReport yearlyReport;


    ReportService() {
        monthlyReports = new MonthlyReport[3];
        yearlyReport = new YearlyReport();
    }

    /**
     * Метод, вызываемый из Main по считыванию и записи месячных отчетов
     *
     * @param nameOfYear календарный год, к примеру "2021"
     */

    public void readMonthlyReport(String nameOfYear) {

        for (int i = 0; i < monthlyReports.length; i++) {
            String numberMonth = String.format("%02d", (i + 1));
            String path = "resources/m." + nameOfYear + numberMonth + ".csv";
            monthlyReports[i] = new MonthlyReport();
            String file = monthlyReports[i].readFileContentsOrNull(path);
            monthlyReports[i].saveData(file);
            System.out.println("Успешно считано. Отчет за " + Month.of(i + 1));
        }
    }

    /**
     * Метод, вызываемый из Main по считыванию и записи годового отчета
     *
     * @param nameOfYear календарный год, к примеру "2021"
     */
    public void readYearlyReport(String nameOfYear) {

        String path = "resources/y." + nameOfYear + ".csv";
        yearlyReport = new YearlyReport();
        String file = yearlyReport.readFileContentsOrNull(path);
        yearlyReport.saveData(file);
        System.out.println("Успешно считано. Отчет за " + nameOfYear + " год.");
    }

    /**
     * Метод для сверки месячных и годового отчета(вызывается из Main)
     */
    public void collationYearlyAndMonthlyReports() {

        for (int i = 0; i < monthlyReports.length; i++) {
            try {
                monthlyReports[i].collation(monthlyReports[i], yearlyReport, (i + 1));
            } catch (java.lang.NullPointerException Main) {
                i = monthlyReports.length;
                System.out.println("Вы еще не считали отчеты, возвращайтесь когда комманды " +
                        "1 и 2 будут использованы");
            }
        }
    }

    /**
     * Метод, показывающий краткую статистику по месячным отчетам(вызывается из Main)
     */
    public void showMonthlyReportsInfo() {

        for (int i = 0; i < monthlyReports.length; i++) {
            try {
                monthlyReports[i].infoOfReports(monthlyReports[i], (i + 1));
            } catch (java.lang.NullPointerException Main) {
                System.out.println("Вы еще не считали отчеты, возвращайтесь когда комманды " +
                        "1 и 2 будут использованы");
                i = monthlyReports.length;
            }
        }
    }

    /**
     * Метод, показывающий круткую статистику по годовому отчету(вызываемый из Main)
     *
     * @param nameOfYear календарный год, к примеру "2021"
     */
    public void showYearlyReportInfo(String nameOfYear) {

        try {
            yearlyReport.infoOfYear(yearlyReport, nameOfYear, monthlyReports.length);
        } catch (java.lang.NullPointerException Main) {
            System.out.println("Вы еще не считали отчеты, возвращайтесь когда комманды " +
                    "1 и 2 будут использованы");
        }
    }

}

