package com.fv.ContainerApp.Model;

/**
 * The Container class contains a private container objects and stores them
 *
 * @author Fabian Valerius
 * @version 1.0.2
 *
 */
public class Container {

   // Fields
    private final String containerName;
    private final String location;
    private final boolean status;
    private final String deliveryDate;
    private final String  volume;
    private final String weight;
    private final String typeOfContainer;

    public Container(String containerName, String location, boolean status, String deliveryDate,
                     String volume, String weight, String typeOfContainer) {
        this.containerName = containerName;
        this.location = location;
        this.status = status;
        this.deliveryDate = deliveryDate;
        this.volume = volume;
        this.weight = weight;
        this.typeOfContainer = typeOfContainer;
    }

    public String getContainerName() {
        return containerName;
    }

    public String getLocation() {
        return location;
    }

    public boolean getStatus() {
        return status;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getVolume() {
        return volume;
    }

    public String  getWeight() {
        return weight;
    }

    public String getTypeOfContainer() {
        return typeOfContainer;
    }
}
