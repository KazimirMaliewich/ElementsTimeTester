import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.*;


public class S30436Project01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input data type:");
        System.out.println("1. INTEGER (Liczby całkowite)");
        System.out.println("2. DOUBLE (Liczby zmiennoprzecinkowe)");
        System.out.println("3. PERSON (Obiekty Person)");
        System.out.println("4. MYCOLOR (Obiekty MyColor)");
        System.out.println("5. ANIMAL (Obiekty Animal)");
        System.out.println("6. FOOD (Obiekty Food)");
        System.out.println("7. CAR (Obiekty Car)");

        System.out.print("Your choice (1-7): ");
        int choiceData = sc.nextInt();

        DataType dataType = null;
        switch(choiceData) {
            case 1:
                dataType = DataType.INTEGER;
                break;
            case 2:
                dataType = DataType.DOUBLE;
                break;
            case 3:
                dataType = DataType.PERSON;
                break;
            case 4:
                dataType = DataType.MYCOLOR;
                break;
            case 5:
                dataType = DataType.ANIMAL;
                break;
            case 6:
                dataType = DataType.FOOD;
                break;
            case 7:
                dataType = DataType.CAR;
                break;
            default:
                System.out.println("Incorrect input!");
                System.exit(1);
        }

        System.out.println("Your choice: " + dataType);

        System.out.println("Choose Collection Type:");
        System.out.println("1. ARRAYLIST");
        System.out.println("2. LINKEDLIST");
        System.out.println("3. HASHSET");
        System.out.println("4. TREESET");

        //Creating a variable to select a collection
        CollectionType colType = null;
        //Collection type variable
        int choiceCollection = sc.nextInt();

        switch (choiceCollection){
            case 1:
                colType = CollectionType.ARRAYLIST;
                break;
            case 2 :
                colType = CollectionType.LINKEDLIST;
                break;
            case 3 :
                colType = CollectionType.HASHSET;
                break;
            case 4 :
                colType = CollectionType.TREESET;
                break;
            default :
                System.out.println("There is no such collection");
                System.exit(1);
        }
        System.out.println("Your choice: " + colType);

        //Creating a variable of the screen output type
        OutputType output = null;

        System.out.println("Choose output type:");
        System.out.println("1. Console");
        System.out.println("2. CSV");
        //аselection of output type
        int choiceOutput = sc.nextInt();

        switch (choiceOutput){
            case 1:
                output = OutputType.CONSOLE;
                break;
            case 2:
                output = OutputType.CSV;
                break;
            default:
                System.out.println("There is no such output type");
                System.exit(1);
        }

        System.out.println("Your choice: " + output);

        //selection of testing type
        Tests testType = null;

        System.out.println("Choose test type:");
        System.out.println("1. ADD");
        System.out.println("2. SEARCH");
        System.out.println("3. REMOVE");
        System.out.println("4. ALL_OPERATIONS");
        int choiceTest = sc.nextInt();

        switch (choiceTest){
            case 1:
                testType = Tests.ADD;
                break;
            case 2 :
                testType = Tests.SEARCH;
                break;
            case 3 :
                testType = Tests.REMOVE;
                break;
            case 4 :
                testType = Tests.ALL_OPERATIONS;
                break;
            default :
                System.out.println("There is no such test type");
                System.exit(1);
        }
        System.out.println("Your choice: " + testType);


        System.out.println("Choose how much elements:");
        System.out.println("1. HUNDRED");
        System.out.println("2. FIVE_HUNDRED");
        System.out.println("3. THOUSAND");
        System.out.println("4. TEN_THOUSANDS");
        System.out.println("5. Your custom value");

        //Variable for entering your own number of elements
        int elementsCount;
        //variable for choosing elements count
        Operations operationsElements = null;

        int choice = sc.nextInt();
        //switch for default values
        if (choice >= 1 && choice <= 4) {
            switch (choice){
                case 1: operationsElements = Operations.HUNDRED; break;
                case 2: operationsElements = Operations.FIVE_HUNDRED; break;
                case 3: operationsElements = Operations.THOUSAND; break;
                case 4: operationsElements = Operations.TEN_THOUSANDS; break;
            }
            //variable for getting value from 5 choices
            elementsCount = operationsElements.getValue();
            //if choosing to input your own value
        } else if (choice == 5) {
            System.out.print("Enter your own value: ");
            //Scanner for input values
            elementsCount = sc.nextInt();

            //getting custom user's value
            operationsElements = Operations.fromValue(elementsCount);
            //If input is same as default values
            if (operationsElements != null) {
                System.out.println("matches with a default option: " + operationsElements);
                //if not
            } else {
                System.out.println("user input: " + elementsCount);
            }
            //if inputing wrong type of value
        } else {
            System.out.println("incorrect input");
            System.exit(1);
            return;
        }

        //generating variable which gets unknown type of value
        DataGenerator<?> generator = switch(dataType){
            case INTEGER -> new IntegerGenerator();
            case DOUBLE -> new DoubleGenerator();
            case PERSON -> new PersonGenerator();
            case MYCOLOR -> new ColorGenerator();
            case ANIMAL -> new AnimalGenerator();
            case FOOD -> new FoodGenerator();
            case CAR -> new CarGenerator();
        };
        //creating a list variable that implements the generate method and accepts an unknown data type
        List<?> data = generator.generate(elementsCount);
        //Creating type of object collection which gets of enum collections
        Collection<Object> collection = switch (colType){
            case ARRAYLIST -> new ArrayList<>();
            case LINKEDLIST -> new LinkedList();
            case HASHSET -> new HashSet();
            case TREESET -> new TreeSet(Comparator.comparing(Object::hashCode));
        };
        //Implements interface tester which gets collection type, list type and output
        CollectionTester<Object> tester = new GenericTester<>();
        //Creating variable to output result on screen
        TestResult result = tester.test(collection, (List<Object>) data, testType);
        //result Presenter for result presentation classes
        //depending on the user input, the variable will contain Console Present or CSV Presenter
        ResultPresenter presenter = switch (output){
            case CONSOLE -> new ConsolePresenter();
            case CSV ->  new CSVPresenter();
        };
        //uses presenter method to display result
        presenter.present(Collections.singletonList(result));

    }
}

