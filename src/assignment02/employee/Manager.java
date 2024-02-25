package assignment02.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Manager extends Worker {

	private List<Employee> _subordinates;

	public Manager(String firstName, String surname, LocalDate birthDate, BigDecimal salary, Manager manager,
			LocalDate employmentDate, BigDecimal bonus, List<Employee> subordinates) {
		super(firstName, surname, birthDate, salary, manager, employmentDate, bonus);
		_subordinates = subordinates;
	}

	public void setSubordinates(List<Employee> _subordinates) {
		this._subordinates = _subordinates;
	}

	public List<Employee> getSubordinates() {
		return _subordinates;
	}

	public List<Employee> getAllSubordinates() {

		return _subordinates.stream()
				.flatMap(employee -> {
					List<Employee> allSubordinates = new ArrayList<>();
					allSubordinates.add(employee);
					if (employee instanceof Manager) {
						allSubordinates.addAll(((Manager) employee).getAllSubordinates());
					}
					return allSubordinates.stream();
				})
				.collect(Collectors.toList());
	}

}