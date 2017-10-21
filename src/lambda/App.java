package lambda;
/*
 * Lambda expressions are a way of passing a block of code to a method.
 * Lambda expressions are objects and a door to functional programming.
 * Generally used for passing code to a thread or in swing passing a code to a button.
 * 
 * NOTE:The only succinct way to say a method accepts a block of code is through interfaces  
 */


// An interface with only single abstract method is called a Functional Interface in java 8
// other examples include Comparable, Cloneable, Runnable.
@FunctionalInterface
interface Executable {
    void execute();
    // compile time error- not a functional interface
    //void Test();
}

// Functional Interface can have as many default methods as it wants
@FunctionalInterface
interface ReturnExecutable {
    int execute();
    default void test1() {
    	System.out.println("Default Method - test1()");
    }
    default void test2() {
       	System.out.println("Default Method - test2()");
    }
}

@FunctionalInterface
interface ExecutableParam {
    int execute(int param);
}


@FunctionalInterface
interface ExecutableMultiParams {
    int execute(int param1, int param2);
}

class Runner {
	
    public void run(Executable e) {
    	System.out.println("Executable code block");
        e.execute();
    }
    
    public void run(ReturnExecutable e) {
      	System.out.println("ReturnExecutable code block");
        int value = e.execute();
        System.out.println("Return value: " + value);
    }
    
    public void run(ExecutableParam e) {
    	System.out.println("ExecutableParam code block");
    	int value = e.execute(7);
        System.out.println("Return value: " + value);
    }
    
    public void run(ExecutableMultiParams e) {
     	System.out.println("ExecutableMultiParams code block");
    	int value = e.execute(7, 8);
        System.out.println("Return value: " + value);
    }
}

public class App {
    public static void main(String[] args) {
            
    	Runner runner = new Runner();
        runner.run(new Executable() {
        	@Override
        	public void execute() {
            	System.out.println("Hello there.");
            }
        });
        
        // lambda equivalent
    	runner.run(() -> System.out.println("Hello there."));
    	
    	runner.run(() -> {
    		System.out.println("Code passed in a lambda expression.");
    		System.out.println("Hello there.");
    	});
    	
    	runner.run(() -> {
    		System.out.println("Code passed in a lambda expression.");
    		System.out.println("Hello there.");
    		return 8;
    	});
    	
    	runner.run(() -> {
    		return 8;
    	});
    	
    	// returning the value of 8
    	runner.run(() -> 8);
    	
    	int value = 9;
    	runner.run(() -> calculate(value));
    	
    	runner.run(new ExecutableParam() {
			@Override
			public int execute(int param) {
				System.out.println("Passed param: " + param);
				return param + param;
			}
    		
    	});
    	// lambda equivalent
    	runner.run((int param) -> {
    		System.out.println("Passed param: " + param);
    		return param + param;
    	});
    	// or
    	runner.run((int param) -> param + param);
    	// or
    	runner.run((param) -> param + param);
    	// or
    	runner.run(param -> param + param);
        
    	runner.run((a, b) -> a + b);
    	
    	int c = 100;
    	// compilation error - local variable must be final or effectively final
    	// c = 20;
    	runner.run((a, b) -> a + c);
    	
    }

	private static int calculate(int value) {
		return value++;
	}
}