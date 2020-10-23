package com.proyectouno.proyecto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.proyectouno.proyecto.model.Student;
import com.proyectouno.proyecto.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class StudentController {
//se van hacer los mapeos, implemento metodos donde exponen los datos
//metodo que devuelva una lista de estudiantes
//coloco la anotacion @GetMapping("/students") para ir a consultar una 
//informacion del servidor y que el me la retorne esa informacion
//llamo al repositorio es una forma de instanciar cololcarle un sobrenombre
//Forma rapida de inyectar, form facil de llamr una clase que se usara en otro recurso
//para que no sea nulla.
//metodos donde se expone informacion o se crea nueva informacion PUT,post
@Autowired
StudentRepository studentrepository;

/*Forma tradiciona de para inyectar
public StudentController(StudentRepository studentRepository){
this.studentrepository=studentRepository;
}*/
//Crear un recurso en el servidor
@PostMapping("/students")
public ResponseEntity<Student> createTutorials(@RequestBody Student student){
try{
Student _student = studentrepository.save(new Student(student.getname(),student.getApellido(),false));
return new ResponseEntity<>(_student,HttpStatus.CREATED);
}catch(Exception e){
return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

}
}

@GetMapping("/students")
public ResponseEntity<List<Student>> getAllTutorials(@RequestParam(required=false) String name){
try{
List<Student> students = new ArrayList<Student>();//Inicializa vacia

if(name==null){
studentrepository.findAll().forEach(students::add);//forma de llenar elementos
}else{
studentrepository.findByNameContaining(name).forEach(students::add);
} 
if(students.isEmpty()){
return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
return new ResponseEntity<>(students,HttpStatus.OK);
//SI HAY ERROR O EXCEPCION
//return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
}catch(Exception e){
return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
}
}
@GetMapping("/students/{id}")
public ResponseEntity<?> getStudentById(@PathVariable("id") String id){
Optional<Student> studentData = studentrepository.findById(id);

if(studentData.isPresent()){
return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
}else{
return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
}

@DeleteMapping("/students/{id}")
public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
try{
studentrepository.deleteById(id);
return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}catch (Exception e){
return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
}
}
@PutMapping("/students/{id}")
public ResponseEntity<?> update(@PathVariable ("id") String id, @RequestBody Student student){
Optional<Student> studentData = studentrepository.findById(id);
if(studentData.isPresent()){
Student _student =studentData.get();
_student.setname(student.getname());
_student.setApellido(student.getApellido());
_student.setattend(false);
studentrepository.save(_student);
return new ResponseEntity<>(_student,HttpStatus.OK);
}else{
return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
}
} 


