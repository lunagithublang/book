import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  set token(accessToken: string) {
    if (this.isLocalStorageAvailable()) {
      localStorage.setItem('accessToken', accessToken);
    }
  }

  get token(): string | null {
    if (this.isLocalStorageAvailable()) {
      return localStorage.getItem('accessToken');
    } else {
      return null;
    }
  }

  private isLocalStorageAvailable(): boolean {
    try {
      const testKey = '__test__';
      localStorage.setItem(testKey, testKey);
      localStorage.removeItem(testKey);
      return true;
    } catch (e) {
      return false;
    }
  }

  isTokenValid() {
    const token = this.token;
    if (!token) {
      return false;
    }
   
    const jwtHelper = new JwtHelperService();
    const isTokenExpired = jwtHelper.isTokenExpired(token);

    if (isTokenExpired) {
      localStorage.clear();
      return false;
    }
    return true;
  }

  isTokenNotValid() {
    return !this.isTokenValid();
  }

  get accountRoles(): string [] {

    const token = this.token;
    if (!token) {
      return []
    }

    const jwtHelper = new JwtHelperService();
    const decodedToken = jwtHelper.decodeToken(token);

    return decodedToken.authorities;
  }

  get fullName(): string {
    
    const token = this.token;
    if (!token) {
      return '';
    }

    const jwtHelper = new JwtHelperService();
    const decodedToken = jwtHelper.decodeToken(token);

    return decodedToken.fullName;
  }

}
