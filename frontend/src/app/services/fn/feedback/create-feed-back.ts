/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { FeedBackRequest } from '../../models/feed-back-request';
import { FeedBackResponse } from '../../models/feed-back-response';

export interface CreateFeedBack$Params {
      body: FeedBackRequest
}

export function createFeedBack(http: HttpClient, rootUrl: string, params: CreateFeedBack$Params, context?: HttpContext): Observable<StrictHttpResponse<FeedBackResponse>> {
  const rb = new RequestBuilder(rootUrl, createFeedBack.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<FeedBackResponse>;
    })
  );
}

createFeedBack.PATH = '/feedbacks';
