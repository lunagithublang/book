/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { createFeedBack } from '../fn/feedback/create-feed-back';
import { CreateFeedBack$Params } from '../fn/feedback/create-feed-back';
import { FeedBackResponse } from '../models/feed-back-response';
import { index } from '../fn/feedback/index';
import { Index$Params } from '../fn/feedback/index';
import { PageResponseFeedBackResponse } from '../models/page-response-feed-back-response';

@Injectable({ providedIn: 'root' })
export class FeedbackService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `createFeedBack()` */
  static readonly CreateFeedBackPath = '/feedbacks';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `createFeedBack()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createFeedBack$Response(params: CreateFeedBack$Params, context?: HttpContext): Observable<StrictHttpResponse<FeedBackResponse>> {
    return createFeedBack(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `createFeedBack$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createFeedBack(params: CreateFeedBack$Params, context?: HttpContext): Observable<FeedBackResponse> {
    return this.createFeedBack$Response(params, context).pipe(
      map((r: StrictHttpResponse<FeedBackResponse>): FeedBackResponse => r.body)
    );
  }

  /** Path part for operation `index()` */
  static readonly IndexPath = '/feedbacks/book/{bookId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `index()` instead.
   *
   * This method doesn't expect any request body.
   */
  index$Response(params: Index$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseFeedBackResponse>> {
    return index(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `index$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  index(params: Index$Params, context?: HttpContext): Observable<PageResponseFeedBackResponse> {
    return this.index$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseFeedBackResponse>): PageResponseFeedBackResponse => r.body)
    );
  }

}
