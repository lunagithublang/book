import { Injectable, PLATFORM_ID, Inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import Keycloak from 'keycloak-js';
import { AccountProfile } from './account-profile';

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {

  private _keyCloak: Keycloak | undefined;
  private _accountProfile: AccountProfile | undefined;

  get accountProfile() : AccountProfile | undefined{
    return this._accountProfile;
  }

  get keyCloak() {
    if(!this._keyCloak) {
      this._keyCloak = new Keycloak({
        url: 'http://localhost:9090',
        realm: 'book',
        clientId: 'book'
      })
    }
    return this._keyCloak
  }

  constructor(@Inject(PLATFORM_ID) private platformId: Object) { }

  async init () {

    if (!isPlatformBrowser(this.platformId)) {
      console.log("Not running in the browser, do nothing")
      return;
    }

    console.log("Authenticating the user...")
    const authenticated = await this.keyCloak?.init({
      onLoad: 'login-required',
    })

    if (authenticated) {
      console.log("User authenticated...")
      this._accountProfile = (await this.keyCloak?.loadUserProfile()) as AccountProfile;
      this._accountProfile.token = this.keyCloak?.token;
    }
  }

  settings () {
    return this.keyCloak?.accountManagement();
  }

  login () {
    return this.keyCloak?.login();
  }

  logout () {
    return this.keyCloak?.logout({
      redirectUri: 'http://localhost:4200'
    });
  }

}
