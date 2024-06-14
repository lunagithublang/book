/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { BookTransactionHistoryResponse } from '../../models/book-transaction-history-response';

export interface ReturnBorrowedBook$Params {
  resourceId: string;
}

export function returnBorrowedBook(http: HttpClient, rootUrl: string, params: ReturnBorrowedBook$Params, context?: HttpContext): Observable<StrictHttpResponse<BookTransactionHistoryResponse>> {
  const rb = new RequestBuilder(rootUrl, returnBorrowedBook.PATH, 'patch');
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

returnBorrowedBook.PATH = '/books/borrow/return/{resourceId}';
