import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.time.Month;
import java.util.HashMap;

public class MonthlyReport {
    HashMap<String, HashMap<Integer, Double>> expenseReport;
    HashMap<String, HashMap<Integer, Double>> incomeReport;


    MonthlyReport(String path) {
        expenseReport = new HashMap<>();
        incomeReport = new HashMap<>();
        HashMap<Integer, Double> expense = new HashMap<>();
        HashMap<Integer, Double> income = new HashMap<>();

        String file = readFileContentsOrNull(path);
        String[] lines = file.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] lineContents = lines[i].split(",");
            if (Boolean.valueOf(lineContents[1]) == true) {
                expense = new HashMap<>();
                expense.put(Integer.valueOf(lineContents[2]),
                        Double.valueOf(lineContents[3]));
                expenseReport.put(lineContents[0], expense);

            } else {
                income = new HashMap<>();
                income.put(Integer.valueOf(lineContents[2]),
                        Double.valueOf(lineContents[3]));
                incomeReport.put(lineContents[0], income);
            }
        }
    }

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

    public void infoOfReports(MonthlyReport reportPerMonth, int i) {
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


