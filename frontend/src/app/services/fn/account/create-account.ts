/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { AccountRequest } from '../../models/account-request';
import { AuthenticateResponse } from '../../models/authenticate-response';

export interface CreateAccount$Params {
      body: AccountRequest
}

export function createAccount(http: HttpClient, rootUrl: string, params: CreateAccount$Params, context?: HttpContext): Observable<StrictHttpResponse<AuthenticateResponse>> {
  const rb = new RequestBuilder(rootUrl, createAccount.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<AuthenticateResponse>;
    })
  );
}

createAccount.PATH = '/accounts/register';
