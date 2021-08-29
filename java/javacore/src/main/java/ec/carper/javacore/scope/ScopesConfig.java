package ec.carper.javacore.scope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ScopesConfig {

	@Bean
	@Scope("singleton")
	public Person personSingleton() {
		return new Person();
	}
}
