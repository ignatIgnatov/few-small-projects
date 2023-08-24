package com.demo.jwt.backend.model.dto;

public class SignUpDto {

   private String firstName;
   private String lastName;
   private String username;
   private String email;
   private char[] password;

   public SignUpDto() {
   }

   public String getFirstName() {
      return firstName;
   }

   public SignUpDto setFirstName(String firstName) {
      this.firstName = firstName;
      return this;
   }

   public String getLastName() {
      return lastName;
   }

   public SignUpDto setLastName(String lastName) {
      this.lastName = lastName;
      return this;
   }

   public String getUsername() {
      return username;
   }

   public SignUpDto setUsername(String username) {
      this.username = username;
      return this;
   }

   public String getEmail() {
      return email;
   }

   public SignUpDto setEmail(String email) {
      this.email = email;
      return this;
   }

   public char[] getPassword() {
      return password;
   }

   public SignUpDto setPassword(char[] password) {
      this.password = password;
      return this;
   }
}
