
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import assignment02.HumanResourcesStatistics;
import assignment02.employee.Employee;
import assignment02.employee.Manager;
import assignment02.employee.Trainee;
import assignment02.employee.Worker;
import assignment02.payroll.PayrollEntry;

public class HumanResourcesStatisticsTest {

	private List<Employee> _allEmployees;
	private List<Employee> subordinates1;
	private List<Employee> subordinates2;
	private List<Employee> subordinates3;
	private List<Employee> managersAndSubordinates;

	private Worker worker1;
	private Worker worker2;
	private Worker worker3;
	private Worker worker4;
	private Worker worker5;
	private Worker worker6;
	private Worker worker7;
	private Worker worker8;
	private Worker worker9;
	private Worker worker10;
	private Worker worker11;

	private Trainee trainee1;
	private Trainee trainee2;
	private Trainee trainee3;
	private Trainee trainee4;
	private Trainee trainee5;

	private Manager director;
	private Manager manager1;
	private Manager manager2;
	private Manager manager3;

	@Before
	public void setup() {

		worker1 = new Worker("Monika", "Stanko", LocalDate.of(1990, 7, 21), BigDecimal.valueOf(900), null,
				LocalDate.of(2020, 2, 18), BigDecimal.valueOf(150));
		worker2 = new Worker("Anna", "Laskowska", LocalDate.of(1993, 3, 15), BigDecimal.valueOf(850), null,
				LocalDate.of(2019, 12, 8), BigDecimal.valueOf(140));
		worker3 = new Worker("Paulina", "Nowak", LocalDate.of(1989, 4, 11), BigDecimal.valueOf(900), null,
				LocalDate.of(2021, 1, 21), BigDecimal.valueOf(150));
		worker4 = new Worker("Karina", "Kowalska", LocalDate.of(1987, 11, 19), BigDecimal.valueOf(950), null,
				LocalDate.of(2018, 3, 8), BigDecimal.valueOf(100));
		worker5 = new Worker("Edmund", "Jawor", LocalDate.of(1988, 7, 1), BigDecimal.valueOf(850), null,
				LocalDate.of(2020, 4, 18), BigDecimal.valueOf(150));
		worker6 = new Worker("Jan", "Jackowski", LocalDate.of(1987, 3, 15), BigDecimal.valueOf(950), null,
				LocalDate.of(2019, 10, 8), BigDecimal.valueOf(120));
		worker7 = new Worker("Marcin", "Jamroz", LocalDate.of(1991, 4, 11), BigDecimal.valueOf(900), null,
				LocalDate.of(2020, 5, 21), BigDecimal.valueOf(150));
		worker8 = new Worker("Szymon", "Kraska", LocalDate.of(1992, 5, 19), BigDecimal.valueOf(900), null,
				LocalDate.of(2018, 5, 8), BigDecimal.valueOf(130));
		worker9 = new Worker("Justyna", "Machala", LocalDate.of(1991, 8, 11), BigDecimal.valueOf(850), null,
				LocalDate.of(2020, 5, 21), BigDecimal.valueOf(150));
		worker10 = new Worker("Joanna", "Kalik", LocalDate.of(1992, 9, 20), BigDecimal.valueOf(900), null,
				LocalDate.of(2019, 5, 8), BigDecimal.valueOf(130));
		worker11 = new Worker("Kamil", "Berniak", LocalDate.of(1985, 7, 1), BigDecimal.valueOf(950), null,
				LocalDate.of(2018, 2, 18), BigDecimal.valueOf(150));

		trainee1 = new Trainee("Agata", "Dela", LocalDate.of(1995, 4, 25), BigDecimal.valueOf(600), null,
				LocalDate.of(2022, 9, 1), 90);
		trainee2 = new Trainee("Elena", "Stanek", LocalDate.of(1997, 6, 29), BigDecimal.valueOf(550), null,
				LocalDate.of(2022, 9, 8), 60);
		trainee3 = new Trainee("Ingrid", "Essen", LocalDate.of(1996, 12, 25), BigDecimal.valueOf(650), null,
				LocalDate.of(2022, 10, 1), 50);
		trainee4 = new Trainee("Eliza", "Stan", LocalDate.of(1994, 2, 23), BigDecimal.valueOf(600), null,
				LocalDate.of(2022, 10, 9), 50);
		trainee5 = new Trainee("Albert", "Matusiak", LocalDate.of(1996, 2, 24), BigDecimal.valueOf(650), null,
				LocalDate.of(2022, 9, 18), 90);

		subordinates1 = Arrays.asList(worker1, worker2, worker3, trainee1, trainee2);
		subordinates2 = Arrays.asList(worker4, worker5, worker6, trainee3, trainee4);
		subordinates3 = Arrays.asList(worker7, worker8, worker9, trainee5);

		director = new Manager("Monika", "Potocka", LocalDate.of(1970, 2, 13), BigDecimal.valueOf(2000), null,
				LocalDate.of(2010, 12, 8), BigDecimal.valueOf(400), null);
		manager1 = new Manager("Łukasz", "Darski", LocalDate.of(1980, 12, 8), BigDecimal.valueOf(1200), director,
				LocalDate.of(2015, 12, 8), BigDecimal.valueOf(200), subordinates1);
		manager2 = new Manager("Sabina", "Kolodziejcyk", LocalDate.of(1981, 3, 1), BigDecimal.valueOf(1300), director,
				LocalDate.of(2014, 12, 8), BigDecimal.valueOf(250), subordinates2);
		manager3 = new Manager("Joanna", "Zmark", LocalDate.of(1982, 6, 6), BigDecimal.valueOf(1200), director,
				LocalDate.of(2016, 12, 8), BigDecimal.valueOf(250), subordinates3);

		managersAndSubordinates = Arrays.asList(manager1, manager2, manager3, worker10, worker11);
		director.setSubordinates(managersAndSubordinates);

		managersAndSubordinates.stream().forEach(emp -> emp.setManager(director));
		subordinates1.stream().forEach(emp -> emp.setManager(manager1));
		subordinates2.stream().forEach(emp -> emp.setManager(manager2));
		subordinates3.stream().forEach(emp -> emp.setManager(manager3));

		_allEmployees = new ArrayList<>();
		_allEmployees.add(director);
		_allEmployees.addAll(managersAndSubordinates);
		_allEmployees.addAll(subordinates1);
		_allEmployees.addAll(subordinates2);
		_allEmployees.addAll(subordinates3);

	}


