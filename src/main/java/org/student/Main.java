package org.student;

public class Main {
    public static void main(String[] args) {
        int[] studentIdList = {1001, 1002};
        char[][] studentsGrades = {
                {'A', 'A', 'A', 'B'},
                {'A', 'B', ' '}
        };

        try {
            System.out.println("Calculating GPAs...");
            double[] gpaArray = StudentUtil.calculateGPA(studentIdList, studentsGrades);
            for (int i = 0; i < studentIdList.length; i++) {
                System.out.printf("Student ID: %d, GPA: %.4f%n", studentIdList[i], gpaArray[i]);
            }
        } catch (MissingGradeException e) {
            System.err.println("Error: " + e.getMessage() + " (Student ID: " + e.getStudentId() + ")");
        }

        System.out.println("\nFiltering students by GPA range (3.2 - 3.5)...");
        try {
            int[] filteredStudents = StudentUtil.getStudentsByGPA(3.2, 3.5, studentIdList, studentsGrades);
            if (filteredStudents != null) {
                System.out.print("Students in range: ");
                for (int id : filteredStudents) {
                    System.out.print(id + " ");
                }
                System.out.println();
            } else {
                System.out.println("Invalid GPA range.");
            }
        } catch (InvalidDataException e) {
            System.err.println("Data error: " + e.getMessage());
            System.err.println("Root cause: " + e.getCause().getMessage());
        }
    }
}
