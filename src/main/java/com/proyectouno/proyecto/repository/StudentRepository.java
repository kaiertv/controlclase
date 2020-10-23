package com.proyectouno.proyecto.repository;

import java.util.List;
import com.proyectouno.proyecto.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student,String>{//Mongorepository tiene los metodos para el crud
    List<Student> findByAttend(boolean asistio);
    List<Student> findByNameContaining(String name);
}



