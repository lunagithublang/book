import { Component, OnInit } from '@angular/core';
import { TokenService } from '../../../../services/token/token.service';
import { KeycloakService } from '../../../../services/keycloak/keycloak.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent implements OnInit {

  fullName : string = '';

  constructor(
    private tokenService: TokenService,
    private keyCloakService: KeycloakService
  ) {
  }

  ngOnInit(): void {

    this.fullName = this.tokenService.fullName;

    if (typeof document !== 'undefined' && typeof window !== 'undefined') {
        const linkColor = document.querySelectorAll('.nav-link')
        linkColor.forEach( link => {
          if (window.location.href.endsWith(link.getAttribute('href') || '')) {
              link.classList.add('active')
          }
          link.addEventListener('click', () => {
            linkColor.forEach(l => l.classList.remove('active'))
            link.classList.add('active')
          })
        })
    }
  }

  // JWT logout
  // logout() {
  //   localStorage.clear()
  //   window.location.reload();
  // }

  async settings() {
    await this.keyCloakService.settings();
  }

  async logout() {
    await this.keyCloakService.logout();
  }

}
