package com.mvs.leetcode_problems;

import java.util.List;
import java.util.stream.Collectors;

class Student {
	int id;
	Student(int id){
		this.id= id;
	}
	
	int getId(){
		return id;
	}
}
class School {
	List<Student> students;
	School(List<Student> students){
		this.students = students;
	}
}

public class Sandbox {
	public static void main(String[] args) {
		Student s1 = new Student(1);
		Student s2 = new Student(2);
		List<Student> students = List.of(s1, s2);
		School school = new School(students);
		List<Student> studentList = school.students;
		//school.students = null;
		System.out.printf("students:%s",studentList.stream().map(Student::getId).map(String::valueOf).collect(Collectors.joining(",")));
	}
}
