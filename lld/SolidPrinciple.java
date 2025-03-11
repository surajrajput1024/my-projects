/**
 * Solid Principle:
 * S-  Single responsibility principle: Single reason to change
 * 
 * O - Open close principle: Open for extension but closed for modification
 * 
 * L - Liskov's Substitution principle: If class B is subclass of A and we want to substitute class B for class A then it should not change the behaviour
 * of the program. In other words, child should add more capabikities not narrow it down or change behaviour.
 * 
 * I - Interface Seggregation principle: Interface should be such that client should not implement the methods that it does not need.
 * 
 * D - Dependency Inversion principle: High level modules should not depend on low level modules. Both should depend on abstractions.
 * 
 * Benefits of SOLID:
 * 1. Easy to maintain
 * 2. Reduces complexity
 * 3. Reduces bugs
 * 4. Reduces coupling
 * 5. Increases reusability
 * 6. Increases readability
 * 7. Increases scalability
 * 8. Increases performance
 * 9. Increases security
 * 10. Increases productivity
 * 11. Increases maintainability
 * 12. Increases flexibility
 * 13. Increases robustness
 * 14. Increases efficiency
 * 15. Increases quality and many more..
 */


/**
 * Single Responsibily Principle: A class should have only one reason to change.
 * 1. Easy to maintain
 * 2. Easy to understand
 */

class Marker {
    String name;
    String color;
    int year;
    int price;

    Marker(String name, String color, int year, int price) {
        this.name = name;
        this.color = color;
        this.year = year;
        this.price = price;
    }
}

/**
 * Invoice has a marker object.
 */
class Invoice {
   private Marker marker;
   private int quantity;

   public Invoice(Marker marker, int quantity) {
    this.marker = marker;
    this.quantity = quantity;
   }

   public int calculateTotal() {
    int price = (marker.price) * this.quantity;
    return price;
   }
}


class InvoiceDataAccessLayer {
    private Invoice invoice;

    public void saveToDB() {
        // save to DB or save to files logic should be here only.
    }
}

class InvoiceDisplay {
    private Invoice invoice;

    public void print() {
        // print logic should be here only 
    }
}

/**
 * Open/Close Principle: Open for extension but closed for modification
 */
class InvoiceDataAccessLayer {
    private Invoice invoice;

    public void saveToDB() {
        // save to DB or save to files logic should be here only.
    }

    public void saveToS3() {
        // not following Open/close principle
    }
}

// instead we can do following :
interface InvoiceDataAccessLayer {
    public void save(Invoice invoice);
}

class DatabaseInvoiceDAL implements InvoiceDataAccessLayer {
    @Override
    public void save(Invoice invoice) {
        // save to db
    }
}

class CloudInvoiceDAL implements InvoiceDataAccessLayer {
    @Override
    public void save(Invoice invoice) {
        // save to S3
    }
}

class FileInvoiceDAL implements InvoiceDataAccessLayer {
    @Override 
    public void save(Invoice invoice) {
        // save to file.
    }
}


/**
 * Liskov's Substitution principle: If class B is subtype of class A, then we should be able to replace object of A with B
 * without breaking the behavious of the program.
 * 
 * Subclass should extend the capability of parent not narrow it down.
 */

interface Bike {
    void turnOnEngine();
    void accelerate();
}

class MotorCycle implements Bike {
    boolean isEngineOn;
    int speed;

    Bike() {
        this.isEngineOn = false;
        this.speed = 0;
    }

    @Override
    public void turnOnEngine() {
     isEngineOn = true;
    }

    @Override
    public void accelerate() {
        speed += 10;
    }
}

class Bicycle implements Bike {
    // It will narrow down the capabilities of it's parent and will also change 
    // the behaviour of parent class so it's not following liskov's substitution principle. 
    public void turnOnEngine() {
        throw new AssertionError("there is no engine!");
    }

    public void accelerate() {
        // do something!
    }
}


/**
 * Interface Segmented principle: Interfaces should be such, that client shouldn't implement
 * unncessary functions they do not need!
 * 
 * If we have a class that implements an interface, then it should not implement the methods that it does not need.
 * For example For a waiter class, it should not implement washDishes() and cookFood() methods.
 * For a chef class, it should not implement washDishes() and serverCustomers() methods.
 * 
 * So we need to segregate the interface into smaller interfaces.
 * 
 */
interface RestaurantEmployee {
    void washDishes();
    void serverCustomers();
    void cookFood();
}

class Waiter implements RestaurantEmployee {
    public void washDishes() {
        // not my job
    }

    public void serverCustomers() {
        // yes and here is my implementation
        System.out.print("I'll do this!");
    }

    public void cookFood() {
        // not my job
    }
}


/**
 * It should be seggregated into smaller interfaces. like:
 * 
 */
interface DishWasher {
    void washDishes();
}

interface FoodServer {
    void serverCustomers();
}

interface FoodCooker {
    void cookFood();
}

class Waiter implements FoodServer {
    public void serverCustomers() {
        // yes and here is my implementation
        System.out.print("I'll do this!");
    }
}

class Chef implements FoodCooker {
    public void cookFood() {
        // yes and here is my implementation
        System.out.print("I'll do this!");
    }
}

/**
 * Dependency Inversion principle: Class should dependens on interfaces not on concrete classes.
 *
 * High level modules should not depend on low level modules. Both should depend on abstractions.
 *
**/
class Macbook {
    private final WiredKeyboard wiredKeyboard;
    private final WiredMouse wiredMouse;

    Macbook(WiredKeyboard wiredKeyboard, WiredMouse wiredMouse) {
        // This is not following dependency inversion principle. as it's depending on concrete classes.
        // Suppose in future we want to change the wiredKeyboard to wirelessKeyboard then we need to change the code here.
        // So, it's not following dependency inversion principle.
        this.wiredKeyboard = new WiredKeyboard();
        this.wiredMouse = new WiredMouse();
    }
}

// Instead we can do following:
interface Keyboard {
    void type();
}

interface Mouse {
    void click();
}

class WiredKeyboard implements Keyboard {
    public void type() {
        // type something
    }
}

class WiredMouse implements Mouse {
    public void click() {
        // click something
    }
}

class WirelessKeyboard implements Keyboard {
    public void type() {
        // type something
    }
}

class WirelessMouse implements Mouse {
    public void click() {
        // click something
    }
}

class Macbook {
    private final Keyboard keyboard;
    private final Mouse mouse;

    // Now we can send any type of keyboard and mouse to the Macbook class.
    Macbook(Keyboard keyboard, Mouse mouse) {
        this.keyboard = keyboard;
        this.mouse = mouse;
    }
}








