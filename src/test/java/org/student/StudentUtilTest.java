package org.student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentUtilTest {

    @Test
    void testCalculateGPA() throws MissingGradeException {
        int[] studentIds = {1001, 1002};
        char[][] grades = { {'A', 'A', 'A', 'B'}, {'A', 'B', 'B'} };

        double[] expectedGPA = {3.75, 3.3333};
        double[] actualGPA = StudentUtil.calculateGPA(studentIds, grades);

        assertEquals(expectedGPA[0], actualGPA[0], 0.001);
        assertEquals(expectedGPA[1], actualGPA[1], 0.001);
    }

    @Test
    void testGetStudentsByGPA() {
        int[] studentIds = {1001, 1002};
        char[][] grades = { {'A', 'A', 'A', 'B'}, {'A', 'B', 'B'} };

        int[] expectedStudents = {1002};
        int[] actualStudents = StudentUtil.getStudentsByGPA(3.2, 3.5, studentIds, grades);

        assertArrayEquals(expectedStudents, actualStudents);
    }

    @Test
    void testInvalidGPARange() {
        int[] studentIds = {1001, 1002};
        char[][] grades = { {'A', 'A', 'A', 'B'}, {'A', 'B', 'B'} };

        assertNull(StudentUtil.getStudentsByGPA(-1, 3.5, studentIds, grades));
        assertNull(StudentUtil.getStudentsByGPA(3.6, 3.5, studentIds, grades));
    }

    @Test
    void testCalculateGPA_ThrowsIllegalArgumentException() {
        int[] studentIds = {1001, 1002};
        char[][] grades = { {'A', 'A', 'A', 'B'} }; // Mismatch in array sizes

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                StudentUtil.calculateGPA(studentIds, grades)
        );

        String expectedMessage = "studentIdList & studentsGrades are out-of-sync";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void testCalculateGPA_ThrowsMissingGradeException() {
        int[] studentIds = {1001, 1002};
        char[][] grades = { {'A', 'A', 'A', 'B'}, {'A', 'B', ' '} }; // One missing grade

        MissingGradeException exception = assertThrows(MissingGradeException.class, () ->
                StudentUtil.calculateGPA(studentIds, grades)
        );

        assertEquals(1002, exception.getStudentId());
    }

    @Test
    void testGetStudentsByGPA_ThrowsInvalidDataException() {
        int[] studentIds = {1001, 1002};
        char[][] grades = { {'A', 'A', 'A', 'B'}, {'A', 'B', ' '} }; // One missing grade

        Exception exception = assertThrows(InvalidDataException.class, () ->
                StudentUtil.getStudentsByGPA(3.0, 4.0, studentIds, grades)
        );

        assertTrue(exception.getMessage().contains("Failed to compute GPA due to missing grades."));
        assertNotNull(exception.getCause());
        assertTrue(exception.getCause() instanceof MissingGradeException);
    }
}