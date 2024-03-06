import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginResponse } from '../model/loginresponse';
import { AuthService } from '../service/auth.service';
 
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  isError = false;
 
  constructor(private authService: AuthService, private router: Router) {}
 
  ngOnInit(): void {
    this.loginForm = new FormGroup({
      'username': new FormControl(null, Validators.required),
      'password': new FormControl(null, Validators.required),
    });
  }
 
  onSubmit(): void {
    console.log(this.loginForm.value);
    this.login(this.loginForm.value);
  }
 
  logout(): void {
    // clear specific session storage items
    sessionStorage.removeItem('user');
    sessionStorage.removeItem('token');
  }
 
 
  login(userData: any): void {
    this.authService.login(userData.username, userData.password).subscribe(
      (response: any) => {  // Use 'any' type for the response
        console.log(response);
  
        // Making sure the user is logged out
        this.logout();
  
        // Handle your response here
        const role = response.role;
        const token = response.token;
  
        sessionStorage.setItem('user', JSON.stringify({ username: userData.username, role: role }));
        sessionStorage.setItem('token', token);
  
        console.log("Logging Session Item -> ");
  
        let userItem = sessionStorage.getItem('user');
        let jwtTokenItem = sessionStorage.getItem('token');
  
        if (userItem !== null) {
          let user = JSON.parse(userItem);
          console.log("username -> ", user.username);
  
          if (user.role === 'admin') {
            this.router.navigate(['/company']);
          } else if (user.role === 'user') {
            this.router.navigate(['/dashboard']);
          }
        } else {
          console.error("User not found in sessionStorage");
        }
  
        if (jwtTokenItem !== null) {
          console.log("token -> ", jwtTokenItem);
        } else {
          console.error("JwtToken not found in sessionStorage");
        }
      },
      error => {
        console.error(error);
        this.isError = true;
      }
    );
  }
  
 
}

function token(arg0: string, token: any) {
  throw new Error('Function not implemented.');
}