	@Test
	public void shouldReturnAllSubordinates() {
		System.out.println(director.getAllSubordinates());
	}
	
	// (assignment 03)
	
	@Test
	public void shouldReturnListOfEmployeesWhoAreOlderThanAndEarnLess() {		
		List<Employee> expectedList = Arrays.asList(worker5);		
		Assert.assertEquals(expectedList, HumanResourcesStatistics.olderThanAndEarnLess(_allEmployees, worker3));
	}
	
	@Test
	public void shouldReturnListOfTraineesWhosPracticeLengthLongerThan() {
		List<Trainee> expectedList = Arrays.asList(trainee1, trainee2, trainee3, trainee4, trainee5);
		
		List<Trainee> obtainedList = HumanResourcesStatistics.practiceLengthLongerThan(_allEmployees, 60);
			
		Assert.assertEquals(expectedList, obtainedList);
	}
	
	@Test
	public void shouldRaiseSalaryByFivePercent() {		
		List<Trainee> obtainedList = HumanResourcesStatistics.practiceLengthLongerThan(_allEmployees, 30);
			
		Assert.assertTrue(BigDecimal.valueOf(630).compareTo(obtainedList.get(0).getSalary()) == 0);
	}
	
	@Test
	public void shouldReturnListOfWorkersWhosPracticeSeniorityLongerThan() {
		List<Worker> expectedList = Arrays.asList(director, manager1, manager2, manager3);
		
		List<Worker> obtainedList = HumanResourcesStatistics.seniorityLongerThan(_allEmployees, 60);
			
		Assert.assertEquals(expectedList, obtainedList);
	}
	
	@Test
	public void shouldRaiseBonus() {		
		List<Worker> obtainedList = HumanResourcesStatistics.seniorityLongerThan(_allEmployees, 60);
			
		Assert.assertTrue(BigDecimal.valueOf(300).compareTo(obtainedList.get(1).getBonus()) == 0);
	}
	
	@Test
	public void shouldReturnListOfWorkersWhosSeniorityBetweenOneAndThreeYears() {
		List<Worker> expectedList = Arrays.asList(worker10, worker1, worker2, worker5, worker6, worker7, worker9);
		
		List<Worker> obtainedList = HumanResourcesStatistics.seniorityBetweenOneAndThreeYears(_allEmployees);
			
		Assert.assertEquals(expectedList, obtainedList);
	}
	
	@Test
	public void shouldRaiseSalaryByTenPercent() {		
		List<Worker> obtainedList = HumanResourcesStatistics.seniorityBetweenOneAndThreeYears(_allEmployees);
			
		Assert.assertTrue(BigDecimal.valueOf(990).compareTo(obtainedList.get(0).getSalary()) == 0);
	}
	
