package com.seatingarrangement.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seatingarrangement.main.dto.BlueprintDto;
import com.seatingarrangement.main.model.Blueprint;
import com.seatingarrangement.main.model.Courses;
import com.seatingarrangement.main.model.StudentDetails;
import com.seatingarrangement.main.service.BlueprintService;
import com.seatingarrangement.main.service.CoursesService;
import com.seatingarrangement.main.service.StudentDetailsService;

@RestController
@RequestMapping("/blueprint")
public class BlueprintController {
	
	@Autowired
	private CoursesService coursesService;
	
	@Autowired
	private StudentDetailsService studentDetailsService;
	
	@Autowired
	private BlueprintService blueprintService;
	
	@PostMapping("/add/{coursesId}/{studentDetailsId}")
		public ResponseEntity<?> postBlueprint(@RequestBody Blueprint blueprint,
				                               @PathVariable("coursesId") int coursesId,
				                               @PathVariable("studentDetailsId") int studentDetailsId){
		
		Courses courses=coursesService.getById(coursesId);
		if(courses==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid courses ID given");
		}
		
		StudentDetails studentDetails=studentDetailsService.getById(studentDetailsId);
		if(studentDetails==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid studentDetails ID given");
		}
		
		blueprint.setCourses(courses);
		blueprint.setStudentDetails(studentDetails);
		
		blueprint=blueprintService.insert(blueprint);
		return ResponseEntity.status(HttpStatus.OK)
				.body(blueprint);
		
		
		
	}
	
	@PostMapping("/adding")
	public ResponseEntity<?> postBlueprint(@RequestBody Blueprint blueprint ){	
	Courses courses=coursesService.getByCourse(blueprint.getCourses().getCourse());
	
	studentDetailsService.insert(blueprint.getStudentDetails());
	
	blueprint.setCourses(courses);
	 if (courses.getNo_of_seats_available() > 0) {
         courses.setNo_of_seats_alloted(courses.getNo_of_seats_alloted() + 1);
         courses.setNo_of_seats_available(courses.getNo_of_seats_available() - 1);
	 }
	 else {
        // Branch is full, handle accordingly (throw an exception, return null, etc.)
        throw new IllegalStateException("Branch is full. Cannot add more students.");
    }
	blueprint= blueprintService.insert(blueprint);
	return ResponseEntity.status(HttpStatus.OK)
			.body(blueprint);

}
	
	 @GetMapping("/all")
	    public ResponseEntity<List<Blueprint>> getAllStudentDetails() {
	        List<Blueprint> allStudentDetails = blueprintService.getAllStudentDetails();
	        return ResponseEntity.status(HttpStatus.OK).body(allStudentDetails);
	    }
	 
	 @GetMapping("/report")
	 public List<BlueprintDto> blueprintReport(){
		 
		 List<Blueprint> list=blueprintService.getAllStudentDetails();
		 
		 List<BlueprintDto> listDto=new ArrayList<>();
		 
		 list.stream().forEach(entry->{
			 BlueprintDto dto=new BlueprintDto();
			 dto.setId(entry.getStudentDetails().getId());
			 dto.setStudentName(entry.getStudentDetails().getStudentName());
			 dto.setEmailId(entry.getStudentDetails().getEmailId());
			 dto.setMarks(entry.getStudentDetails().getMarks());
			 dto.setCourse(entry.getCourses().getCourse());
			 dto.setFloor(entry.getFloor());
			 dto.setRoom(entry.getRoom());
			 listDto.add(dto);
			 
		 });
		 return listDto;
		  
		 
	 }
	
	 
	 @PutMapping("/update/{id}")
	    public ResponseEntity<?> updateBlueprint(@PathVariable("id") int id, @RequestBody Blueprint updatedBlueprint) {
	        Blueprint blueprint = blueprintService.getById(id);
	        if (blueprint == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid blueprint ID given");
	        }

	        Courses courses = coursesService.getByCourse(updatedBlueprint.getCourses().getCourse());
	        StudentDetails studentDetails = studentDetailsService.getByStudentName(updatedBlueprint.getStudentDetails().getStudentName());

	        if (courses == null || studentDetails == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid course or studentDetails ID given");
	        }

	        blueprint.setCourses(courses);
	        blueprint.setStudentDetails(studentDetails);
	        blueprint.setFloor(updatedBlueprint.getFloor());
	        blueprint.setRoom(updatedBlueprint.getRoom());

	        blueprint = blueprintService.insert(blueprint);
	        return ResponseEntity.status(HttpStatus.OK).body(blueprint);
	    }
	
	
	}
	


