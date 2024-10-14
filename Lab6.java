import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab7FileIO {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Single Scanner for all input
        int choice;
        
        do {
            System.out.println("Menu:");
            System.out.println("1. Create input.txt file with sample data.");
            System.out.println("2. Calculate sum, max, and average from file.");
            System.out.println("3. Enter student records.");
            System.out.println("4. Display student records.");
            System.out.println("0. Exit.");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createInputFile();
                    break;
                case 2:
                    calculateSumMaxAverage(scanner);
                    break;
                case 3:
                    writeStudentRecords(scanner);
                    break;
                case 4:
                    readAndDisplayStudentRecords(scanner);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();  // Close the Scanner at the end
    }

    // Method to create input.txt with sample data
    public static void createInputFile() {
        try {
            PrintStream inputFile = new PrintStream(new File("input.txt"));
            // Write some example numbers to the input file
            inputFile.println(23.5);
            inputFile.println(12.1);
            inputFile.println(45.8);
            inputFile.println(3.2);
            inputFile.close();
            System.out.println("input.txt has been created with sample numbers.");
        } catch (FileNotFoundException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    // Problem 1: Calculate sum, max, and average from input.txt and write to output.txt
    public static void calculateSumMaxAverage(Scanner scanner) {
        Scanner fileScanner = null;
        PrintStream outputFile = null;
        try {
            fileScanner = new Scanner(new File("input.txt"));
            double sum = 0;
            double max = Double.MIN_VALUE;
            int count = 0;

            while (fileScanner.hasNextDouble()) {
                double number = fileScanner.nextDouble();
                sum += number;
                if (number > max) {
                    max = number;
                }
                count++;
            }

            double average = (count > 0) ? sum / count : 0;

            outputFile = new PrintStream(new File("output.txt"));
            outputFile.println("Sum: " + sum);
            outputFile.println("Max: " + max);
            outputFile.println("Average: " + average);

            System.out.println("Results written to output.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } finally {
            if (fileScanner != null) fileScanner.close();
            if (outputFile != null) outputFile.close();
        }
    }

    // Problem 2: Write student records to students.dat
    public static void writeStudentRecords(Scanner scanner) {
        PrintStream studentFile = null;
        try {
            studentFile = new PrintStream(new File("students.dat"));
            String another = "y";

            while (another.equalsIgnoreCase("y")) {
                System.out.print("Enter first name: ");
                String firstName = scanner.nextLine();
                System.out.print("Enter last name: ");
                String lastName = scanner.nextLine();
                System.out.print("Enter letter grade: ");
                String grade = scanner.nextLine();
                System.out.print("Enter year: ");
                int year = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                studentFile.println(firstName + " " + lastName + " " + grade + " " + year);

                System.out.print("Do you want to add another record? (y/n): ");
                another = scanner.nextLine();
            }

            System.out.println("Student records written to students.dat");
        } catch (FileNotFoundException e) {
            System.out.println("File error: " + e.getMessage());
        } finally {
            if (studentFile != null) studentFile.close();
        }
    }

    // Problem 3: Read and display student records from students.dat
    public static void readAndDisplayStudentRecords(Scanner scanner) {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File("students.dat"));
            ArrayList<String> firstNames = new ArrayList<>();
            ArrayList<String> lastNames = new ArrayList<>();
            ArrayList<String> grades = new ArrayList<>();
            ArrayList<Integer> years = new ArrayList<>();

            while (fileScanner.hasNext()) {
                firstNames.add(fileScanner.next());
                lastNames.add(fileScanner.next());
                grades.add(fileScanner.next());
                years.add(fileScanner.nextInt());
            }

            fileScanner.close();

            int choice;

            do {
                System.out.println("Student Records:");
                for (int i = 0; i < firstNames.size(); i++) {
                    System.out.println((i + 1) + ". " + firstNames.get(i) + " " + lastNames.get(i));
                }
                System.out.println("0. Go back to main menu.");
                System.out.print("Enter the number to view details: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice > 0 && choice <= firstNames.size()) {
                    int index = choice - 1;
                    System.out.println("Name: " + firstNames.get(index) + " " + lastNames.get(index));
                    System.out.println("Grade: " + grades.get(index));
                    System.out.println("Year: " + years.get(index));
                } else if (choice != 0) {
                    System.out.println("Invalid choice. Try again.");
                }
            } while (choice != 0);

        } catch (FileNotFoundException e) {
            System.out.println("File error: " + e.getMessage());
        } finally {
            if (fileScanner != null) fileScanner.close();
        }
    }
}
