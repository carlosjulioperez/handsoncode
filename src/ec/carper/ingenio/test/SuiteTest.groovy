package ec.carper.ingenio.test

import junit.framework.Test
import junit.framework.TestCase
import junit.framework.TestSuite

class SuiteTest extends TestCase{
	
    public static Test suite(){
       TestSuite suite = new TestSuite("Prueba integral o de integración...")
       
       // Tablas
       suite.addTestSuite(AreaTest.class)
       suite.addTestSuite(BrixDensidadTitSusTest.class)
       suite.addTestSuite(BrixDensidadWpTest.class)
       suite.addTestSuite(MaterialTest.class)
       suite.addTestSuite(ModuloTest.class)
       suite.addTestSuite(ParametroTest.class)
       suite.addTestSuite(TurnoTest.class)
       suite.addTestSuite(UnidadTest.class)
       suite.addTestSuite(VariedadTest.class)

       // Transacciones
       suite.addTestSuite(DiaTrabajoTest.class)
       suite.addTestSuite(TrashCanaTest.class)
       suite.addTestSuite(TrashTest.class)
       suite.addTestSuite(CanaTest.class)
       suite.addTestSuite(BagazoTest.class)
       suite.addTestSuite(PhTest.class)
       suite.addTestSuite(JugoTest.class)
       suite.addTestSuite(MeladuraTest.class)
       suite.addTestSuite(TurbiedadTest.class)
       suite.addTestSuite(FosfatosTest.class)
       suite.addTestSuite(MasasTest.class)
       suite.addTestSuite(MielesTest.class)
       suite.addTestSuite(MielesNutschTest.class)
       suite.addTestSuite(MagmasTest.class)
       suite.addTestSuite(TqFundidorTest.class)
       suite.addTestSuite(ColorMatTest.class)
       suite.addTestSuite(AzucarGranelTest.class)
       suite.addTestSuite(AzucarCrudoTest.class)
       suite.addTestSuite(GrasshoperTest.class)
       suite.addTestSuite(TamanoGranoTest.class)
       suite.addTestSuite(BtuLbBagazoTest.class)
       suite.addTestSuite(Cto24HTest.class)
       suite.addTestSuite(ParoTest.class)

       return suite
    }
}
