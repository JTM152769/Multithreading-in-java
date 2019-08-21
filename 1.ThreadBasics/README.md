## Thread
* Threads consumes CPU in best possible manner, hence enables multi processing. Multi threading reduces idle time of CPU which improves performance of application.
* Thread are light weight process.
* A thread class belongs to java.lang package.
* We can create multiple threads in java, even if we don’t create any Thread, one Thread at least  do exist i.e. main thread.
* Multiple threads run parallely in java.  
* Threads have their own stack.
* Advantage of Thread : Suppose one thread needs 10 minutes to get certain task, 10 threads used at a time could complete that task in 1 minute, because threads can run parallely.

## Thread States/Thread Life Cycle
  Thread states/Thread life cycle is very basic question, before going deep into concepts we must understand Thread life cycle.
    This post contains in depth explanation of thread methods explaining which method puts thread from which state to whichstate. 
    Thread has following States : 
    * New
    * Runnable
    * Running
    * Waiting/blocked/sleeping
    * Terminated (Dead)  


* New  
 * When instance of thread is created using new operator it is in new state, but the start() method has not been invoked on the          thread yet, thread is not eligible to run yet.
 * Thread object is considered alive but thread is not alive yet.

* Runnable :  
When start() method is called on thread it enters runnable state. 
As soon as Thread enters runnable state it is eligible to run, but not running. (Thread scheduler has not scheduled the Thread execution yet, Thread has not entered in run() method yet)
>A thread first enters the runnable state when the start() method is invoked, but a thread can also return to the runnable state after either running or coming back from a
blocked, waiting, or sleeping state. 
>Thread is considered alive in runnable state.
>Thread is in Runnable pool.

Running : Thread scheduler selects thread to go from runnable to running state. In running state Thread starts executing by entering run() method.
>Thread scheduler selects thread from the runnable pool on basis of priority, if priority of two threads is same, threads are scheduled in unpredictable manner. Thread scheduler behaviour is completely unpredictable.
 >When threads are in running state, yield() method can make thread to go in Runnable state.

Waiting/blocked/sleeping : In this state a thread is not eligible to run. 
>Thread is still alive, but currently it’s not eligible to run. In other words.

> How can Thread go from running to waiting state ?
   By calling wait() method thread go from running to waiting state. In waiting state it will wait for other threads to release object monitor/lock. 
> How can Thread return from waiting to runnable state ?
   Once notify() or notifyAll() method is called object monitor/lock becomes available and thread can again return to runnable state.


> How can Thread go from running to sleeping state ?
   By calling sleep() method thread go from running to sleeping state. In sleeping state it will wait for sleep time to get over.
> How can Thread return from sleeping to runnable state ?
   Once specified sleep time is up thread can again return to runnable state.

Suspend() method can be used to put thread in waiting state and resume() method is the only way which could put thread in runnable state.

Thread also may go from running to waiting state if it is waiting for some I/O operation to take place. Once input is available thread may return to running state.


Terminated (Dead) : A thread is considered dead when its run() method completes. 
>Once thread is dead it cannot be started again doing so will throw runtimeException i.e. IllegalThreadStateException.

destroy() method puts thread directly into dead state. 




## Example
```java
package com.amitsa.patterns;

public class SingletonPattern {

    private static SingletonPattern _instance = new SingletonPattern();

    private SingletonPattern() {
        System.out.println("Creating .......");
    }

    public static SingletonPattern getInstance() {
        return _instance;
    }
}

class TestClient{
    public static void main(String[] args){
        SingletonPattern s1=SingletonPattern.getInstance();
        SingletonPattern s2=SingletonPattern.getInstance();

        print("s1",s1);
        print("s2",s2);
    }
    static void print(String name, SingletonPattern object) {
        System.out.println(String.format("object : %s, Hashcode : %d", name , object.hashCode()));
        }
}
```
Output:
>Creating .......
object : s1, Hashcode : 1956725890
object : s2, Hashcode : 1956725890

Process finished with exit code 0

## Explanation
Real world example

> There can only be one ivory tower where the wizards study their magic. The same enchanted ivory tower is always used by the wizards. Ivory tower here is singleton.

In plain words

> Ensures that only one object of a particular class is ever created.

Wikipedia says

> In software engineering, the singleton pattern is a software design pattern that restricts the instantiation of a class to one object. This is useful when exactly one object is needed to coordinate actions across the system.

**Programmatic Example**

Joshua Bloch, Effective Java 2nd Edition p.18

> A single-element enum type is the best way to implement a singleton

```java
public enum EnumIvoryTower {
  INSTANCE;
}
```

Then in order to use

```java
EnumIvoryTower enumIvoryTower1 = EnumIvoryTower.INSTANCE;
EnumIvoryTower enumIvoryTower2 = EnumIvoryTower.INSTANCE;
assertEquals(enumIvoryTower1, enumIvoryTower2); // true
```

## Applicability
Use the Singleton pattern when

* there must be exactly one instance of a class, and it must be accessible to clients from a well-known access point
* when the sole instance should be extensible by subclassing, and clients should be able to use an extended instance without modifying their code

## Typical Use Case

* the logging class
* managing a connection to a database
* file manager

## Real world examples

* [java.lang.Runtime#getRuntime()](http://docs.oracle.com/javase/8/docs/api/java/lang/Runtime.html#getRuntime%28%29)
* [java.awt.Desktop#getDesktop()](http://docs.oracle.com/javase/8/docs/api/java/awt/Desktop.html#getDesktop--)
* [java.lang.System#getSecurityManager()](http://docs.oracle.com/javase/8/docs/api/java/lang/System.html#getSecurityManager--)


## Consequences

* Violates Single Responsibility Principle (SRP) by controlling their own creation and lifecycle.
* Encourages using a global shared instance which prevents an object and resources used by this object from being deallocated.     
* Creates tightly coupled code. The clients of the Singleton become difficult to test.
* Makes it almost impossible to subclass a Singleton.

## Credits

* [Design Patterns: Elements of Reusable Object-Oriented Software](http://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612)
* [Effective Java (2nd Edition)](http://www.amazon.com/Effective-Java-Edition-Joshua-Bloch/dp/0321356683)

