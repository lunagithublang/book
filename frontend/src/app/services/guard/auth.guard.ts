import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { TokenService } from '../token/token.service';
import { KeycloakService } from '../keycloak/keycloak.service';

export const authGuard: CanActivateFn = () => {

  const tokenService: TokenService = inject(TokenService)
  const keyCloakService: KeycloakService = inject(KeycloakService);
  const router: Router = inject(Router)

  // Keycloak implementation
  if (keyCloakService.keyCloak?.isTokenExpired()) {
    router.navigate(['login'])
    return false;
  }

  // JWT implementation
  // dont need this since I added Keycloak
  // if (tokenService.isTokenNotValid()) {
  //   router.navigate(['login'])
  //   return false;
  // }

  return true;
};
