import { Component } from '@angular/core';
import { AuthenticateRequest } from '../../services/models';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/services';
import { TokenService } from '../../services/token/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  authRequest: AuthenticateRequest = {email: '', password: ''}
  errorMessage: Array<String> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private tokenService: TokenService
  ) {}


  login() {
    this.errorMessage = [];
    this.authService.authenticate({
      body: this.authRequest
    }).subscribe({
      next:(res) => {
        // Save the token
        this.tokenService.setAccessToken = res.accessToken as string;
        this.router.navigate(['books']);
      },
      error:(err) => {
        if (err.error.errors) {
          for (const [key, value] of Object.entries(err.error.errors)) {
            this.errorMessage.push(`${key}: ${value}`);
          }
        } else {
          this.errorMessage.push('An unexpected error occurred.');
        }
      }
    })
  }

  register() {
    this.router.navigate(['register'])
  }

}
