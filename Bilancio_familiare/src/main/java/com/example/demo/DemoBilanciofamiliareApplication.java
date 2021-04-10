package com.example.demo;

import java.io.IOException;
import java.net.Socket;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class DemoBilanciofamiliareApplication {

	private static Logger logger=LoggerFactory.getLogger(DemoBilanciofamiliareApplication.class);
	
	public static boolean hostAvailabilityCheck(String address, int port) {
		try (Socket s = new Socket(address, port)) {
		        return true;
		    } catch (IOException ex) {
		    	logger.info(new DemoBilanciofamiliareApplication().messageSource().getMessage("DBNonPresente", new Object[] {address,String.format("%5d",port)}, Locale.getDefault()));
		    }
		    return false;
		}
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.ITALY);
		if (hostAvailabilityCheck("localhost", 3306))
			SpringApplication.run(DemoBilanciofamiliareApplication.class, args);
	}

    @Bean(name = "messageResourceSB")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultLocale(Locale.ITALY);
        messageSource.setBasename("classpath:messaggio");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    } 

}
