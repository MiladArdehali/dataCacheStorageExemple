package com.example.demo;

import com.example.demo.bo.Car;
import com.example.demo.services.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class DemoApplication {

	/**
	 * Definition of logger
	 */
	Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	@Autowired
	DemoService demoService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	public void runDataCacheStorageDemo() {
		// Example of object to injest into DataCacheStorage
		Car myBeetle = new Car("VolksWagen", "Beetle", 50, LocalDate.of(1972, Month.JANUARY, 12), "MB-123-RC");
		Car myFiat= new Car("Fiat", "500", 34, LocalDate.of(1982, Month.AUGUST, 25), "TR-987-KV");

		//Example of direct injection of Object into DataCacheStorage.
		// Possible but not recommanded because you don't set an expiration date at your data
		demoService.exampleInjectionDacasElementIntoDacas(myBeetle);

		//Example of good practice for injection of Object into DataCacheStorage.
		//Your object is packaged into DacasElement.
		demoService.exampleInjectionDacasElementIntoDacasWithBuilder(myFiat);

		// We force backup before scheduled task execution (for this application example)
		demoService.forceBackup();

		// Example of acces to your data
		Car myBeetleRecovered = demoService.exampleAccessToYourData("MB-123-RC");
		Car myFiatRecovered = demoService.exampleAccessToYourData("TR-987-KV");
		logger.info("myBeetle : " + myBeetleRecovered.getHoursePower());
		logger.info("myFiatRecovered : " + myFiatRecovered.getHoursePower());

	}

}
