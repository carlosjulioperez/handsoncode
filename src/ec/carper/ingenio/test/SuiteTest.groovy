package ec.carper.ingenio.test

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SuiteTest extends TestCase{
	
    public static Test suite(){
       TestSuite suite = new TestSuite("Prueba integral o de integración...");
       
       // Tablas
       suite.addTestSuite(AreaTest.class);
       suite.addTestSuite(BrixDensidadTitSusTest.class);
       suite.addTestSuite(BrixDensidadWpTest.class);
       suite.addTestSuite(ModuloTest.class);
       suite.addTestSuite(TurnoTest.class);
       suite.addTestSuite(VariedadTest.class);

       // Transacciones
       suite.addTestSuite(DiaTrabajoTest.class);
       suite.addTestSuite(CanaTest.class);
       suite.addTestSuite(TrashCanaTest.class);

       return suite;
    }
}
