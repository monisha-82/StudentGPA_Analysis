package org.student;

public class MissingGradeException extends Exception {
    private final int studentId;

    public MissingGradeException(int studentId) {
        super("Missing grade detected for student ID: " + studentId);
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }
}