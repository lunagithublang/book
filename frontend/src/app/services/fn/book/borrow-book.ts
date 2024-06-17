/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { BookTransactionHistoryResponse } from '../../models/book-transaction-history-response';

export interface BorrowBook$Params {
  resourceId: string;
}

export function borrowBook(http: HttpClient, rootUrl: string, params: BorrowBook$Params, context?: HttpContext): Observable<StrictHttpResponse<BookTransactionHistoryResponse>> {
  const rb = new RequestBuilder(rootUrl, borrowBook.PATH, 'post');
  if (params) {
    rb.path('resourceId', params.resourceId, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<BookTransactionHistoryResponse>;
    })
  );
}

borrowBook.PATH = '/books/borrow/{resourceId}';