	@Test
	public void shouldReturnListOfWorkersWhosSeniorityLongerThan() {
		List<Worker> expectedList = Arrays.asList(worker2, worker5, worker9);
		
		List<Worker> obtainedList = HumanResourcesStatistics.seniorityLongerThan(_allEmployees, worker3);
		
		Assert.assertEquals(expectedList, obtainedList);		
	}
	
	@Test
	public void shouldAlignSalary() {		
		List<Worker> obtainedList = HumanResourcesStatistics.seniorityLongerThan(_allEmployees, worker3);
			
		Assert.assertTrue(BigDecimal.valueOf(900).compareTo(obtainedList.get(0).getSalary()) == 0);
	}
	
	@Test
	public void shouldReturnListOfWorkersWhosSeniorityBetweenTwoAndFourYearsAndAgeGreaterThan() {
		List<Worker> expectedList = Arrays.asList(worker11);
		
		List<Worker> obtainedList = HumanResourcesStatistics.seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(_allEmployees, 35);
			
		Assert.assertEquals(expectedList, obtainedList);
	}

	
	@Test
	public void payroll() {
		List<Employee> employees = new ArrayList<>();
		employees.add(director);
		employees.addAll(managersAndSubordinates);

		List<PayrollEntry> expectedList = Arrays.asList(
				new PayrollEntry(director, BigDecimal.valueOf(2000), BigDecimal.valueOf(400)),
				new PayrollEntry(manager1, BigDecimal.valueOf(1200), BigDecimal.valueOf(200)),
				new PayrollEntry(manager2, BigDecimal.valueOf(1300), BigDecimal.valueOf(250)),
				new PayrollEntry(manager3, BigDecimal.valueOf(1200), BigDecimal.valueOf(250)),
				new PayrollEntry(worker10, BigDecimal.valueOf(900), BigDecimal.valueOf(130)),
				new PayrollEntry(worker11, BigDecimal.valueOf(950), BigDecimal.valueOf(150)));

		List<PayrollEntry> payroll = HumanResourcesStatistics.payroll(employees);
		Assert.assertEquals(expectedList, payroll);
	}

	@Test
	public void shouldReturnSubordinatesPayrollOfGivenManager() {
		List<PayrollEntry> expectedList = Arrays.asList(
				new PayrollEntry(worker1, BigDecimal.valueOf(900), BigDecimal.valueOf(150)),
				new PayrollEntry(worker2, BigDecimal.valueOf(850), BigDecimal.valueOf(140)),
				new PayrollEntry(worker3, BigDecimal.valueOf(900), BigDecimal.valueOf(150)));

		Assert.assertEquals(expectedList, HumanResourcesStatistics.subordinatesPayroll(manager1));
	}

	@Test
	public void shouldReturnNullIfManagerOfsubordinatesPayrollNull() {
		Assert.assertEquals(null, HumanResourcesStatistics.subordinatesPayroll(null));
	}

	@Test
	public void shouldReturnBonusTotal() {
		BigDecimal total = HumanResourcesStatistics.bonusTotal(_allEmployees);
		Assert.assertEquals(new BigDecimal("2620"), total);
	}

	@Test
	public void shouldReturnEmployeeWithLongestSeniority() {
		Employee emp = HumanResourcesStatistics.longestSeniority(_allEmployees);
		Assert.assertEquals(director, emp);
	}

	@Test
	public void shouldReturnHighestSalaryWithoutBonus() {
		BigDecimal highestSalaryWithoutBonus = HumanResourcesStatistics.highestSalaryWithoutBonus(_allEmployees);
		Assert.assertEquals(BigDecimal.valueOf(2000), highestSalaryWithoutBonus);
	}

	@Test
	public void shouldReturnHighestSalaryIncludingBonus() {
		BigDecimal highestSalaryIncludingBonus = HumanResourcesStatistics.highestSalaryIncludingBonus(_allEmployees);
		Assert.assertEquals(BigDecimal.valueOf(2400), highestSalaryIncludingBonus);
	}

	@Test
	public void shouldReturnListOfEmoloyeesWithSurnameBeginsWithA() {
		List<Employee> expectedList = Arrays.asList(worker2, trainee1);
		List<Employee> beginsWithA = HumanResourcesStatistics.surnameBeginsWithA(manager1);
		Assert.assertEquals(expectedList, beginsWithA);
	}

	@Test
	public void shouldReturnListOfEmployeesWhoEarnMoreThan1000() {
		List<Employee> expectedList = Arrays.asList(director, manager1, manager2, manager3);
		List<Employee> eariningMoreThan1000 = HumanResourcesStatistics.earnMoreThan1000(_allEmployees);
		Assert.assertEquals(expectedList, eariningMoreThan1000);
	}

}