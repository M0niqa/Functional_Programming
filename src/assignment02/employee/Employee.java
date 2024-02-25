package assignment02.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Employee extends assignment02.employee.Person {

    private BigDecimal _salary;
    private Manager _manager;

    protected Employee(String firstName, String surname, LocalDate birthDate, BigDecimal salary, Manager manager) {
        super(firstName, surname, birthDate);
        _salary = salary;
        _manager = manager;
    }

    public BigDecimal getSalary() {
        return _salary;
    }

    public void setSalary(BigDecimal _salary) {
        this._salary = _salary;
    }

    public Manager getManager() {
        return _manager;
    }

    public void setManager(Manager _manager) {
        this._manager = _manager;
    }

    public boolean isSalaryGreaterThanGivenAmount(BigDecimal amount) {
        return this.getSalary().compareTo(amount) > 0;
    }

    public boolean isSalaryLessThanGivenAmount(BigDecimal amount) {
        return this.getSalary().compareTo(amount) < 0;
    }

    public int compareSalary(Employee employee) {
        if (isSalaryGreaterThanGivenAmount(employee.getSalary())) {
            return 1;
        } else if (isSalaryLessThanGivenAmount(employee.getSalary())) {
            return -1;
        } else {
            return 0;
        }
    }
}