enum DataType{
    INTEGER, DOUBLE, PERSON, MYCOLOR, ANIMAL, FOOD, CAR;
}

enum CollectionType{
    ARRAYLIST, LINKEDLIST, HASHSET, TREESET;
}

enum OutputType {
    CONSOLE, CSV;
}

enum Tests {
    ADD, SEARCH, REMOVE, ALL_OPERATIONS;
}

enum Operations {
    HUNDRED(100),
    FIVE_HUNDRED(500),
    THOUSAND(1000),
    TEN_THOUSANDS(10000);

    private final int value;

    Operations(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    //checking method for two type of enum
    public static Operations fromValue(int val) {
        for (Operations op : Operations.values()) {
            if (op.getValue() == val) {
                return op;
            }
        }
        return null;
    }
}


interface DataGenerator<T>{
    List<T> generate(int count);
}

interface CollectionTester<T>{
    TestResult test(Collection<T> collection, List<T> data, Tests testType);
}

interface ResultPresenter{
    void present(List<TestResult> results);
}

class MyColor {
    private int r;
    private int g;
    private int b;
    private int sum;

    public MyColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        sum = r + g + b;
    }
}

class Person {
    private String name;
    private int birthYear;

    public Person(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }
}

class Animal {
    private String name;
    private String animalType;
    private int age;

    public Animal(String name, String animalType, int age) {
        this.name = name;
        this.animalType = animalType;
        this.age = age;
    }
}

class Food {
    private String name;
    private String foodType;

    public Food(String name, String foodType) {
        this.name = name;
        this.foodType = foodType;
    }
}

class Car {
    private String model;
    private String mark;
    private String productionCountry;
    private int productionYear;
    private int price;
    private MyColor color;

    public Car(String model, String mark, String productionCountry, int productionYear, int price, MyColor color) {
        this.model = model;
        this.mark = mark;
        this.productionCountry = productionCountry;
        this.productionYear = productionYear;
        this.price = price;
        this.color = color;
    }
}
//result output class
class TestResult {
    String collectionType;
    String dataType;
    String operationType;
    long addTime, searchTime, removeTime, time;

    //constructor for output all types of time checks
    public TestResult(String collectionType, String dataType, long addTime, long searchTime, long removeTime) {
        this.collectionType = collectionType;
        this.dataType = dataType;
        this.addTime = addTime;
        this.searchTime = searchTime;
        this.removeTime = removeTime;
        this.operationType = "ALL_OPERATIONS";
    }

    //constructor for output one type of time checks
    public TestResult(String collectionType, String dataType, String operationType, long time) {
        this.collectionType = collectionType;
        this.dataType = dataType;
        this.operationType = operationType;
        this.time = time;

    }

    //result output
    @Override
    public String toString() {
        if (operationType.equals("ALL_OPERATIONS")) {
            return String.format("[%s | %s ] add: %d ns, search: %d ns, remove: %d ns", dataType, collectionType, addTime, searchTime, removeTime);
        } else {
            return String.format("[%s | %s ] type operation: %s, time: %d ns", dataType, collectionType, operationType, time);
        }
    }
}

//class for calculating time using Integer data type
class IntegerGenerator implements DataGenerator<Integer>{
    public List<Integer> generate(int count){
        return Stream.generate(() -> new Random().nextInt(1000)).limit(count).collect(Collectors.toList());
    }
}

//class for calculating time using Double data type
class DoubleGenerator implements DataGenerator<Double>{
    public List<Double> generate(int count){
        return Stream.generate(() -> new Random().nextDouble() * 1000).limit(count).collect(Collectors.toList());
    }
}

