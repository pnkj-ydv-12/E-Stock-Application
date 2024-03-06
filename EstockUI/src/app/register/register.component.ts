import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms'; // Import FormBuilder and Validators
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;

  constructor(private http: HttpClient, private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      'username': [null, Validators.required],
      'firstname': [null, Validators.required],
      'lastname': [null, Validators.required],
      'email': [null, [Validators.required, Validators.email]],
      'password': [null, Validators.required],
      'contact': [null, Validators.required],
      'role': [null, Validators.required],
    });
  }

  onSubmit(): void {
    console.log(this.registerForm.value);
  
    // Assuming you have a 'role' field in your form
    const role: string = this.registerForm.get('role')?.value;
  
    const isAdmin: boolean = role.toLowerCase() === 'admin';
  
    this.saveUser(this.registerForm.value, isAdmin);
  }
  
  saveUser(userData: any, isAdmin: boolean): void {
    const payload = {
      ...userData,
      role: isAdmin ? "admin" : "user"
    };

    this.http.post('http://localhost:8080/auth/v1.0/add/user', payload)
      .pipe(
        catchError((error) => {
          // Handle the error here
          console.error('Error occurred during user registration:', error);
          // Optionally, you can rethrow the error to propagate it to the subscriber
          return throwError(error);
        })
      )
      .subscribe(
        (response) => {
          console.log('Registration successful:', response);
          // handle your response here
          this.registerForm.reset(); // Reset the form
          this.router.navigate(['/login']); // Navigate to login after successful registration
        },
        (error) => {
          // handle error here (if not handled in the catchError block)
          console.error('Error occurred during user registration:', error);
          this.registerForm.reset(); // Reset the form
          this.router.navigate(['/login']); // Navigate to login after unsuccessful registration
        }
      );
  }
}
