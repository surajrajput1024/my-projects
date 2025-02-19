interface Vehicle {
    String getType();
}

// implementation 
class Car implements Vehicle {
    @Override
    public String getType() {
        return "Car";
    }
}

class Bike implements Vehicle {
    @Override
    public String getType() {
        return "Bike";
    }
}

class Truck implements Vehicle {
    @Override
    public String getType() {
        return "Truck";
    }
}

abstract class VehicleFactory {
    abstract Vehicle createVehicle();
}

class CarFactory extends VehicleFactory {
    // Write your code here
}

class BikeFactory extends VehicleFactory {
    // Write your code here
}

class TruckFactory extends VehicleFactory {
    // Write your code here
}