//class for calculating time using Person data type
class PersonGenerator implements DataGenerator<Person>{
    public List<Person> generate(int count){
        return Stream.generate(() -> new Person("Name" + new Random().nextInt(1000),
                1980 + new Random().nextInt(40))).limit(count).collect(Collectors.toList());
    }
}

//class for calculating time using Color data type
class ColorGenerator implements DataGenerator<MyColor>{
    public List<MyColor> generate(int count){
        return Stream.generate(() -> new MyColor(new Random().nextInt(256),
                new Random().nextInt(256), new Random().nextInt(256))).limit(count).collect(Collectors.toList());
    }
}

//class for calculating time using Animal data type
class AnimalGenerator implements DataGenerator<Animal>{
    public List<Animal> generate(int count){
        return Stream.generate(() -> new Animal("Animal" + new Random().nextInt(1000),
                "animalType" + new Random().nextInt(1000), new Random().nextInt(20))).limit(count).collect(Collectors.toList());
    }
}

//class for calculating time using Food data type
class FoodGenerator implements DataGenerator<Food>{
    public List<Food> generate(int count){
        return Stream.generate(() -> new Food("FoodName" + new Random().nextInt(1000),
                "FoodType" + new Random().nextInt(1000))).limit(count).collect(Collectors.toList());
    }
}

//class for calculating time using Car data type
class CarGenerator implements DataGenerator<Car>{
    public  List<Car> generate(int count){
        return Stream.generate(() -> new Car("Model" + new Random().nextInt(1000),
                "Mark" + new Random().nextInt(1000),
                "Production Country" + new Random().nextInt(1000),
                1980 + new Random().nextInt(45),
                10000 + new Random().nextInt(100000),
                new MyColor(new Random().nextInt(256),
                        new Random().nextInt(256),
                        new Random().nextInt(256)))).limit(count).collect(Collectors.toList());
    }
}

class GenericTester<T> implements CollectionTester<T> {
    @Override
    public TestResult test(Collection<T> collection, List<T> data, Tests testType) {
        long start, end;
        switch (testType) {
            case ADD -> {
                start = System.nanoTime();
                collection.addAll(data);
                end = System.nanoTime();
                System.out.println(end - start);
                return new TestResult(collection.getClass().getSimpleName(),
                        data.get(0).getClass().getSimpleName(), "ADD", end - start);
            }
            case REMOVE -> {
                start = System.nanoTime();
                collection.removeAll(data);
                end = System.nanoTime();
                System.out.println(end - start);
                return new TestResult(collection.getClass().getSimpleName(),
                        data.get(0).getClass().getSimpleName(), "REMOVE", end - start);
            }
            case SEARCH -> {
                start = System.nanoTime();
                for (T t : data) {
                    collection.contains(t);
                }
                end = System.nanoTime();
                System.out.println(end - start);
                return new TestResult(collection.getClass().getSimpleName(),
                        data.get(0).getClass().getSimpleName(), "SEARCH", end - start);
            }
            case ALL_OPERATIONS -> {
                start = System.nanoTime();
                collection.addAll(data);
                end = System.nanoTime();
                long addTime = end - start;
                start = System.nanoTime();
                collection.removeAll(data);
                end = System.nanoTime();
                long removeTime = end - start;
                start = System.nanoTime();
                for (T t : data) {
                    collection.contains(t);
                }
                end = System.nanoTime();
                long searchTime = end - start;
                return new TestResult(collection.getClass().getSimpleName(),
                        data.get(0).getClass().getSimpleName(), addTime, removeTime, searchTime);

            }
        }
        return null;
    }
}

//result output in terminal
class ConsolePresenter implements ResultPresenter{
    @Override
    public void present(List<TestResult> results){
        for (TestResult result : results){
            System.out.println(result);
        }
    }
}

//result output in CSV file
class CSVPresenter implements ResultPresenter{
    @Override
    public void present(List<TestResult> results){
        try(FileWriter writer = new FileWriter("Results.csv")){
            writer.write("collectionType, dataType, operationType, time\n");
            System.out.println("collectionType, dataType, operationType, time, add time, remove time, search time");
            for (TestResult r : results){
                if (r.time == 0){
                    System.out.printf("%s, %s, %s, %d, %d, %d%n", r.collectionType, r.dataType, r.operationType, r.addTime, r.removeTime, r.searchTime);
                    writer.write(String.format("%s, %s, %s, %d, %d, %d%n", r.collectionType, r.dataType, r.operationType, r.addTime, r.removeTime, r.searchTime));
                }else {
                    System.out.printf("%s, %s, %s, %d%n", r.collectionType, r.dataType, r.operationType, r.time);
                    writer.write(String.format("%s, %s, %s, %d%n", r.collectionType, r.dataType, r.operationType, r.time));

                }
                }
            System.out.println("CSV file created succesfull");
        } catch (IOException e) {
            System.out.println("ERROR while writing CSV file" + e.getMessage());
        }
    }
}

