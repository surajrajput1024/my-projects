/*
 * Listkov's Substitution Principle: If class B is subset of class A, then we should be able to replace object of A with B
 * and it should not change the behavior of the program.
 * 
 * For example: If we have class Vehicle and we have class Car which extends Vehicle. If we have a method which takes Vehicle
 * as parameter, we should be able to pass Car object to that method.
 * 
 * If we have a method which takes Vehicle as parameter, we should be able to pass Car object to that method.
 * 
 */
class Vehicle {
    
    public int getNumberOfWheels() {
        return 2;
    }

    public Boolean hasEngine() {
        return true;
    }

}

class Car extends Vehicle {
    @Override
    public int getNumberOfWheels() {
        return 4;
    }
}

class Bike extends Vehicle {

}

class Bicycle extends Vehicle {
    @Override
    public Boolean hasEngine() {
        return null;
    }
}

class Main {
    public static void main(String[] args) {
       List<Vehicle> vehicles = new ArrayList<>();
       vehicles.add(new Car());
       vehicles.add(new Bike());
       vehicles.add(new Bicycle());
       
       for(Vehicle vehicle: vehicles) {
           System.out.println(vehicle.hasEngine().toString()); // null pointer exception on Bicycle object.
       }
}
}

// the behavior of the program. This is violation of Liskov's Substitution principle.
// Adding bicycle object to the list of vehicles will break the program. 

//Solution: Put only generic methods in parent class and specific methods in child class.
// For example: getNumberOfWheels() is a generic method and hasEngine() is a specific method.
// So, we should put getNumberOfWheels() in parent class and hasEngine() in child class.
class Vehicle {
    
    public int getNumberOfWheels() {
        return 2;
    }

}

class Car extends  {
    @Override
    public int getNumberOfWheels() {
        return 4;
    }

    public Boolean hasEngine() {
        return true;
    }
}

class Bike extends Vehicle {
    public Boolean hasEngine() {
        return true;
    }
}

class Bicycle extends Vehicle {
}

class Main {
    public static void main(String[] args) {
       List<Vehicle> vehicles = new ArrayList<>();
       vehicles.add(new Car());
       vehicles.add(new Bike());
       vehicles.add(new Bicycle());
       
       for(Vehicle vehicle: vehicles) {
           System.out.println(vehicle.getNumberOfWheels()); // we won't be able to access hasEngine() method on Vehicle object.
       }
}
}


