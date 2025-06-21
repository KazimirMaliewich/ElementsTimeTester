 # Project Description
 ## ElementsTimeTester

 The task is to create a program that allows testing various types of Java collections in terms of their
 performance. The program should allow for detailed configuration of test parameters via a command-line
 interface (CLI) and present results in the desired format.

  ### Program Configuration (CLI)
 The CLI should allow the user to specify the following parameters:

 1. Type of input data:- Generated integers (Integer)- Generated floating-point numbers (Double)- Objects of class Person (with at least: birth year int and name String)- Objects of class MyColor (with fields r, g, b and sum = r + g + b)- Other custom classes (at least two)

 Data should be created using the Stream API. The user should be able to select the number of generated
 elements: 100, 500, 1000, 10,000, or provide a custom value.

 2. Type of collection:- ArrayList- LinkedList- HashSet- TreeSet
  If the collection requires sorting (e.g. TreeSet), proper implementation of Comparable or Comparator is
 required.

 3. Type of test:- Index-based access- Insertion/removal frequency- Element search (e.g. linear or binary depending on the structure)- Checking element existence in the collection

 4. Data presentation format:- Console output (e.g. display timing metrics)- Save to CSV file
 Test Execution

 Performance measurements should include:
 
 - Time to add elements (addTime)- Time to search (searchTime)- Time to remove (removeTime)
 Tests must be performed via:- Repeated insertion of elements into the collection- Measuring read or search time (if applicable)- Removing specific or repeated elements

- Verifying element presence in the collection

 ### Guidelines and Final Remarks
 The project must:
 - Define all configuration elements (data type, collection, test, presentation) using enums to ease future
 expansion.
 - Organize code so that implementations of interfaces (data generators, test runners, etc.) are reusable and
 extensible.
 - Present test results clearly, e.g., via a TestResult class holding timing metrics, displayed in an easy-to-read
 format.
 - Handle exceptional situations, e.g., unsupported collection types or invalid input sizes - especially for
 collections that require ordering like TreeSet.
 - Consider the differences between lists (indexed access), sets (non-indexed), and sorting nuances to better
 understand each collection's behavior.