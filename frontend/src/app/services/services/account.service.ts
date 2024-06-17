/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { AuthenticateResponse } from '../models/authenticate-response';
import { createAccount } from '../fn/account/create-account';
import { CreateAccount$Params } from '../fn/account/create-account';

@Injectable({ providedIn: 'root' })
export class AccountService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `createAccount()` */
  static readonly CreateAccountPath = '/accounts/register';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `createAccount()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createAccount$Response(params: CreateAccount$Params, context?: HttpContext): Observable<StrictHttpResponse<AuthenticateResponse>> {
    return createAccount(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `createAccount$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createAccount(params: CreateAccount$Params, context?: HttpContext): Observable<AuthenticateResponse> {
    return this.createAccount$Response(params, context).pipe(
      map((r: StrictHttpResponse<AuthenticateResponse>): AuthenticateResponse => r.body)
    );
  }

}
