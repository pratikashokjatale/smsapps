package edu.cjc.sms.app.servicei;

import java.util.List;

import edu.cjc.sms.app.model.Student;

public interface StudentServiceI {

	List<Student> addStudent(Student s);
	List<Student> getAllStudents();
	public List<Student> findAllByBatchNumber(String batchNumber);
	List<Student> deletestudent(int studentId);
	Student updateStu(int id);
	List<Student> feespay(int studentId, float feesPaid);
	List<Student> changebatch(int studentId, String batchNumber);
	public List<Student> paging(int pageNo, int pageSize);

}
