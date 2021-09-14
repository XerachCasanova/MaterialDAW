import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CCuentaTest {
	CCuenta cuenta = new CCuenta();
	

	@ParameterizedTest
	@CsvSource({"1,0","-1,1","-3,2", "0,1"})
	@DisplayName("Caja Blanca - Ingresar")
	
	void testIngresar(double cant,int resultado) {
		assertEquals(resultado,cuenta.ingresar(cant));
	}
	
}
