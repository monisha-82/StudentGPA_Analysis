package org.student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentUtilTest {

    @Test
    void testCalculateGPA() {
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
}
