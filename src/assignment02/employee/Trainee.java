package assignment02.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Trainee extends Employee {

	// attributes:
	// * apprenticeship start date
	// * apprenticeship length (in days)
	
	private final LocalDate _apprenticeshipStart;
	private final int _apprenticeshipLen;

	public Trainee(String firstName, String surname, LocalDate birthDate, BigDecimal salary, Manager manager,
			LocalDate apprenticeshipStart, int apprenticeshipLen) {
		super(firstName, surname, birthDate, salary, manager);
		_apprenticeshipStart = apprenticeshipStart;
		_apprenticeshipLen = apprenticeshipLen;

	}

	public LocalDate getApprenticeshipStart() {
		return _apprenticeshipStart;
	}

	public int getApprenticeshipLen() {
		return _apprenticeshipLen;
	}
	
	public int getPracticeLen() {
		return (int) getApprenticeshipStart().until(LocalDate.now(), ChronoUnit.DAYS);
	}
		
	// (assignment 03)
	// * practice length is shorter than given number of days
	// * practice length is longer than given number of days
	
	public boolean isPracticeLengthShorterThanGivenNumber(int nbOfDays) {
		return getPracticeLen() < nbOfDays;
	}
	
	public boolean isPracticeLengthLongerThanGivenNumber(int nbOfDays) {
		return getPracticeLen() > nbOfDays;
	}
}