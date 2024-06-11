import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  set setAccessToken(accessToken: string) {
    localStorage.setItem('accessToken', accessToken);
  }

  get getAccessToken() {
    return localStorage.getItem('accessToken') as string;
  }
}
