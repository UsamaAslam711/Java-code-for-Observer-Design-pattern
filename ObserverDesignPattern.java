import java.util.ArrayList;
import java.util.List;

// The Subject interface defines the operations for attaching, detaching and notifying observers.
interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}

// The ConcreteSubject class implements the Subject interface. It maintains a list of observers and notifies them when its state changes.
class ConcreteSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

// The Observer interface defines the operation for updating when a subject's state changes.
interface Observer {
    void update();
}

// The ConcreteObserver class implements the Observer interface. It maintains a reference to the subject it is observing and updates itself when the subject's state changes.
class ConcreteObserver implements Observer {
    private int observerState;
    private ConcreteSubject subject;

    public ConcreteObserver(ConcreteSubject subject) {
        this.subject = subject;
        subject.attach(this);
    }

    public void update() {
        observerState = subject.getState();
        System.out.println("Observer state updated to: " + observerState);
    }
}

// The main method demonstrates the use of the Observer pattern.
public class ObserverPatternDemo {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        ConcreteObserver observer1 = new ConcreteObserver(subject);
        ConcreteObserver observer2 = new ConcreteObserver(subject);

        // Change the state of the subject and notify the observers.
        subject.setState(1);
        subject.setState(2);
        subject.setState(3);
    }
}
