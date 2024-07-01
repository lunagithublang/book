import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { withInterceptors, HttpClient, HttpClientModule, provideHttpClient, withFetch } from '@angular/common/http';
import { LoginComponent } from './pages/login/login.component';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './pages/register/register.component';
import { ActivateAccountComponent } from './pages/activate-account/activate-account.component';
import { CodeInputModule } from 'angular-code-input';
import { TokenService } from './services/token/token.service';
import { tokenInterceptor } from './services/interceptor/token.interceptor';
import { ApiModule } from './services/api.module';
import { KeycloakService } from './services/keycloak/keycloak.service';


export function keyCloakFactory(keycloakService: KeycloakService) {
    return () => keycloakService.init()
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ActivateAccountComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    CodeInputModule,
    ApiModule.forRoot({rootUrl: 'http://localhost:8088/api/v1'})
  ],
  providers: [
    provideClientHydration(),
    HttpClient,
    TokenService,
    provideHttpClient(
      withFetch(),
      withInterceptors([tokenInterceptor])
    ),
    {
      provide: APP_INITIALIZER,
      deps: [KeycloakService],
      useFactory: keyCloakFactory,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
