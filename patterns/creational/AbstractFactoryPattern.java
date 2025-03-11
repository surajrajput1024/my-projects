package patterns.creational;

/**
 * Abstract Factory Pattern: Provide an interface for creating families of related or dependent
 * objects without specifying their concrete classes.
 * 
 * The abstract factory pattern is a creational pattern that provides an interface for creating
 * families of related or dependent objects without specifying their concrete classes. The abstract
 * factory pattern is a creational pattern that provides an interface for creating families of
 * related or dependent objects without specifying their concrete classes. 
 * 
 * Real world example:
 * Consider the case of a hiring manager. It is impossible for one person to interview for each of
 * the positions. Based on the job opening, she has to decide and delegate the interview steps to
 * different people.
 * 
 * In plain words:
 * It provides a way to delegate the instantiation logic to child classes.
 * 
 * 
 * Difference between Factory Method and Abstract Factory:
 * Factory Method: It provides an interface for creating objects in a superclass, but allows subclasses
 * to alter the type of objects that will be created.
 * Abstract Factory: It provides an interface for creating families of related or dependent objects
 * without specifying their concrete classes.
 * 
 * Real world example:
 * 1. Consider the case of a hiring manager. It is impossible for one person to interview for each of
 * the positions. Based on the job opening, she has to decide and delegate the interview steps to
 * different people.
 * 
 * 2. Vehicle factory: A vehicle factory produces different types of vehicles like cars, trucks,
 * scooters, and motorcycles. All these vehicles have common components like engine, body, and
 * wheels but they are different in their implementations. The factory can't produce a truck with two
 * wheels or a bike with 18 wheels.
 * 
 * 3. GUI factory: Consider a theme in an operating system. A theme is a set of colors for the
 * interface, like the window color, the text color, etc. Different operating systems have different
 * themes like Mac OS, Windows, and Linux. Each theme has a different set of colors for the
 * interface. The GUI factory can produce different themes for different operating systems.
 * 
 * 
 * Interview questions:
 * 1. What is the abstract factory pattern?
 *  - The abstract factory pattern is a creational pattern that provides an interface for creating
 * families of related or dependent objects without specifying their concrete classes.
 * 
 * 2. What is the difference between the factory method and abstract factory patterns?
 * - Factory Method: It provides an interface for creating objects in a superclass, but allows
 * subclasses to alter the type of objects that will be created.
 * - Abstract Factory: It provides an interface for creating families of related or dependent objects
 * without specifying their concrete classes.
 * 
 * 3. What are the real-world examples of the abstract factory pattern?
 * - Hiring manager: It is impossible for one person to interview for each of the positions. Based on
 * the job opening, she has to decide and delegate the interview steps to different people.
 * - Vehicle factory: A vehicle factory produces different types of vehicles like cars, trucks,
 * scooters, and motorcycles. All these vehicles have common components like engine, body, and
 * wheels but they are different in their implementations. The factory can't produce a truck with two
 * wheels or a bike with 18 wheels.
 * - GUI factory: Consider a theme in an operating system. A theme is a set of colors for the
 * interface, like the window color, the text color, etc. Different operating systems have different
 * themes like Mac OS, Windows, and Linux. Each theme has a different set of colors for the
 * interface. The GUI factory can produce different themes for different operating systems.
 * 
 * 4. What are the benefits of the abstract factory pattern?
 * - It provides a way to delegate the instantiation logic to child classes.
 * - It provides an interface for creating families of related or dependent objects without specifying
 * their concrete classes.
 * 
 * 5. What are the drawbacks of the abstract factory pattern?
 * - The abstract factory pattern can lead to a large number of small classes if you have many
 * factories.
 * - The abstract factory pattern can be complex to understand for beginners.
 * 
 * 6. Can you give an example of when you would use the abstract factory pattern?
 * - You would use the abstract factory pattern when you want to create families of related or
 * dependent objects without specifying their concrete classes.
 * 
 * 7. What is the difference between the abstract factory pattern and the factory method pattern?
 * - Factory Method: It provides an interface for creating objects in a superclass, but allows
 * subclasses to alter the type of objects that will be created.
 * - Abstract Factory: It provides an interface for creating families of related or dependent objects
 * without specifying their concrete classes.
 */
// Step 1: Create an interface
public class AbstractFactoryPattern {
    
}
