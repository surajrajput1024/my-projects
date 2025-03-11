/**
 * ObserverDesignPattern: Observer Design Pattern is a behavioral design pattern. It defines a one-to-many dependency
 * between objects so that when one object changes state, all its dependents are notified and updated automatically.
 * 
 * In Observer Design Pattern, an object (subject) maintains a list of its dependents (observers) and notifies them of any
 * state changes, usually by calling one of their methods.
 * 
 * There are two types of objects in Observer Design Pattern: one is observer and observable. Observer is the object that
 * wants to be notified when there is a change in the state of another object. Observable is the object that is being
 * observed.
 * 
 * Interview Question: Suppose you're going to any ecommerce website and you're looking for a product. The article you're
 * searching for is not available right now. But you want to get notified when the article is available. How will you
 * implement "Notify me" feature in the ecommerce website?
 * 
 * Real world example:
 * 1. Stock market: Stock market is a real world example of Observer Design Pattern. In stock market, there are many
 *    investors who are interested in buying and selling stocks. When the price of a stock changes, all the investors
 *    are notified of the change in the price of the stock.
 * 
 * 2. Weather station: Weather station is another real world example of Observer Design Pattern. In weather station, there
 *    are many weather stations that are interested in knowing the weather conditions. When the weather conditions change,
 *   all the weather stations are notified of the change in the weather conditions.
 * 
 * 3. Social media: Social media is another real world example of Observer Design Pattern. In social media, there are many
 *    users who are interested in knowing the updates of their friends. When a user updates his status, all his friends are
 *    notified of the change in the status of the user. For example: Linkedin's "Who viewed your profile" feature.
 * 
 * Benefits of Observer Design Pattern:
 * 1. It allows us to define a one-to-many dependency between objects so that when one object changes state, all its
 *    dependents are notified and updated automatically.
 * 
 * How to implement Observer Design Pattern:
 * 1. Create an interface called Observer with a method called update().
 * 2. Create a class called Subject with a list of observers and methods to add and remove observers.
 * 3. Create a class called ConcreteObserver that implements the Observer interface.
 * 4. Create a class called ConcreteSubject that extends the Subject class.
 * 5. In the ConcreteSubject class, override the methods to add and remove observers and notify observers of any state
 *   changes.
 * 6. In the ConcreteObserver class, implement the update() method to update the state of the observer when it is notified
 *  of any state changes.
 * 7. In the main class, create an object of the ConcreteSubject class and add observers to the subject.
 * 8. When the state of the subject changes, notify all the observers of the change in the state of the subject.
 * 
 */

 1. "Notify me" feature in ecommerce website:
 
    interface Observer {
        void update();
    }

    class Product implements Observer {
        private String name;
        private boolean available;

        public Product(String name, boolean available) {
            this.name = name;
            this.available = available;
        }

        public void update() {
            if(available) {
                System.out.println(name + " is available now.");
            } else {
                System.out.println(name + " is not available right now.");
            }
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }
    }

    class EcommerceWebsite {
        private List<Observer> observers = new ArrayList<>();

        public void addObserver(Observer observer) {
            observers.add(observer);
        }

        public void removeObserver(Observer observer) {
            observers.remove(observer);
        }

        public void notifyObservers() {
            for(Observer observer: observers) {
                observer.update();
            }
        }
    }

    class Main {
        public static void main(String[] args) {
            Product product = new Product("Laptop", false);
            EcommerceWebsite ecommerceWebsite = new EcommerceWebsite();
            ecommerceWebsite.addObserver(product);

            // Product is available now
            product.setAvailable(true);
            ecommerceWebsite.notifyObservers();
        }
    }

2. Stock market example:

    interface Observer {
        void update();
    }

    class Stock implements Observer {
        private String name;
        private double price;
        private boolean available;

        public Stock(String name, double price, boolean available) {
            this.name = name;
            this.price = price;
            this.available = available;
        }

        public void update() {
            if(available) {
                System.out.println(name + " price is " + price);
            } else {
                System.out.println(name + " is not available right now.");
            }
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }
    }

    class StockMarket {
        private List<Observer> observers = new ArrayList<>();

        public void addObserver(Observer observer) {
            observers.add(observer);
        }

        public void removeObserver(Observer observer) {
            observers.remove(observer);
        }

        public void notifyObservers() {
            for(Observer observer: observers) {
                observer.update();
            }
        }
    }

    class Main {
        public static void main(String[] args) {
            Stock stock = new Stock("Apple", 150.0, false);
            StockMarket stockMarket = new StockMarket();
            stockMarket.addObserver(stock);

            // Stock price is changed
            stock.setPrice(160.0);
            stock.setAvailable(true);
            stockMarket.notifyObservers();
        }
    }

    



