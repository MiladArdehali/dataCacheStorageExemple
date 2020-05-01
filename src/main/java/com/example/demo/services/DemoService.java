package com.example.demo.services;


import com.digilion.dataAccessClass.element.DacasElement;
import com.digilion.dataAccessClass.manager.DacasManager;
import com.example.demo.bo.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Example of service class
 *
 * @author Digital Lion
 * @since 1.0
 */
@Service
public class DemoService {

    @Autowired
    public DacasManager dacasManager;

    /**
     * Default Constructor
     */
    public DemoService() {
    }

    /**
     * Exemple to inject Object into DataCacheStorage.
     * @param carToInject
     * <u>Insert Objects in Dacas without package them into a DacasElement is not recommanded</u>
     */
    public void exampleInjectionObjectDataIntoDacas(Car carToInject) {
        // Commit direct into dacaStorage
        dacasManager.dacaStorage.commit(carToInject.getLicensePlate(), carToInject);
    }

    /**
     * Exemple to inject Object into DataCacheStorage.
     * @param carToInject
     */
    public void exampleInjectionDacasElementIntoDacas(Car carToInject) {
        DacasElement dacasElement = new DacasElement();
        dacasElement.setValue(carToInject);
        // It is recommanded to fix DateTimeStorage and ExpiredDateTime for your dacasElement.
        //If you don't define DateTimeStorage and/or ExpiredDateTime; default values defined on configuraton is set.
        LocalDateTime now = LocalDateTime.now();
        dacasElement.setDateTimeStorage(now);
        dacasElement.setExpiredDateTime(now.plusHours(2));

        // Commit your object packaged into DacasElement in dacasStrorage
        dacasManager.dacaStorage.commit(carToInject.getLicensePlate(), carToInject);
    }

    /**
     * Exemple to inject Object into DataCacheStorage with DacasElementBuilder.
     * @param carToInject
     */
    public void exampleInjectionDacasElementIntoDacasWithBuilder(Car carToInject) {
        // You can also use builder
        DacasElement dacasElementBuilder;
        LocalDateTime now = LocalDateTime.now();
        dacasElementBuilder = new DacasElement.DacasElementBuilder<>().value(carToInject).dateTimeStorage(now).lifeTime(now.plusHours(2)).build();

        // Commit your object packaged into DacasElement in dacasStrorage
        dacasManager.dacaStorage.commit(carToInject.getLicensePlate(), dacasElementBuilder);
    }

    /**
     * Example access to your data
     * @param key
     * @return car
     */
    public Car exampleAccessToYourData(String key) {
        // When you recover your value, you directly recover your value and not a DacasElement.
        // Cast your recovered data, that's all.
        return (Car) dacasManager.dacaStorage.get(key);
    }

    /**
     * Example of pushing element in memory into dacasmemoriesvalues.dacas file
     */
    public void forceBackup() {
        // If DataCacheStorage support storage is configured as a file.
        // Push method send all data from memory into file referenced into properties.
        // Before writing, all expired data will be flushed.
        dacasManager.dacaStorage.push();

    }



}
