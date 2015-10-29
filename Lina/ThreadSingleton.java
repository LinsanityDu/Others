https://en.wikipedia.org/wiki/Double-checked_locking#Usage_in_Java




public final class Foo implements Serializable
 {    
 	private static final longserialVersionUID = 1L;    
	private static class FooLoader {
	        private static final Foo INSTANCE = new Foo();    
   }    
   private Foo() {        
   		if(FooLoader.INSTANCE != null) {            
   			throw newIllegalStateException("Already instantiated");        
   		}    }    
   public staticFoo getInstance() {       
   	    return FooLoader.INSTANCE;    
   	}   
   	@SuppressWarnings("unused")    
   	private Foo readResolve() 
   	{        return FooLoader.INSTANCE;    }
  }


/*We're going to create a SingleObject class. SingleObject class have its constructor as private and have a static instance of itself.

SingleObject class provides a static method to get its static instance to outside world. SingletonPatternDemo, our demo class will use SingleObject class to get a SingleObject object.*/

public class SingletonObjectDemo {

	private static SingletonObject singletonObject;
	// Note that the constructor is private
	private SingletonObjectDemo() {
		// Optional Code
	}
	public static SingletonObjectDemo getSingletonObject() {
		if (singletonObject == null) {
			singletonObject = new SingletonObjectDemo();
		}
		return singletonObject;
	}
}

/*Step 3: Make the Access method Synchronized to prevent Thread Problems.

public static synchronized SingletonObjectDemo getSingletonObject()

It could happen that the access method may be called twice from 2 different classes at the same time and hence more than one object being created. This could violate the design patter principle. In order to prevent the simultaneous invocation of the getter method by 2 threads or classes simultaneously we add the synchronized keyword to the method declaration

Step 4: Override the Object clone method to prevent cloning

We can still be able to create a copy of the Object by cloning it using the Object’s clone method. This can be done as shown below

SingletonObjectDemo clonedObject = (SingletonObjectDemo) obj.clone();

This again violates the Singleton Design Pattern’s objective. So to deal with this we need to override the Object’s clone method which throws a CloneNotSupportedException exception.

public Object clone() throws CloneNotSupportedException {
throw new CloneNotSupportedException();
}

The below program shows the final Implementation of Singleton Design Pattern in java, by using all the 4 steps mentioned above.*/

class SingletonClass {

	private static SingletonClass singletonObject;
	/** A private Constructor prevents any other class from instantiating. */
	private SingletonClass() {
		//	 Optional Code
	}
	public static synchronized SingletonClass getSingletonObject() {
		if (singletonObject == null) {
			singletonObject = new SingletonClass();
		}
		return singletonObject;
	}
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}

public class SingletonObjectDemo {

	public static void main(String args[]) {
		//		SingletonClass obj = new SingletonClass();  
                //Compilation error not allowed
		SingletonClass obj = SingletonClass.getSingletonObject();
		// Your Business Logic
		System.out.println("Singleton object obtained");
	}
}


// Lazy Initiliation
public class CrunchifySingleton {
 
	private static CrunchifySingleton instance = null;
 
	protected CrunchifySingleton() {
	}
 
	// Lazy Initialization (If required then only)
	public static CrunchifySingleton getInstance() {
		if (instance == null) {
			// Thread Safe. Might be costly operation in some case
			synchronized (CrunchifySingleton.class) {
				if (instance == null) {
					instance = new CrunchifySingleton();
				}
			}
		}
		return instance;
	}
}


//  Whole process


Consider, for example, this code segment in the Java programming language as given by [2] (as well as all other Java code segments):

// Single-threaded version
class Foo {
    private Helper helper;
    public Helper getHelper() {
        if (helper == null) {
            helper = new Helper();
        }
        return helper;
    }

    // other functions and members...
}
The problem is that this does not work when using multiple threads. A lock must be obtained in case two threads call getHelper() simultaneously. Otherwise, either they may both try to create the object at the same time, or one may wind up getting a reference to an incompletely initialized object.

The lock is obtained by expensive synchronizing, as is shown in the following example.

// Correct but possibly expensive multithreaded version
class Foo {
    private Helper helper;
    public synchronized Helper getHelper() {
        if (helper == null) {
            helper = new Helper();
        }
        return helper;
    }

    // other functions and members...
}
However, the first call to getHelper() will create the object and only the few threads trying to access it during that time need to be synchronized; after that all calls just get a reference to the member variable. Since synchronizing a method could in some extreme cases decrease performance by a factor of 100 or higher,[4] the overhead of acquiring and releasing a lock every time this method is called seems unnecessary: once the initialization has been completed, acquiring and releasing the locks would appear unnecessary. Many programmers have attempted to optimize this situation in the following manner:

Check that the variable is initialized (without obtaining the lock). If it is initialized, return it immediately.
Obtain the lock.
Double-check whether the variable has already been initialized: if another thread acquired the lock first, it may have already done the initialization. If so, return the initialized variable.
Otherwise, initialize and return the variable.
// Broken multithreaded version
// "Double-Checked Locking" idiom
class Foo {
    private Helper helper;
    public Helper getHelper() {
        if (helper == null) {
            synchronized(this) {
                if (helper == null) {
                    helper = new Helper();
                }
            }
        }
        return helper;
    }

    // other functions and members...
}


