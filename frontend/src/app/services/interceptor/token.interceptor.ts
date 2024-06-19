import { HttpInterceptorFn } from '@angular/common/http';
import { TokenService } from '../token/token.service';
import { inject } from '@angular/core';
import { KeycloakService } from '../keycloak/keycloak.service';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  const tokenService: TokenService = inject(TokenService);
  // const token = tokenService.token;

  const keyCloakService: KeycloakService = inject(KeycloakService);
  const token = keyCloakService.keyCloak?.token;

  if (token) {
    const authRequest = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(authRequest);
  }

  return next(req);
};
