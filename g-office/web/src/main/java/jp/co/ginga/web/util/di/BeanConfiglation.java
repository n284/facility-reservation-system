package jp.co.ginga.web.util.di;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiglation {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
