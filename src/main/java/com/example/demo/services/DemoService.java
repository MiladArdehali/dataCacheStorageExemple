package com.example.demo.services;

import com.digilion.dataAccessClass.element.DacasElement;
import com.digilion.dataAccessClass.transaction.DacasTransaction;
import com.example.demo.bo.Car;
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

    /**
     * Default Constructor
     */
    public DemoService() {
    }

    /**
     * Example of injecting Object into DataCacheStorage.
     * <p>
     *     <u>Insert Objects in Dacas without package them into a DacasElement is not recommanded</u>
     * </p>
     * @param carToInject
     */
    public void exampleInjectionObjectDataIntoDacas(Car carToInject) {
        // Commit direct into dacaStorage
        // As you don't define DateTimeStorage and/or ExpiredDateTime; Object will be mapped into DacasElement with default values defined on configuraton is set.
        DacasTransaction.commit(carToInject.getLicensePlate(), carToInject);
    }

    /**
     * Example to inject Object packaged into DacasElement to DataCacheStorage.
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
        DacasTransaction.commit(carToInject.getLicensePlate(), dacasElement);
    }

    /**
     * Example to inject Object packaged into DacasElement (using Builder) to DataCacheStorage.
     * @param carToInject
     */
    public void exampleInjectionDacasElementIntoDacasWithBuilder(Car carToInject) {
        // You can also use builder
        DacasElement dacasElementBuilder;
        LocalDateTime now = LocalDateTime.now();
        dacasElementBuilder = new DacasElement.DacasElementBuilder<>().value(carToInject).dateTimeStorage(now).lifeTime(now.plusHours(2)).build();

        // Commit your object packaged into DacasElement in dacasStrorage
        DacasTransaction.commit(carToInject.getLicensePlate(), dacasElementBuilder);
    }

    /**
     * Example access to your data
     * @param key
     * @return car
     */
    public Car exampleAccessToYourData(String key) {
        // When you recover your value, you directly recover your value and not a DacasElement.
        // Cast your recovered data, that's all.
        return (Car) DacasTransaction.get(key);
    }

    /**
     * Example of pushing element in memory into dacasmemoriesvalues.dacas file
     */
    public void forceBackup() {
        // If you configures storage support as a file,
        // method push() send all data from memory into file referenced into properties file.
        // Before writing, all expired data will be flushed.
        DacasTransaction.push();

    }

}