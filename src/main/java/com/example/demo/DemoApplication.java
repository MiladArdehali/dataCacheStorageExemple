package com.example.demo;

import com.example.demo.bo.Car;
import com.example.demo.services.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class DemoApplication {

	/**
	 * Definition of logger
	 */
	private static Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		runDataCacheStorageDemo();
	}

	/**
	 * Demonstration method
	 */
	public static void runDataCacheStorageDemo() {
		DemoService demoService = new DemoService();
		// Example of object to inject into DataCacheStorage
		Car myBeetle = new Car("VolksWagen", "Beetle", 50, LocalDate.of(1972, Month.JANUARY, 12), "MB-123-RC");
		Car myFiat= new Car("Fiat", "500", 38, LocalDate.of(1982, Month.AUGUST, 25), "TR-987-KV");
		Car myFord= new Car("Ford", "GT", 550, LocalDate.of(1991, Month.NOVEMBER, 03), "GT-987-LM");

		//Example of direct injection of Object into DataCacheStorage.
		// Possible but not recommanded because you don't set an expiration date at your data
		demoService.exampleInjectionDacasElementIntoDacas(myBeetle);

		//Example of good practice for injection of Object into DataCacheStorage.
		//Your object is packaged into DacasElement.
		demoService.exampleInjectionObjectDataIntoDacas(myFiat);
		demoService.exampleInjectionDacasElementIntoDacasWithBuilder(myFord);

		// We force backup before scheduled task execution (for this application example)
		demoService.forceBackup();

		// Example of acces to your data
		Car myBeetleRecovered = demoService.exampleAccessToYourData("MB-123-RC");
		logger.info("First Circulation date of my recovered car : " + myBeetleRecovered.getFistCirculationDate());
	}

}
