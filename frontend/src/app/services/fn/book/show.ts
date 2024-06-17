/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { BookResponse } from '../../models/book-response';

export interface Show$Params {
  resourceId: string;
}

export function show(http: HttpClient, rootUrl: string, params: Show$Params, context?: HttpContext): Observable<StrictHttpResponse<BookResponse>> {
  const rb = new RequestBuilder(rootUrl, show.PATH, 'get');
  if (params) {
    rb.path('resourceId', params.resourceId, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<BookResponse>;
    })
  );
}

show.PATH = '/books/{resourceId}';
