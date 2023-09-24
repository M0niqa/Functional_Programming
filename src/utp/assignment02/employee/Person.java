package utp.assignment02.employee;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public abstract class Person {

	// To implement an attribute means that you provide a backing field and
	// getter or optionally setter for read-write properties/attributes
	//
	// NO BACKING FIELDS SHOULD BE PROVIDED FOR DERIVED ATTRIBUTES
	// THOSE SHOULD BE COMPUTED ON-LINE
	//
	// attributes:
	// * first name (read-only)
	// * surname (read-only)
	// * birth date (read-only) --- date MUST BE represented by an instance of
	// the type designed for date representation (either Date or LocalDate)
	//
	// * age (derived --- computed based on birth date) --- implemented as a
	// getter calculating the difference between the current date and birthdate

	private final String _firstName; // backing field
	private final String _surname;
	private final LocalDate _birthDate;

	protected Person(String firstName, String surname, LocalDate birthDate) {

		_firstName = firstName;
		_surname = surname;
		_birthDate = birthDate;
	}

	public String getFirstName() {
		return _firstName;
	}

	public short getAge() {
		return (short) Period.between(_birthDate, LocalDate.now()).getYears();
	}

	public String getSurname() {
		return _surname;
	}

	public LocalDate getBirthDate() {
		return _birthDate;
	}

	@Override
	public String toString() {
		return _firstName + " " + _surname;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_birthDate, _firstName, _surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(_birthDate, other._birthDate) && Objects.equals(_firstName, other._firstName)
				&& Objects.equals(_surname, other._surname);
	}	
	
	// (assignment 03)
	// methods:
	// * is older than other person
	// * is younger than other person
	// * compare age with other person's age
	
	public boolean isOlderThanGivenPerson(Person person) {
		return this.getAge() > person.getAge();
	}
	
	public boolean isYoungerThanGivenPerson(Person person) {
		return this.getAge() < person.getAge();
	}
	
	public int compareAge(Person person) {
	    if (isOlderThanGivenPerson(person)) {
	        return 1;
	    }
	    else if (isYoungerThanGivenPerson(person)) {
	        return -1;
	    }
	    else {
	    	return 0; 
	    }
	}	
}