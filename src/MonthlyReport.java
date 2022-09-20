import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.time.Month;
import java.util.HashMap;

public class MonthlyReport {
    final HashMap<String, HashMap<Integer, Double>> expenseReport;
    final HashMap<String, HashMap<Integer, Double>> incomeReport;


    MonthlyReport() {
        expenseReport = new HashMap<>();
        incomeReport = new HashMap<>();


    }

    /**
     * Метод для считывания строки из файла, такой же используется в классе YearlyReport
     *
     * @param path путь к файлу отчета формата .csv
     * @return возвращает содержимое файла строкой
     */  //не совсем разобрался, но судя по всему javadoc отличается от обычных комментов
    //цветом и возможностью добавлять дополнительные обозначения(имя разработчика/версию/параметры метода)
    public String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. " +
                    "Возможно, файл не находится в нужной директории или " +
                    "отчетов за введенный год не найдено, попробуйте снова.");
            return null;
        }
    }

    /**
     * Метод, проводящий сверку месячных отчетов с годовым
     *
     * @param reportPerMonth объект класса MonthlyReport
     * @param reportPerYear  объект класса YearlyReport
     * @param i              номер месяца
     */
    public void collation(MonthlyReport reportPerMonth,
                          YearlyReport reportPerYear, int i) {
        double monthExpenseSum = 0;
        double monthIncomeSum = 0;
        for (String name : reportPerMonth.expenseReport.keySet()) {
            HashMap<Integer, Double> expenses = reportPerMonth.expenseReport.get(name);
            for (Integer count : expenses.keySet()) {
                monthExpenseSum += count * expenses.get(count);
            }

        }
        for (String name : reportPerMonth.incomeReport.keySet()) {
            HashMap<Integer, Double> incomes = reportPerMonth.incomeReport.get(name);
            for (Integer count : incomes.keySet()) {
                monthIncomeSum += count * incomes.get(count);
            }
        }
        if (monthExpenseSum == reportPerYear.expenses.get(i) &&
                monthIncomeSum == reportPerYear.incomes.get(i)) {
            System.out.println("Сверка " + i + "-го месяца прошла успешно, " +
                    "несоответствий не обнаружено.");
        } else {
            System.out.println("Обнаружено несоответствие с годовым отчетом, " +
                    "проверьте " + i + "-й месяц");
        }
    }

    /**
     * Метод, отображающий краткую статистику по месячным отчетам
     *
     * @param reportPerMonth объект класса MonthlyReport
     * @param i              номер месяца
     */
    public void infoOfReports(MonthlyReport reportPerMonth, int i) {
        if (!reportPerMonth.incomeReport.isEmpty()) {
            System.out.println(Month.of(i));
            double itemSum = 0;
            String key = null;
            for (String name : reportPerMonth.incomeReport.keySet()) {
                HashMap<Integer, Double> incomes = reportPerMonth.incomeReport.get(name);
                for (Integer count : incomes.keySet()) {
                    if (count * incomes.get(count) > itemSum) {
                        itemSum = count * incomes.get(count);
                        key = name;
                    }
                }
            }
            System.out.println("Самый прибыльный товар - " + key + " на сумму " + itemSum);
            key = null;
            itemSum = 0;

            for (String name : reportPerMonth.expenseReport.keySet()) {
                HashMap<Integer, Double> expenses = reportPerMonth.expenseReport.get(name);
                for (Integer count : expenses.keySet()) {
                    if (count * expenses.get(count) > itemSum) {
                        itemSum = count * expenses.get(count);
                        key = name;
                    }

                }
            }
            System.out.println("Самая большая трата - " + key + " на сумму " + itemSum);
        }
    }

    /**
     * Метод для сохранения считанных в строку файлов отчетов в мапы,аналогичный в классе YearlyReport
     *
     * @param file строка, полученная из метода readFileContentsOrNull
     */
    public void saveData(String file) {
        String[] lines = file.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] lineContents = lines[i].split(",");
            if (Boolean.parseBoolean(lineContents[1])) {
                HashMap<Integer, Double> expense = new HashMap<>();
                expense.put(Integer.valueOf(lineContents[2]),
                        Double.valueOf(lineContents[3]));
                expenseReport.put(lineContents[0], expense);

            } else {
                HashMap<Integer, Double> income = new HashMap<>();
                income.put(Integer.valueOf(lineContents[2]),
                        Double.valueOf(lineContents[3]));
                incomeReport.put(lineContents[0], income);
            }
        }
    }
}


