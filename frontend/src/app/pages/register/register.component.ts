import { Component } from '@angular/core';
import { AccountRequest } from '../../services/models';
import { Router } from '@angular/router';
import { AccountService } from '../../services/services';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {

  errorMessage: Array<String> = [];
  accountRequest: AccountRequest = {
    email: '',
    firstName: '',
    lastName: '',
    password: ''
  }

  constructor(
    private router: Router,
    private accountService: AccountService
  ){}

  register () {
    this.errorMessage = [];
    this.accountService.create({
      body: this.accountRequest
    }).subscribe({
      next: () => {
        this.router.navigate(['activate-account'])
      },
      error: (err) => {
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

  login() {
    this.router.navigate(['login'])
  }

}
