package patterns.structural;

/**
 * Decorator Pattern: Attach additional responsibilities to an object dynamically.
 *  Decorators provide a flexible alternative to subclassing for extending functionality.
 * 
 * The decorator pattern is a structural pattern that allows adding new behaviors to objects 
 * dynamically by placing them inside special wrapper objects called decorators.
 * 
 * Real world example:
 * Imagine you run a car service shop offering multiple services. Now how do you calculate the
 * bill to be paid by a customer? You pick one service and dynamically add the cost of the
 * selected services. Here each type of service is a decorator.
 * 
 * In plain words:
 * Decorator pattern lets you dynamically change the behavior of an object at run time by wrapping
 * them in an object of a decorator class.
 * 
 * Wikipedia says:
 * In object-oriented programming, the decorator pattern is a design pattern that allows behavior
 * to be added to individual objects, either statically or dynamically, without affecting the
 * behavior of other objects from the same class. The decorator pattern is often useful for
 * adhering to the Single Responsibility Principle, as it allows functionality to be divided
 * between classes with unique areas of concern.
 * 
 * Programmatic Example:
 * Let's take a simple example of a coffee. First, we have a simple coffee implementing the coffee
 * interface. We can get the cost and the ingredients of the coffee. Then we can decorate the coffee
 * with some condiments like milk, soy, and whip. The cost of a coffee with a condiment is the sum of
 * the coffee and the cost of the condiment.
 */

// Step 1: Create an interface
interface Coffee {
    double getCost();
    String getIngredients();
}

// Step 2: Create a concrete class implementing the interface
class SimpleCoffee implements Coffee {
    @Override
    public double getCost() {
        return 1;
    }

    @Override
    public String getIngredients() {
        return "Coffee";
    }
}

// Step 3: Create a decorator class implementing the interface
abstract class CoffeeDecorator implements Coffee {
    protected final Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee decoratedCoffee) {
        this.decoratedCoffee = decoratedCoffee;
    }

    public double getCost() {
        return decoratedCoffee.getCost();
    }

    public String getIngredients() {
        return decoratedCoffee.getIngredients();
    }
}

// Step 4: Create concrete decorators
class Milk extends CoffeeDecorator {
    public Milk(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    public double getCost() {
        return super.getCost() + 0.5;
    }

    public String getIngredients() {
        return super.getIngredients() + ", Milk";
    }
}

class Whip extends CoffeeDecorator {
    public Whip(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    public double getCost() {
        return super.getCost() + 0.7;
    }

    public String getIngredients() {
        return super.getIngredients() + ", Whip";
    }
}

/**
 * Why would you use it?
 * 
 * The decorator pattern is a great way to add responsibilities to individual objects without
 * inheriting from them. Also, it's a flexible alternative to subclassing.
 * 
 * When you want to add behavior or state to individual objects at runtime.
 */

public class DecoratorPattern {
    
}

/**
 * Interview questions:
 * 1. What is the decorator pattern? 
 *  - The decorator pattern is a structural pattern that allows adding new behaviors to objects
 *    dynamically by placing them inside special wrapper objects called decorators.
 *  - Decorators provide a flexible alternative to subclassing for extending functionality.
 * 
 * 2. What is the difference between the decorator pattern and the adapter pattern?
 * - The decorator pattern is a structural pattern that allows adding new behaviors to objects
 *  dynamically by placing them inside special wrapper objects called decorators. Decorators
 * provide a flexible alternative to subclassing for extending functionality. while the adapter
 * pattern is a structural pattern that allows objects with incompatible interfaces to work together.
 * 
 * 3. What are the benefits of the decorator pattern?
 * - The decorator pattern allows adding new behaviors to objects dynamically by placing them inside
 * special wrapper objects called decorators.
 * - Decorators provide a flexible alternative to subclassing for extending functionality.
 * - The decorator pattern allows adding behavior or state to individual objects at runtime.
 * 
 * 4. What are the drawbacks of the decorator pattern?
 * - The decorator pattern can lead to a large number of small classes if you have many decorators.
 * - The decorator pattern can be complex to understand for beginners.
 * 
 * 5. Can you give an example of when you would use the decorator pattern?
 * - You would use the decorator pattern when you want to add responsibilities to individual objects
 * without inheriting from them. Also, it's a flexible alternative to subclassing.
 * - When you want to add behavior or state to individual objects at runtime.
 * 
 * 6. What is the difference between the decorator pattern and the strategy pattern?
 * - The decorator pattern is a structural pattern that allows adding new behaviors to objects
 * dynamically by placing them inside special wrapper objects called decorators. Decorators provide
 * a flexible alternative to subclassing for extending functionality.
 * - The strategy pattern is a behavioral pattern that allows selecting an algorithm at runtime from
 * a family of algorithms. The strategy pattern defines a family of algorithms, encapsulates each
 * algorithm, and makes the algorithms interchangeable.
 * 
 * 7. Can you give an example of when you would use the decorator pattern in a real-world scenario?
 * - You would use the decorator pattern when you want to add responsibilities to individual objects
 * without inheriting from them. Also, it's a flexible alternative to subclassing.
 * - When you want to add behavior or state to individual objects at runtime.
 * - For example, you could use the decorator pattern to add new features to a text editor, such as
 * spell checking, grammar checking, and auto-correction.
 * 
 * 8. What is decorator chaining?
 * - Decorator chaining is when you chain multiple decorators together to add multiple behaviors to an
 * object. For example, you could chain a spell checker decorator, a grammar checker decorator, and an
 * auto-correction decorator to add multiple features to a text editor.
 * 
 * 9. What is the difference between the decorator pattern and the composite pattern?
 * - The decorator pattern is a structural pattern that allows adding new behaviors to objects
 * dynamically by placing them inside special wrapper objects called decorators. Decorators provide
 * a flexible alternative to subclassing for extending functionality.
 * - The composite pattern is a structural pattern that allows us to compose objects into tree
 * structures to represent part-whole hierarchies. The composite pattern lets clients treat individual
 * objects and compositions of objects uniformly.
 * 
 * 10. What is the difference between the decorator pattern and the proxy pattern?
 * - The decorator pattern is a structural pattern that allows adding new behaviors to objects
 * dynamically by placing them inside special wrapper objects called decorators. Decorators provide
 * a flexible alternative to subclassing for extending functionality.
 * - The proxy pattern is a structural pattern that allows us to provide a surrogate or placeholder
 * for another object to control access to it. The proxy pattern allows us to add a level of
 * indirection to an object to control access to it.
 * 
 * 11. What is difference between abstract and interface?
 * - An abstract class can have both abstract and non-abstract methods, while an interface can only
 * have abstract methods.
 * - An abstract class can have instance variables, while an interface cannot have instance variables.
 * - A class can extend only one abstract class, while a class can implement multiple interfaces.
 * - An abstract class can have constructors, while an interface cannot have constructors.
 * - An abstract class can have method implementations, while an interface cannot have method
 * implementations.
 * 
 */
