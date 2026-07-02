import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

import { Auth } from '../../../core/services/auth';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class LoginComponent {

  usersname = '';
  password = '';

  constructor(private router: Router, private authService: Auth) {}

  login() {

    this.authService.login({
      usersname: this.usersname,
      password: this.password
    }).subscribe(() => {

      this.router.navigate(['/products']);

    }, () => {
      alert('Usuario o contraseña incorrectos');
    });

  }

}