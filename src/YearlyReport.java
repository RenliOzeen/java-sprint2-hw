import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.time.Month;
import java.util.HashMap;

public class YearlyReport {

    HashMap<Integer, Double> expenses;
    HashMap<Integer, Double> incomes;

    YearlyReport() {

    }

    YearlyReport(String path) {
        expenses = new HashMap<>();
        incomes = new HashMap<>();

        String file = readFileContentsOrNull(path);
        String[] lines = file.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] lineContents = lines[i].split(",");
            if (Boolean.valueOf(lineContents[2]) == true) {
                expenses.put(Integer.valueOf(lineContents[0]), Double.valueOf(lineContents[1]));

            } else {
                incomes.put(Integer.valueOf(lineContents[0]), Double.valueOf(lineContents[1]));
            }
        }
    }


    public String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, " +
                    "файл не находится в нужной директории или отчета за введенный год не найдено, " +
                    "попробуйте снова.");
            return null;
        }
    }
    public void infoOfYear(YearlyReport reportPerYear, String nameOfYear, int monthsCount){
        double expensesSum=0;
        double incomesSum=0;
        System.out.println("Информация по отчету за " + nameOfYear + " год:");
        for (int i = 1; i <= monthsCount; i++) {
            System.out.println("Прибыль за " + Month.of(i) + " равна " +
                    (reportPerYear.incomes.get(i)-reportPerYear.expenses.get(i)));
            expensesSum+=reportPerYear.expenses.get(i);
            incomesSum+=reportPerYear.incomes.get(i);
        }
        System.out.println("Средний расход за " + monthsCount + " месяца(-ев) = " +
                expensesSum/monthsCount);
        System.out.println("Средний доход за " + monthsCount + " месяца(-ев) = " +
                incomesSum/monthsCount);

    }
}

