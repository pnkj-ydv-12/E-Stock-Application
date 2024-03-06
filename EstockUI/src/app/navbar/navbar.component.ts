import { Component } from '@angular/core';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})

export class NavbarComponent {
  isError: boolean | undefined;
// logincomponent.ts
constructor( private authService: AuthService) { }

isAdminLoggedIn(): boolean {
  // Add your logic here to check if admin is logged in
  // Return true if admin is logged in, false otherwise
  return this.userRole === 'admin';
}
userRole: string = '';

    // Other methods...

    login(userData: any): void {
        this.authService.login(userData.username, userData.password).subscribe(
            (response: any) => {
                // Handle your response here
                this.userRole = response.role; // Update userRole property

                // Rest of your login logic...

            },
            error => {
                console.error(error);
                this.isError = true;
            }
        );
    }
}
