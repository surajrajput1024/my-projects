/**
 * Is A - Inheritance means a class is a subset of another class. For example: Car is a Vehicle.
 * Has A - Composition means a class has a reference to another class. For example: Invoice has a marker object.
 */

class Vehicle {
    public void drive() {
        // drive logic with normal capability
    }
}

class SportyVehicle extends Vehicle {
    @Override
    public void drive() {
        // drive logic with special capability
    }
}

class OffRoadVehicle extends Vehicle {
    @Override
    public void drive() {
        // drive logic with special capability
    }
}

class PassengerVehicle extends Vehicle {
    @Override
    public void drive() {
        // drive logic with special capability
    }
}

/**
 * Now in above scenario, classes SportyVehicle, OffRoadVehicle and PassengerVehicle are extending Vehicle class.
 * and overriding the drive method. We can use Strategy Design pattern here. As we can see different childs are
 * overriding and re-implementing drive method with special capability and that's the duplicate implementation.
 * 
 * So here we can use Strategy Design pattern. We can create an interface DriveStrategy and implement it in different
 * classes with different capabilities.
 * 
 * Strategy Design pattern is used when we have multiple classes that are similar to each other and they are only
 * different in the way they perform some specific task. In this case, we can create an interface and implement it in
 * different classes with different capabilities.
 * 
 * For real life projects example:
 * 
 * 1. Payment Gateway: Different payment gateways have different payment processing logic. So we can create an interface
 * PaymentGateway and implement it in different classes with different payment processing logic.
 * 
 * 2. Sorting Algorithm: Different sorting algorithms have different sorting logic. So we can create an interface
 * SortingAlgorithm and implement it in different classes with different sorting logic.
 * 
 * 3. File Compression: Different file compression algorithms have different compression logic. So we can create an
 * interface CompressionAlgorithm and implement it in different classes with different compression logic.
 * 
 * 4. Image Processing: Different image processing algorithms have different image processing logic. So we can create an
 * interface ImageProcessingAlgorithm and implement it in different classes with different image processing logic.
 * 
 * 5. Database Connection: Different database connection algorithms have different database connection logic. So we can
 * create an interface DatabaseConnectionAlgorithm and implement it in different classes with different database connection
 * logic.
 */

 1. Above example is a good example of Strategy Design pattern. We have created an interface DriveStrategy and implemented
    it in different classes with different capabilities.

interface DriveStrategy {
    void drive();
}

interface NormalDriveStrategy extends DriveStrategy {
    @Override
    void drive();
}

interface SpecialDriveStrategy extends DriveStrategy {
    @Override
    void drive();
}

class NormalDrive implements NormalDriveStrategy {
    @Override
    public void drive() {
        // drive logic with normal capability
    }
}

class SportyDrive implements SpecialDriveStrategy {
    @Override
    public void drive() {
        // drive logic with special capability
    }
}

class OffRoadDrive implements SpecialDriveStrategy {
    @Override
    public void drive() {
        // drive logic with special capability
    }
}

class PassengerDrive implements SpecialDriveStrategy {
    @Override
    public void drive() {
        // drive logic with special capability
    }
}

class Vehicle {
    DriveStrategy driveStrategy;

    public void setDriveStrategy(DriveStrategy driveStrategy) {
        this.driveStrategy = driveStrategy;
    }

    public void drive() {
        driveStrategy.drive();
    }
}

2. Database connection using Strategy Design pattern:

interface DatabaseConnectionAlgorithmStrategy {
    void connect();
}

class MySQLConnection implements DatabaseConnectionAlgorithmStrategy {
    @Override
    public void connect() {
        // MySQL connection logic
    }
}

class OracleConnection implements DatabaseConnectionAlgorithmStrategy {
    @Override
    public void connect() {
        // Oracle connection logic
    }
}

class DatabaseConnection {
    DatabaseConnectionAlgorithmStrategy databaseConnectionAlgorithmStrategy;

    public void setDatabaseConnectionAlgorithmStrategy(DatabaseConnectionAlgorithmStrategy databaseConnectionAlgorithmStrategy) {
        this.databaseConnectionAlgorithmStrategy = databaseConnectionAlgorithmStrategy;
    }

    public void connect() {
        databaseConnectionAlgorithmStrategy.connect();
    }
}

class Main {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.setDatabaseConnectionAlgorithmStrategy(new MySQLConnection());
        databaseConnection.connect();
        system.out.println("Connected to MySQL database");

        databaseConnection.setDatabaseConnectionAlgorithmStrategy(new OracleConnection());
        databaseConnection.connect();
        system.out.println("Connected to Oracle database");
    }
}

3. Payment Gateway using Strategy Design pattern:

interface PaymentGatewayStrategy {
    void processPayment();
}

class PayPalPaymentGateway implements PaymentGatewayStrategy {
    @Override
    public void processPayment() {
        // PayPal payment processing logic
    }
}

class StripePaymentGateway implements PaymentGatewayStrategy {
    @Override
    public void processPayment() {
        // Stripe payment processing logic
    }
}

class PaymentGateway {
    PaymentGatewayStrategy paymentGatewayStrategy;

    public void setPaymentGatewayStrategy(PaymentGatewayStrategy paymentGatewayStrategy) {
        this.paymentGatewayStrategy = paymentGatewayStrategy;
    }

    public void processPayment() {
        paymentGatewayStrategy.processPayment();
    }
}

class Main {
    public static void main(String[] args) {
        PaymentGateway paymentGateway = new PaymentGateway();
        paymentGateway.setPaymentGatewayStrategy(new PayPalPaymentGateway());
        paymentGateway.processPayment();
        system.out.println("Payment processed using PayPal");

        paymentGateway.setPaymentGatewayStrategy(new StripePaymentGateway());
        paymentGateway.processPayment();
        system.out.println("Payment processed using Stripe");
    }
}

/**
 * When to use Strategy design pattern:
 * 1. When we've multiple classes that are similar to each other and they are only different in the way they perform
 * some specific task.
 * 2. When we want to define a class that will have one behavior that is similar to other behaviors in a list.
 * 3. When we want to change the behavior of an object at runtime.
 * 4. When we want to hide the implementation details of an object from the client.
 * 5. When we want to separate the behavior of an object from the object itself.
 * 6. When we want to reduce the complexity of a class by moving the behavior to another class.
 * 7. When we want to implement different variations of an algorithm and allow the client to choose the best one.
 * 
 * Strategy design pattern is a behavioral design pattern that allows us to define a family of algorithms, encapsulate
 * each one of them, and make them interchangeable. Strategy lets the algorithm vary independently from the clients
 * that use it.
 * 
 * Benefits of Strategy Design pattern:
 * 1. It allows us to define a family of algorithms, encapsulate each one of them, and make them interchangeable.
 * 2. It allows us to change the behavior of an object at runtime.
 * 3. It allows us to hide the implementation details of an object from the client.
 * 4. It allows us to separate the behavior of an object from the object itself.
 * 5. It allows us to reduce the complexity of a class by moving the behavior to another class.
 * 6. It allows us to implement different variations of an algorithm and allow the client to choose the best one.
 * 7. It allows us to implement the SOLID principles.
 * 
 */