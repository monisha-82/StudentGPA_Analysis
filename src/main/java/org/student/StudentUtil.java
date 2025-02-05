package org.student;

import java.util.ArrayList;
import java.util.List;

public class StudentUtil {

    // Convert grades to points
    private static int getGradePoint(char grade) throws MissingGradeException {
        return switch (grade) {
            case 'A' -> 4;
            case 'B' -> 3;
            case 'C' -> 2;
            case ' ' -> throw new MissingGradeException(-1); // Placeholder studentId, handled in loop
            default -> throw new IllegalArgumentException("Invalid grade: " + grade);
        };
    }

    // Calculate GPA with validation
    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) throws MissingGradeException {
        // Task 1: Validate array lengths
        if (studentIdList.length != studentsGrades.length) {
            throw new IllegalArgumentException("studentIdList & studentsGrades are out-of-sync. " +
                    "studentIdList.length: " + studentIdList.length + ", studentsGrades.length: " + studentsGrades.length);
        }

        double[] gpaArray = new double[studentIdList.length];

        for (int i = 0; i < studentIdList.length; i++) {
            char[] grades = studentsGrades[i];

            double totalPoints = 0;
            for (char grade : grades) {
                if (grade == ' ') {
                    throw new MissingGradeException(studentIdList[i]); // Task 2: Throw checked exception
                }
                totalPoints += getGradePoint(grade);
            }

            gpaArray[i] = totalPoints / grades.length;
        }

        return gpaArray;
    }

    // Get students by GPA range, with exception handling
    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades) {
        // Validate input range
        if (lower < 0 || higher < 0 || lower > higher) {
            return null;
        }

        try {
            double[] gpaArray = calculateGPA(studentIdList, studentsGrades);
            List<Integer> filteredStudents = new ArrayList<>();

            for (int i = 0; i < gpaArray.length; i++) {
                if (gpaArray[i] >= lower && gpaArray[i] <= higher) {
                    filteredStudents.add(studentIdList[i]);
                }
            }

            return filteredStudents.stream().mapToInt(Integer::intValue).toArray();
        } catch (MissingGradeException e) {
            // Task 3: Exception chaining - wrap MissingGradeException in InvalidDataException
            throw new InvalidDataException("Failed to compute GPA due to missing grades.", e);
        }
    }
}