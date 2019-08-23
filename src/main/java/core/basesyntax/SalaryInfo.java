package core.basesyntax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SalaryInfo {
    /**
     * Реализуйте метод getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * <p>
     * Пример ввода: date from = 01.04.2019 date to = 30.04.2019
     * </p>
     * names:
     * Сергей
     * Андрей
     * София
     * <p>
     * data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * </p>
     * Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder allSalary = new StringBuilder();
        allSalary.append("Отчёт за период " + dateFrom + " - " + dateTo + "\n");
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        int salary1 = 0;
        int salary2 = 0;
        int salary3 = 0;

        try {
            Date minDate = format.parse(dateFrom);
            Date maxDate = format.parse(dateTo);
            if (minDate.compareTo(maxDate) > 0) {
                return null;
            }

            for (String s : data) {
                String[] workInfo = s.split(" ");
                Date workDate = format.parse(workInfo[0]);
                if (workDate.compareTo(minDate) >= 0 && workDate.compareTo(maxDate) <= 0) {
                    salary1 += workInfo[1].equals(names[0])
                            ? (Integer.parseInt(workInfo[workInfo.length - 2]))
                            * (Integer.parseInt(workInfo[workInfo.length - 1])) : 0;
                    salary2 += workInfo[1].equals(names[1])
                            ? (Integer.parseInt(workInfo[workInfo.length - 2])
                            * Integer.parseInt(workInfo[workInfo.length - 1])) : 0;
                    salary3 += workInfo[1].equals(names[2])
                            ? (Integer.parseInt(workInfo[workInfo.length - 2])
                            * Integer.parseInt(workInfo[workInfo.length - 1])) : 0;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return allSalary.append(names[0] + " - " + salary1 + "\n")
                        .append(names[1] + " - " + salary2 + "\n")
                        .append(names[2] + " - " + salary3 + "\n").toString();
    }
}
