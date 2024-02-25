package assignment02.payroll;

import java.math.BigDecimal;
import java.util.Objects;

import assignment02.employee.Employee;

public final class PayrollEntry {

	private final Employee _employee;
	private final BigDecimal _salaryPlusBonus;

	public PayrollEntry(Employee employee, BigDecimal salary, BigDecimal bonus) {
		if (salary == null) {
			throw new IllegalArgumentException("Salary cannot be null");
		}
		if (bonus == null) {
			throw new IllegalArgumentException("Bonus cannot be null");
		}
		_employee = employee;
		_salaryPlusBonus = salary.add(bonus); // validate whether salary and bonus are not null
	}

	public BigDecimal getSalaryPlusBonus() {
		return _salaryPlusBonus;
	}

	public Employee getEmployee() {
		return _employee;
	}

	@Override
	public String toString() {
		return _employee + " " + _salaryPlusBonus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_employee, _salaryPlusBonus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PayrollEntry other = (PayrollEntry) obj;
		return Objects.equals(_employee, other._employee) && Objects.equals(_salaryPlusBonus, other._salaryPlusBonus);
	}

}