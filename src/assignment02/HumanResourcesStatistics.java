package assignment02;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import assignment02.employee.*;
import assignment02.payroll.PayrollEntry;

public final class HumanResourcesStatistics {

    private HumanResourcesStatistics() {
    }


    // * search for Employees older than given employee and earning less than him
    public static List<Employee> olderThanAndEarnLess(List<Employee> allEmployees, Employee employee) {

        Predicate<Employee> isOlderAndEarnsLess = e -> e.isOlderThanGivenPerson(employee) && e.isSalaryLessThanGivenAmount(employee.getSalary());

        return allEmployees.stream()
                .filter(isOlderAndEarnsLess)
                .collect(Collectors.toList());
    }

    // * search for Trainees whose practice length is longer than given number of days and raise their salary by 5%
    public static List<Trainee> practiceLengthLongerThan(List<Employee> allEmployees, int daysCount) {

        Predicate<Trainee> isPracticeLenLonger = e -> e.isPracticeLengthLongerThanGivenNumber(daysCount);
        Consumer<Employee> giveRaise = e -> e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.05)));

        List<Trainee> traineesWhosPracticeLengthLongerThan = allEmployees.stream()
                .filter(emp -> emp instanceof Trainee).map(t -> (Trainee) t)
                .filter(isPracticeLenLonger)
                .collect(Collectors.toList());

        traineesWhosPracticeLengthLongerThan
                .forEach(giveRaise);

        return traineesWhosPracticeLengthLongerThan;
    }

    // * search for Workers whose seniority is longer than given number of months and give them bonus of 300 if their bonus is smaller
    public static List<Worker> seniorityLongerThan(List<Employee> allEmployees, int monthCount) {

        Predicate<Worker> isSeniorityLonger = e -> e.isSeniorityLongerThanGivenNumberOfMonths(monthCount);
        Predicate<Worker> isBonusSmallerThan300 = e -> e.getBonus().compareTo(BigDecimal.valueOf(300)) < 0;
        Consumer<Worker> giveBonus = e -> e.setBonus(BigDecimal.valueOf(300));

        List<Worker> workersWhosSeniorityLongerThanGivenMonthCount = allEmployees.stream()
                .filter(emp -> emp instanceof Worker).map(w -> (Worker) w)
                .filter(isSeniorityLonger)
                .collect(Collectors.toList());

        workersWhosSeniorityLongerThanGivenMonthCount.stream()
                .filter(isBonusSmallerThan300)
                .forEach(giveBonus);

        return workersWhosSeniorityLongerThanGivenMonthCount;

    }

    // * search for Workers whose seniority is between 1 and 3 years and give them raise of salary by 10%
    public static List<Worker> seniorityBetweenOneAndThreeYears(List<Employee> allEmployees) {

        Predicate<Worker> isSeniorityBetweenOneAndThreeYears = e -> e.isSeniorityLongerThanGivenNumberOfYears(1) && !e.isSeniorityLongerThanGivenNumberOfYears(3);
        Consumer<Employee> giveSalaryRaise = e -> e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.1)));

        List<Worker> workersWhosSeniorityBetweenOneAndThreeYears = allEmployees.stream()
                .filter(emp -> emp instanceof Worker).map(w -> (Worker) w)
                .filter(isSeniorityBetweenOneAndThreeYears)
                .collect(Collectors.toList());

        workersWhosSeniorityBetweenOneAndThreeYears
                .forEach(giveSalaryRaise);

        return workersWhosSeniorityBetweenOneAndThreeYears;
    }

    // * search for Workers whose seniority is longer than the seniority of a given employee and earn less than him and align their salary with the given employee
    public static List<Worker> seniorityLongerThan(List<Employee> allEmployees, Employee employee) {

        BiPredicate<Worker, Worker> workersWhosSeniorityLongerThanGivenEmployeeAndEarnLess = (e, s) -> e.getSeniority() > s.getSeniority() && e.compareSalary(s) == -1;
        BiConsumer<Worker, Worker> alignSalary = (e, s) -> e.setSalary(s.getSalary());

        List<Worker> seniorityLongerThan = allEmployees.stream()
                .filter(emp -> emp instanceof Worker).map(w -> (Worker) w)
                .filter(e -> workersWhosSeniorityLongerThanGivenEmployeeAndEarnLess.test(e, (Worker) employee))
                .collect(Collectors.toList());

        seniorityLongerThan
                .forEach(e -> alignSalary.accept(e, (Worker) employee));

        return seniorityLongerThan;

    }

    // * search for Workers whose seniority is between 2 and 4 years and whose age is greater than given number of years
    public static List<Worker> seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(List<Employee> allEmployees, int age) {

        Predicate<Worker> isSeniorityBetweenTwoAndFourYears = e -> e.getSeniority() >= 2 && e.getSeniority() <= 4;
        Predicate<Worker> isAgeGreaterThanGivenNumber = e -> e.getAge() > age;

        return allEmployees.stream()
                .filter(emp -> emp instanceof Worker).map(w -> (Worker) w)
                .filter(isSeniorityBetweenTwoAndFourYears.and(isAgeGreaterThanGivenNumber))
                .collect(Collectors.toList());
    }


    public static List<PayrollEntry> payroll(List<Employee> employees) {
        if (employees == null) {
            return null;
        }

        return employees.stream().filter(emp -> emp instanceof Worker).map(emp -> (Worker) emp)
                .map(w -> new PayrollEntry(w, w.getSalary(), w.getBonus())).collect(Collectors.toList());
    }

    // payroll for all subordinates
    public static List<PayrollEntry> subordinatesPayroll(Manager manager) {
        if (manager == null) {
            return null;
        }

        return manager.getSubordinates().stream().filter(emp -> emp instanceof Worker)
                .map(emp -> (Worker) emp).map(w -> new PayrollEntry(w, w.getSalary(), w.getBonus()))
                .collect(Collectors.toList());
    }

    public static BigDecimal bonusTotal(List<Employee> employees) {
        if (employees == null) {
            return BigDecimal.ZERO;
        }

        return employees.stream().filter(emp -> emp instanceof Worker).map(emp -> (Worker) emp)
                .map(Worker::getBonus).reduce(BigDecimal.valueOf(0), BigDecimal::add);
    }

    public static Employee longestSeniority(List<Employee> employees) {
        if (employees == null) {
            return null;
        }

        return employees.stream().filter(emp -> emp instanceof Worker).map(emp -> (Worker) emp)
                .max(Comparator.comparing(Worker::getSeniority)).orElseThrow();
    }

    public static BigDecimal highestSalaryWithoutBonus(List<Employee> employees) {
        if (employees == null) {
            return BigDecimal.ZERO;
        }

        return employees.stream().map(Employee::getSalary).max(BigDecimal::compareTo)
                .orElseThrow();
    }

    public static BigDecimal highestSalaryIncludingBonus(List<Employee> employees) {
        if (employees == null) {
            return BigDecimal.ZERO;
        }

        return employees.stream().filter(emp -> emp instanceof Worker)
                .map(emp -> (Worker) emp).map(w -> w.getSalary().add(w.getBonus())).max(BigDecimal::compareTo)
                .orElseThrow();
    }

    public static List<Employee> surnameBeginsWithA(Manager manager) {
        if (manager == null) {
            return null;
        }
        return manager.getSubordinates().stream()
                .filter(emp -> emp.getFirstName().startsWith("A")).collect(Collectors.toList());
    }

    public static List<Employee> earnMoreThan1000(List<Employee> employees) {
        if (employees == null) {
            return null;
        }
        return employees.stream()
                .filter(emp -> emp.getSalary().compareTo(BigDecimal.valueOf(1000)) > 0).collect(Collectors.toList());
    }

    /**
     * samples for functional processing in Java
     */
    public static List<Short> getAges(List<Employee> employees) {
        if (employees == null) {
            return null;
        }
        return employees //
                .stream() //
                .map(Person::getAge) //
                .collect(Collectors.toList());
    }

    public static void printAges(List<Employee> employees) {
        if (employees == null) {
            return;
        }
        employees //
                .stream() //
                .map(emp -> (int) emp.getAge()) //
                .forEach(age -> System.out.print(age + ", "));
    }

    // average age for the Employees whose first name starts with 'A' and they are older than 20
    public static short getAverageAgeInline(List<Employee> employees) {
        if (employees == null) {
            return 0;
        }
        int employeeTotalAge = employees //
                .stream() //
                .filter(emp -> emp.getFirstName().startsWith("A") && emp.getAge() > 20) //
                .map(emp -> (int) emp.getAge()) //
                .reduce(0, //
                        Integer::sum);

        long filteredEmployeesCount = employees //
                .stream() //
                .filter(emp -> emp.getFirstName().startsWith("A") && emp.getAge() > 20) //
                .count();

        return (short) (employeeTotalAge / filteredEmployeesCount);
    }

    public static short getAverageAgeMethodReference(List<Employee> employees) {
        if (employees == null) {
            return 0;
        }
        int employeeTotalAge = employees //
                .stream() //
                .map(emp -> (int) emp.getAge()) //
                .reduce(0, HumanResourcesStatistics::totalAge);
        return (short) (employeeTotalAge / employees.size());
    }

    public static short getMaxAgeInline(List<Employee> employees) {
        return employees //
                .stream() //
                .map(Person::getAge) //
                .reduce((short) 0, //
                        (maxAge, age) -> {
                            if (maxAge < age) {
                                return age;
                            } else {
                                return maxAge;
                            }
                        });
    }

    public static short getMaxAgeMethodReference(List<Employee> employees) {
        return employees //
                .stream() //
                .map(Person::getAge) //
                .reduce((short) 0, HumanResourcesStatistics::maxAge);
    }

    private static int totalAge(int totalAge, int age) {
        //
        return totalAge + age;
    }

    private static short maxAge(short maxAge, short age) {
        if (maxAge < age) {
            return age;
        } else {
            return maxAge;
        }
    }
}
