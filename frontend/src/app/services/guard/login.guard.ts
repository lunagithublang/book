import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { TokenService } from '../token/token.service';

export const loginGuard: CanActivateFn = () => {

  const tokenService = inject(TokenService)
  const router = inject(Router)

  if (tokenService.isTokenValid()) {
    router.navigate(['/books'])
    return false;
  }

  return true;
};
