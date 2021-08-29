package ec.carper.javacore.scope;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScopeTest {

	private static final String NOMBRE = "CARLOS JULIO";
	private static final String NOMBRE2 = "JOSE ANTONIO";

	@Test
	public void singletonTest() {
		final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("scopes.xml");
		final Person personA = (Person) applicationContext.getBean("personSingleton");
		final Person personB = (Person) applicationContext.getBean("personSingleton");

		personA.setName(NOMBRE);
		assertEquals(NOMBRE, personB.getName());

		System.out.println(personA.getName());
		System.out.println(personB.getName());

		((AbstractApplicationContext) applicationContext).close();
	}

	@Test
	public void prototypeTest() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("scopes.xml");
		Person personPrototypeA = (Person) applicationContext.getBean("personPrototype");
		Person personPrototypeB = (Person) applicationContext.getBean("personPrototype");

		personPrototypeA.setName(NOMBRE);
		personPrototypeB.setName(NOMBRE2);

		System.out.println(personPrototypeA.getName());
		System.out.println(personPrototypeB.getName());

		assertEquals(NOMBRE, personPrototypeA.getName());
		assertEquals(NOMBRE2, personPrototypeB.getName());

		((AbstractApplicationContext) applicationContext).close();
	}

}