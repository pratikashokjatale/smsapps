package edu.cjc.sms.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cjc.sms.app.model.Student;
import edu.cjc.sms.app.repositary.StudentRepo;
import edu.cjc.sms.app.servicei.StudentServiceI;
@Service
public class StudentServiceImpl implements StudentServiceI{

	@Autowired
	StudentRepo sr;
	
	@Override
	public List<Student> addStudent(Student s) {
		  sr.save(s);		
		return sr.findAll();
	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return sr.findAll();
	}

	@Override
	public List<Student> findAllByBatchNumber(String batchNumber) {
		// TODO Auto-generated method stub
		return sr.findAllByBatchNumber(batchNumber);
	}

	@Override
	public List<Student> deletestudent(int studentId) {
		sr.deleteById(studentId);
		List<Student>list=sr.findAll();
		return list ;
	}

	@Override
	public Student updateStu(int id) {
		Optional<Student>list=sr.findById(id);
		if(list.isPresent()) {
			Student p=list.get();
			return p ;
		}else
		return null;
	}

	@Override
	public List<Student> feespay(int studentId, float feesPaid) {
		Optional<Student>list=sr.findById(studentId);
		if(list.isPresent()) {
			Student p=list.get();
			p.setFeesPaid(p.getFeesPaid()+feesPaid);
			sr.save(p);
			return sr.findAll();
		}else 
			return null;
		
	}

	@Override
	public List<Student> changebatch(int studentId, String batchNumber) {
		Optional<Student>list=sr.findById(studentId);
		if(list.isPresent()) {
			Student p=list.get();
			p.setBatchNumber(batchNumber);
			sr.save(p);
			return sr.findAll();
		}else 
			return null;
	}

	
}
