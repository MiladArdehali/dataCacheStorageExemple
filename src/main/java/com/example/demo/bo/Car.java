package com.example.demo.bo;

import java.time.LocalDate;

/**
 * Exemple of POJO
 *
 * @author Digital Lion
 * @since 1.0
 */
public class Car {

    private String brand;
    private String model;
    private Integer hoursePower;
    private LocalDate fistCirculationDate;
    private String licensePlate;

    /**
     * Default Constructor
     */
    public Car() {
    }

    /**
     * All argument constructor
     *
     * @param brand
     * @param model
     * @param hoursePower
     * @param fistCirculationDate
     * @param licensePlate
     */
    public Car(String brand, String model, Integer hoursePower, LocalDate fistCirculationDate, String licensePlate) {
        this.brand = brand;
        this.model = model;
        this.hoursePower = hoursePower;
        this.fistCirculationDate = fistCirculationDate;
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getHoursePower() {
        return hoursePower;
    }

    public void setHoursePower(Integer hoursePower) {
        this.hoursePower = hoursePower;
    }

    public LocalDate getFistCirculationDate() {
        return fistCirculationDate;
    }

    public void setFistCirculationDate(LocalDate fistCirculationDate) {
        this.fistCirculationDate = fistCirculationDate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
