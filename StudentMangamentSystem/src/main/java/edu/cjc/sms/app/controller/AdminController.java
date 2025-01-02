package edu.cjc.sms.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cjc.sms.app.model.Student;
import edu.cjc.sms.app.servicei.StudentServiceI;

@Controller
public class AdminController {
	@Autowired
	StudentServiceI ssi;

	@RequestMapping("/")
	public String preLogin() {
		return "login";
	}
	
	@RequestMapping("/login")
	public String onLogin(@RequestParam("username") String u,@RequestParam("password") String p,Model m) {
		if(u.equals("ADMIN")  && p.equals("ADMIN"))
		{
			m.addAttribute("data",ssi.getAllStudents());
			return "adminscreen";
		}else {
			return "login";
		}
	}
	
	@RequestMapping("/enroll_student")
	public String addStudent(@ModelAttribute Student s,Model m) {
		  List<Student> list=ssi.addStudent(s);
		  m.addAttribute("data", list);
		return "adminscreen";
	}
	
	@RequestMapping("/search")
	public String searchstudent(@RequestParam("batchNumber")String batchNumber ,Model m) {
		List<Student>result=ssi.findAllByBatchNumber(batchNumber);
		if(result.size()>0) {
			m.addAttribute("data", result);
		}else {
			m.addAttribute("data",ssi.getAllStudents());
			m.addAttribute("message", "no record found " +batchNumber);
		}
		return "adminscreen";
	}
	
	
	@RequestMapping("/delete")
	public String deletestudent(@RequestParam("studentId")int studentId ,Model m) {
		List<Student>list=ssi.deletestudent(studentId);
		m.addAttribute("data", list);
		return "adminscreen";
	}
	
	
	@RequestMapping("/updatefess")
	public String updateFess(@RequestParam("studentId")int id,Model m) {
		Student s =ssi.updateStu(id);
		m.addAttribute("st", s);
		return "fess";
	}
	
	@RequestMapping("/payfees")
	public String payfess(@RequestParam("studentId")int studentId, @RequestParam("feesPaid")float feesPaid,Model m) {
		List<Student>list=ssi.feespay(studentId,feesPaid);
		m.addAttribute("data", list);
		return "adminscreen";
	}
	
	@RequestMapping("/switch")
	public String shiftbatch(@RequestParam("studentId")int id,Model m) {
		Student s =ssi.updateStu(id);
		m.addAttribute("st", s);
		return "shift";
	}
	
	@RequestMapping("/changebatch")
	public String changebatch(@RequestParam("studentId")int studentId, @RequestParam("batchNumber")String batchNumber,Model m) {
		List<Student>list=ssi.changebatch(studentId,batchNumber);
		m.addAttribute("data", list);
		return "adminscreen";
	}
	@RequestMapping("/paging")
	public String paging(@RequestParam("pageNo") int pageNo,Model m) {
		

		List<Student> list=ssi.paging(pageNo,2);
		m.addAttribute("data",list);
		return "adminscreen";
	}
	
	
}
