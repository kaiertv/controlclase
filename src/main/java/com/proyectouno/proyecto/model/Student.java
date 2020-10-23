package com.proyectouno.proyecto.model;
//debilmente acoplado

//principio de responsabilidad simple
//codigo sea escalable,legible,
//testeable framework Junit

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Represnta el modelo de dominio del negocio,tablas de la BD, documentos
@Document(collection = "student")
public class Student {
// encapsulacion
@Id
private String id;
private String name;
private String apellido;
private boolean attend;

// Constructor de la clase
public Student() {
}

// Sobre escribir el constructor
// Inyeccion inicializar unos valores es sinonimo de instanciar una clase
public Student(String name, String apellido, boolean attend) {
this.name=name;
this.apellido=apellido;
this.attend=attend;
}
//Metodo publico que expone un atributo privado a otro componente
public String getId(){
return id;
}
public String getApellido() {
return apellido;
}

public void setApellido(String apellido) {
this.apellido = apellido;
}

public boolean isattend() {
return attend;
}

public void setattend(boolean attend) {
this.attend = attend;
}

public String getname() {
return name;
}

public void setname(String name) {
this.name = name;
}

//Retornar los valores en una cadena de textocomo json
@Override
public String toString() {
return "Student [id=" +id+",name"+name+", desc=" + apellido + ", attend=" +attend +"]"; 
}

}


