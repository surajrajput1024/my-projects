package patterns.creational;

/**
 * Factory Pattern: Define an interface for creating an object, but let subclasses decide which class
 * to instantiate. Factory Method lets a class defer instantiation to subclasses.
 * 
 * The factory pattern is a creational pattern that provides an interface for creating objects in a
 * superclass, but allows subclasses to alter the type of objects that will be created.
 * 
 * Real world example:
 * Consider the case of a hiring manager. It is impossible for one person to interview for each of
 * the positions. Based on the job opening, she has to decide and delegate the interview steps to
 * different people.
 * 
 * In plain words:
 * It provides a way to delegate the instantiation logic to child classes.
 * 
 * Wikipedia says:
 * In class-based programming, the factory method pattern is a creational pattern that uses factory
 * methods to deal with the problem of creating objects without having to specify the exact class of
 * object that will be created. This is done by creating objects by calling a factory method—either
 * specified in an interface and implemented by child classes, or implemented in a base class and
 * optionally overridden by derived classes—rather than by calling a constructor.
 * 
 * Programmatic Example:
 * Let's take an example of a hiring manager to understand the factory pattern. First of all, we have
 * an interview class and some types of interviews. The hiring manager class will decide
 * which interview to conduct based on the job opening.
 * 
 * In this example, the hiring manager is the factory. The factory method is the decideInterview
 * method. The concrete creators are the different types of interviews.
 * 
 * The factory method pattern is used to create an object based on a set of logic. The hiring manager
 * decides which interview to conduct based on the job opening. The factory method pattern is used
 * to create an object based on a set of logic. The hiring manager decides which interview to conduct
 * based on the job opening.
 * 
 * The factory method pattern is used to create an object based on a set of logic. The hiring manager
 * decides which interview to conduct based on the job opening.
 */
// Step 1: Create an interface
interface Interview {
    void takeInterview();
}

// Step 2: Create concrete classes implementing the same interface
class DeveloperInterview implements Interview {
    @Override
    public void takeInterview() {
        System.out.println("Developer Interview");
    }
}

class CommunityExecutiveInterview implements Interview {
    @Override
    public void takeInterview() {
        System.out.println("Community Executive Interview");
    }
}

// Step 3: Create a factory to get the objects of concrete classes
class HiringManager {
    public Interview decideInterview(String interviewType) {
        Interview interview = null;
        if ("developer".equals(interviewType)) {
            interview = new DeveloperInterview();
        } else if ("community executive".equals(interviewType)) {
            interview = new CommunityExecutiveInterview();
        }
        return interview;
    }
}

// Step 4: Use the factory to get the object of concrete classes
public class FactoryPattern {
    public static void main(String[] args) {
        HiringManager hiringManager = new HiringManager();
        Interview developerInterview = hiringManager.decideInterview("developer");
        developerInterview.takeInterview();
        Interview communityExecutiveInterview = hiringManager.decideInterview("community executive");
        communityExecutiveInterview.takeInterview();
    }
}
// Output:
// Developer Interview
// Community Executive Interview

/**
 * Summary:
 * The factory pattern is a creational pattern that provides an interface for creating objects in a
 * superclass, but allows subclasses to alter the type of objects that will be created.
 * 
 * The factory pattern is used to create an object based on a set of logic. The hiring manager
 * decides which interview to conduct based on the job opening.
 * 
 * The factory pattern is used to create an object based on a set of logic. The hiring manager
 * decides which interview to conduct based on the job opening.
 */

