import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import utp.assignment02.employee.Worker;
import utp.assignment02.payroll.PayrollEntry;

public class PayrollEntryTest {
	@Test
	public void shouldReturnPayroll() {
		
		Worker worker1 = new Worker("Monika", "Stanko", LocalDate.of(1990, 7, 21), BigDecimal.valueOf(800), null,
				LocalDate.of(2020, 2, 18), BigDecimal.valueOf(150));
		PayrollEntry expected = new PayrollEntry(worker1, BigDecimal.valueOf(800), BigDecimal.valueOf(150));
		PayrollEntry payroll = new PayrollEntry(worker1, BigDecimal.valueOf(800), BigDecimal.valueOf(150));
		
		Assert.assertEquals(expected, payroll);
	}
}
