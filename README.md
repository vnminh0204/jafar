# PP-Project_22
The project of University of Twente's Programming Paradigms module consists of the development of a complete compiler for
a Jafar language. The target machine for compilation is a realistic, though non-existent processor called SPROCKELL, for which a hardware-level simulator is provided in HASKELL. The corresponding machine language is called SPRIL


# Team members:
- Vo Nhat Minh
- Andrea Onofei
- Tran Duc Duc

# PP Final Project: Jafar programming language project

This folder contains a project for Jafar programming language, which includes:

- JUnit integration
- ANTLR integration
- Grammar of the JAFAR language
- Code generation of the JAFAR language
- JUNIT Test of the JAFAR language


The rest of this document gives a brief explanation of how to run the project. For more information we refer you to the documentation of the respective tools.

## Prerequisites

Make sure you have installed:

- Maven. Preferably version 3.8 or later.
- A Java version. Preferably 11 or 17.

To test if you have these tools set up properly, run the following two commands in the command line:

- For maven: `mvn --version`
- For java: `javac --version`

If these commands print sensible outputs (e.g. "version so and so"), this project should work fine. Otherwise, you will have to install these tools either manually or using your operating system's package manager.

## Memory extension
You can change the memory in BasicFunctions.hs which is located in all [sample]spril/Sprockell package.
For the testing purpose, we need to extend the local memory from 32 to 50 to test with function recursion and multi-dimensional array features
With the given 32 it still works but for example the fibFunc will only works with input smaller than 5 and multi-dimensional arrays should have less than 10 elements
In order to support six concurrent threads, we also extend the shared memory from 8 to 48 cells. For detailed reasons and design, please visit our Project Report. 


In order to support six concurrent threads, locking and shared variables we also extended the shared memory from 8 to 48 cells. For detailed information, please visit our Project Report.

## Compiling

In a terminal, run:

```
mvn compile
```

This first generates parsers for any grammars in `src/main/antlr4`. Then it compiles code in the `src/main/java` directory.

>Note: The generated files might not be marked as "Generated Sources Root" (take a look at IntelliJ FAQ)

## Set up run file
To set up the file you want to run, you have to navigate to the `main` method of the class `Main`, located in the `src/main/java/finalproject/main`

In the `main` method you will have 3 option to set up the run

**Option 1: Set string "filename":**

You can choose to run any file provided in `src/main/java/finalproject/sample` by setting the "filename" (without.jafar). 

`Example: filename = "fibFunc"`

**Option 2: Set string "jafarContent":**

You can choose to run your own Jafar program by putting the content in "jafarContent".

`Example: jafarContent = "Program main; int i; {{i:=i+3;} print(i);}"`

It will put the code content into `src/main/java/finalproject/sample/main.jafar` and execute it

Note: To run this option you need to set string "filename" as empty

**Option 3: Set run file as parameter:**

It's similar to option 1 but you have set the filename as program arguments 

Note: To run this option you need to set string "filename" as empty

## Running

Before running command make sure the `target/generated-sources/antlr4` is marked as Generated Sources Root (In our case we use IntelliJ FAQ workaround 2)

There are 2 options to run the Main Class

**Option 1: Running manually:**

Navigate to the `main` method of the class `Main`, located in the `src/main/java/finalproject/main` and click on run button to run it manually

**Option 2: In a terminal, run:**

```
mvn exec:java
```
Note: `exec` does not invoke `compile`, so if changes were made to the code, `compile` needs to be invoked before `exec`. These commands can also be combined:

```
mvn compile exec:java
```

`mvn exec:java` is part of the maven-exec plugin. For more information about e.g. passing arguments to the main class, we refer you to the plugin documentation.

## Running result

After running the file, the file will be first converted to SPRIL instructions which are located in `src/main/java/finalproject/samplespril`, then those instructions will be executed by Sprockell with the final result is located in `src/main/java/finalproject/sampleoutput`
The final result also will be printed on the console/terminal

Example: If you choose to run as option 2, the `main.jafar` file will be converted to `main.hs` in `samplespril` and the final result will be in `main_result.txt` in `sampleoutput`
## Tests

All the tests can be run automatically.

There are 2 options to run the tests.

**Option 1: Run a specific test case with "Run" button:**

Navigate to these test files located in the `src/test/java/finalproject`

In here you can choose to run any of concurrency/contextual/semantic or syntax test as you want.

**Option 2: Run all the test  with terminal:**

In a terminal, run:

```
mvn test
```

This will run all test classes in the `src/test/java` directory which have names ending with "Test".

## Intellij Integration

The project can be imported into intellij by opening the `pom.xml` file in intellij. Then on the right there will be a "Maven" pane. If the "Maven" pane is not there, you might have opened the project directory; be sure to open _specifically the file_ with intellij.

Open the "Maven" pane, and open the tree structure there until you find the "Lifecycle" section. Here you can double-click "compile" and "test" to compile and test your code.

To run your main class, open the main class and click on the green arrow besides the main method. Note: you have to run the "compile" target in the maven pane before running the main class.

To run your a specific test, open the test class and click the green arrows beside the test methods to run the individual tests. Note: you have to run the "test" target in the maven pane before running the individual tests.

For more information on intellij's maven integration, we refer you to the intellij documentation.

### IntelliJ FAQ

#### IntelliJ is not recognizing the generated antlr parser classes

This often happens on the initial import of the subproject. It happens because at the time of import the folder containing the generated antlr classes does not exist yet. To fix this, either refresh the project after compiling (workaround 1), or mark the directory manually (workaround 2).

Workaround 1:

1. Execute the `compile` target in the maven pane. Your project should compile without errors.
2. Refresh the maven project by pressing the "Reload All Maven Projects" button in the top left of the Maven pane

Workaround 2:

4. In the IntelliJ "Project" pane, navigate to the `target/generated-sources/antlr4` folder
5. Right click this `antlr4` folder, and click on "Mark Directory as > Generated Sources Root"
