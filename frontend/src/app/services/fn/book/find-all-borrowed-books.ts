/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseBookTransactionHistoryResponse } from '../../models/page-response-book-transaction-history-response';

export interface FindAllBorrowedBooks$Params {
  number?: number;
  size?: number;
}

export function findAllBorrowedBooks(http: HttpClient, rootUrl: string, params?: FindAllBorrowedBooks$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBookTransactionHistoryResponse>> {
  const rb = new RequestBuilder(rootUrl, findAllBorrowedBooks.PATH, 'get');
  if (params) {
    rb.query('number', params.number, {});
    rb.query('size', params.size, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PageResponseBookTransactionHistoryResponse>;
    })
  );
}

findAllBorrowedBooks.PATH = '/books/borrowed';