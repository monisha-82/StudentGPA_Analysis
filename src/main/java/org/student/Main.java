package org.student;

import java.util.Arrays;
//import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);

        // Define student IDs
        int[] studentIdList = {1001, 1002, 1003};

        // Define students' grades
        char[][] studentsGrades = {
                {'A', 'A', 'A', 'B'},
                {'A', 'B', 'B'},
                {'B', 'B', 'C', 'C'}
        };

        // Display students and their grades
        System.out.println("Student IDs and their respective grades:");
        for (int i = 0; i < studentIdList.length; i++) {
            System.out.println("Student ID: " + studentIdList[i] + ", Grades: " + Arrays.toString(studentsGrades[i]));
        }

        // Call calculateGPA method
        double[] gpas = StudentUtil.calculateGPA(studentIdList, studentsGrades);

        // Display GPA results
        System.out.println("\nCalculated GPAs:");
        for (int i = 0; i < gpas.length; i++) {
            System.out.printf("Student ID: %d, GPA: %.4f%n", studentIdList[i], gpas[i]);
        }

        // Taking input from user to test getStudentsByGPA method
//        System.out.print("\nEnter lower GPA limit: ");
//        double lower = scanner.nextDouble();
        double lower = 3;

//        System.out.print("Enter upper GPA limit: ");
//        double higher = scanner.nextDouble();
        double higher = 4;

        // Call getStudentsByGPA method
        int[] filteredStudents = StudentUtil.getStudentsByGPA(lower, higher, studentIdList, studentsGrades);

        // Display filtered students
        if (filteredStudents == null) {
            System.out.println("\nInvalid range! Lower limit should be <= Upper limit and non-negative.");
        } else if (filteredStudents.length == 0) {
            System.out.println("\nNo students found in the given GPA range.");
        } else {
            System.out.println("\nStudents in GPA range " + lower + " to " + higher + ": " + Arrays.toString(filteredStudents));
        }

//        scanner.close();
    }
}
