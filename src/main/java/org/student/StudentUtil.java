package org.student;

import java.util.ArrayList;
import java.util.List;

public class StudentUtil {

    // Helper method to convert grades to points
    private static int getGradePoint(char grade) {
        return switch (grade) {
            case 'A' -> 4;
            case 'B' -> 3;
            case 'C' -> 2;
            case 'D' -> 1;
            default -> throw new IllegalArgumentException("Invalid grade: " + grade);
        };
    }

    // Method to calculate GPA for each student
    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) {
        double[] gpaArray = new double[studentIdList.length];

        for (int i = 0; i < studentIdList.length; i++) {
            char[] grades = studentsGrades[i];
            double totalPoints = 0;

            for (char grade : grades) {
                totalPoints += getGradePoint(grade);
            }

            gpaArray[i] = totalPoints / grades.length;
        }

        return gpaArray;
    }

    // Method to get students whose GPA is within the given range
    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades) {
        // Parameter validation
        if (lower < 0 || higher < 0 || lower > higher) {
            return null;
        }

        double[] gpaArray = calculateGPA(studentIdList, studentsGrades);
        List<Integer> filteredStudents = new ArrayList<>();

        for (int i = 0; i < gpaArray.length; i++) {
            if (gpaArray[i] >= lower && gpaArray[i] <= higher) {
                filteredStudents.add(studentIdList[i]);
            }
        }

        // Convert List to int array
        return filteredStudents.stream().mapToInt(Integer::intValue).toArray();
    }
}
