package com.github.learning_spring.errors;

public class DepartmentNotFound extends Exception{
   
   public DepartmentNotFound(String message) {
       super(message);
   }

   public DepartmentNotFound(String message, Throwable cause) {
       super(message, cause);
   }

   public DepartmentNotFound(Throwable cause) {
       super(cause);
   }

    public DepartmentNotFound() {
         super();
    }


}
