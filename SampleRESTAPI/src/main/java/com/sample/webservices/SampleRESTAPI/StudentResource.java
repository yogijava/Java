package com.sample.webservices.SampleRESTAPI;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.student.dao.StudentDAO;
import com.student.dao.StudentDAOImplementation;
import com.student.model.Student;

@Path("/students")
@Consumes(javax.ws.rs.core.MediaType.APPLICATION_XML)
 @Produces(javax.ws.rs.core.MediaType.APPLICATION_XML)
public class StudentResource {
	
      StudentDAO dao  = new StudentDAOImplementation();
     
     @GET
  	
      public List<Student> getStudents(){
    	  System.out.println("getting from Resource");
		return dao.getAllStudents();
    	  
      }
      
     /*@POST
    	@Produces(javax.ws.rs.core.MediaType.APPLICATION_XML)
      public String addMessage()
      {
      return "POST Method";
      }*/

     @GET
     @Path("/{studentId}")
   	 
     public Student getByStudentId(@PathParam("studentId") int studentId){
    	 System.out.println("------------------------->");
		return dao.getStudentById(studentId);
    	 
     }
     
     @POST
     
       public void insertStudent(Student student)
        {
         System.out.println("POST");
		   dao.addStudent(student);
			
       }
     
     @PUT
     @Path("/{studentId}")
     
     public Student updateStudent(@PathParam("studentId") int studentId,Student student){
		student.setStudentId(studentId);
    	 
    	 return dao.updateStudent(student);
    	 
     }
     
     @DELETE
     @Path("/{studentId}")
 	 
     public void deleteStudent(@PathParam("studentId") int studentId){
    	 
    	dao.deleteStudent(studentId);
     }

}
