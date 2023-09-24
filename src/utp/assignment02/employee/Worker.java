package utp.assignment02.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Worker extends Employee {

	// attributes
	// * employment date
	// * bonus

	
	private final LocalDate _employmentDate;
	private BigDecimal _bonus;

	public Worker(String firstName, String surname, LocalDate birthDate, BigDecimal salary, Manager manager,
			LocalDate employmentDate, BigDecimal bonus) {
		super(firstName, surname, birthDate, salary, manager);
		_employmentDate = employmentDate;
		_bonus = bonus;
	}

	public short getSeniority() {
		return (short) Period.between(getEmploymentDate(), LocalDate.now()).getYears();
	}

	public LocalDate getEmploymentDate() {
		return _employmentDate;
	}

	public BigDecimal getBonus() {
		return _bonus;
	}

	public void setBonus(BigDecimal _bonus) {
		this._bonus = _bonus;
	}
	
	// (assignment 03)
	// attributes:
	// * has bonus
	
	// methods:
	// * seniority is longer than given number of years (seniority - sta¿)
	// * seniority is longer than given number of months
	// * has bonus greater than given amount of money
	
	public boolean hasBonus() {
		return getBonus().compareTo(BigDecimal.ZERO) == 0;
	}
	
	public boolean isSeniorityLongerThanGivenNumberOfYears(int nbOfYears) {
		return this.getSeniority() > nbOfYears;		
	}
	
	public boolean isSeniorityLongerThanGivenNumberOfMonths(int nbOfMonths) {
		return (int) getEmploymentDate().until(LocalDate.now(), ChronoUnit.MONTHS) > nbOfMonths;	
	}
	
	public boolean hasBonusGreaterThanGivenAmount(BigDecimal amount) {
		return this.getBonus().compareTo(amount) > 0;
	}
	
}