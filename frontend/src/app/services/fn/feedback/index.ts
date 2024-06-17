/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseFeedBackResponse } from '../../models/page-response-feed-back-response';

export interface Index$Params {
  bookId: string;
  number?: number;
  size?: number;
}

export function index(http: HttpClient, rootUrl: string, params: Index$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseFeedBackResponse>> {
  const rb = new RequestBuilder(rootUrl, index.PATH, 'get');
  if (params) {
    rb.path('bookId', params.bookId, {});
    rb.query('number', params.number, {});
    rb.query('size', params.size, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PageResponseFeedBackResponse>;
    })
  );
}

index.PATH = '/feedbacks/book/{bookId}';
