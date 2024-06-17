/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { approveReturnBorrowedBook } from '../fn/book/approve-return-borrowed-book';
import { ApproveReturnBorrowedBook$Params } from '../fn/book/approve-return-borrowed-book';
import { BookResponse } from '../models/book-response';
import { BookTransactionHistoryResponse } from '../models/book-transaction-history-response';
import { borrowBook } from '../fn/book/borrow-book';
import { BorrowBook$Params } from '../fn/book/borrow-book';
import { createBook } from '../fn/book/create-book';
import { CreateBook$Params } from '../fn/book/create-book';
import { displayAllBooks } from '../fn/book/display-all-books';
import { DisplayAllBooks$Params } from '../fn/book/display-all-books';
import { findAllBooksByOwner } from '../fn/book/find-all-books-by-owner';
import { FindAllBooksByOwner$Params } from '../fn/book/find-all-books-by-owner';
import { findAllBorrowedBooks } from '../fn/book/find-all-borrowed-books';
import { FindAllBorrowedBooks$Params } from '../fn/book/find-all-borrowed-books';
import { findAllReturnedBooks } from '../fn/book/find-all-returned-books';
import { FindAllReturnedBooks$Params } from '../fn/book/find-all-returned-books';
import { PageResponseBookResponse } from '../models/page-response-book-response';
import { PageResponseBookTransactionHistoryResponse } from '../models/page-response-book-transaction-history-response';
import { returnBorrowedBook } from '../fn/book/return-borrowed-book';
import { ReturnBorrowedBook$Params } from '../fn/book/return-borrowed-book';
import { show } from '../fn/book/show';
import { Show$Params } from '../fn/book/show';
import { updateArchiveStatus } from '../fn/book/update-archive-status';
import { UpdateArchiveStatus$Params } from '../fn/book/update-archive-status';
import { updateShareableStatus } from '../fn/book/update-shareable-status';
import { UpdateShareableStatus$Params } from '../fn/book/update-shareable-status';
import { uploadBookCover } from '../fn/book/upload-book-cover';
import { UploadBookCover$Params } from '../fn/book/upload-book-cover';

@Injectable({ providedIn: 'root' })
export class BookService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `displayAllBooks()` */
  static readonly DisplayAllBooksPath = '/books';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `displayAllBooks()` instead.
   *
   * This method doesn't expect any request body.
   */
  displayAllBooks$Response(params?: DisplayAllBooks$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBookResponse>> {
    return displayAllBooks(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `displayAllBooks$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  displayAllBooks(params?: DisplayAllBooks$Params, context?: HttpContext): Observable<PageResponseBookResponse> {
    return this.displayAllBooks$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseBookResponse>): PageResponseBookResponse => r.body)
    );
  }

  /** Path part for operation `createBook()` */
  static readonly CreateBookPath = '/books';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `createBook()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createBook$Response(params: CreateBook$Params, context?: HttpContext): Observable<StrictHttpResponse<BookResponse>> {
    return createBook(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `createBook$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createBook(params: CreateBook$Params, context?: HttpContext): Observable<BookResponse> {
    return this.createBook$Response(params, context).pipe(
      map((r: StrictHttpResponse<BookResponse>): BookResponse => r.body)
    );
  }

  /** Path part for operation `uploadBookCover()` */
  static readonly UploadBookCoverPath = '/books/cover/{resourceId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `uploadBookCover()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  uploadBookCover$Response(params: UploadBookCover$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return uploadBookCover(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `uploadBookCover$Response()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  uploadBookCover(params: UploadBookCover$Params, context?: HttpContext): Observable<{
}> {
    return this.uploadBookCover$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

  /** Path part for operation `borrowBook()` */
  static readonly BorrowBookPath = '/books/borrow/{resourceId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `borrowBook()` instead.
   *
   * This method doesn't expect any request body.
   */
  borrowBook$Response(params: BorrowBook$Params, context?: HttpContext): Observable<StrictHttpResponse<BookTransactionHistoryResponse>> {
    return borrowBook(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `borrowBook$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  borrowBook(params: BorrowBook$Params, context?: HttpContext): Observable<BookTransactionHistoryResponse> {
    return this.borrowBook$Response(params, context).pipe(
      map((r: StrictHttpResponse<BookTransactionHistoryResponse>): BookTransactionHistoryResponse => r.body)
    );
  }

  /** Path part for operation `updateShareableStatus()` */
  static readonly UpdateShareableStatusPath = '/books/shareable/{resourceId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateShareableStatus()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateShareableStatus$Response(params: UpdateShareableStatus$Params, context?: HttpContext): Observable<StrictHttpResponse<BookResponse>> {
    return updateShareableStatus(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateShareableStatus$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateShareableStatus(params: UpdateShareableStatus$Params, context?: HttpContext): Observable<BookResponse> {
    return this.updateShareableStatus$Response(params, context).pipe(
      map((r: StrictHttpResponse<BookResponse>): BookResponse => r.body)
    );
  }

  /** Path part for operation `returnBorrowedBook()` */
  static readonly ReturnBorrowedBookPath = '/books/borrow/return/{resourceId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `returnBorrowedBook()` instead.
   *
   * This method doesn't expect any request body.
   */
  returnBorrowedBook$Response(params: ReturnBorrowedBook$Params, context?: HttpContext): Observable<StrictHttpResponse<BookTransactionHistoryResponse>> {
    return returnBorrowedBook(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `returnBorrowedBook$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  returnBorrowedBook(params: ReturnBorrowedBook$Params, context?: HttpContext): Observable<BookTransactionHistoryResponse> {
    return this.returnBorrowedBook$Response(params, context).pipe(
      map((r: StrictHttpResponse<BookTransactionHistoryResponse>): BookTransactionHistoryResponse => r.body)
    );
  }

  /** Path part for operation `approveReturnBorrowedBook()` */
  static readonly ApproveReturnBorrowedBookPath = '/books/borrow/return/approved/{resourceId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `approveReturnBorrowedBook()` instead.
   *
   * This method doesn't expect any request body.
   */
  approveReturnBorrowedBook$Response(params: ApproveReturnBorrowedBook$Params, context?: HttpContext): Observable<StrictHttpResponse<BookTransactionHistoryResponse>> {
    return approveReturnBorrowedBook(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `approveReturnBorrowedBook$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  approveReturnBorrowedBook(params: ApproveReturnBorrowedBook$Params, context?: HttpContext): Observable<BookTransactionHistoryResponse> {
    return this.approveReturnBorrowedBook$Response(params, context).pipe(
      map((r: StrictHttpResponse<BookTransactionHistoryResponse>): BookTransactionHistoryResponse => r.body)
    );
  }

  /** Path part for operation `updateArchiveStatus()` */
  static readonly UpdateArchiveStatusPath = '/books/archive/{resourceId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateArchiveStatus()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateArchiveStatus$Response(params: UpdateArchiveStatus$Params, context?: HttpContext): Observable<StrictHttpResponse<BookResponse>> {
    return updateArchiveStatus(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateArchiveStatus$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateArchiveStatus(params: UpdateArchiveStatus$Params, context?: HttpContext): Observable<BookResponse> {
    return this.updateArchiveStatus$Response(params, context).pipe(
      map((r: StrictHttpResponse<BookResponse>): BookResponse => r.body)
    );
  }

  /** Path part for operation `show()` */
  static readonly ShowPath = '/books/{resourceId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `show()` instead.
   *
   * This method doesn't expect any request body.
   */
  show$Response(params: Show$Params, context?: HttpContext): Observable<StrictHttpResponse<BookResponse>> {
    return show(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `show$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  show(params: Show$Params, context?: HttpContext): Observable<BookResponse> {
    return this.show$Response(params, context).pipe(
      map((r: StrictHttpResponse<BookResponse>): BookResponse => r.body)
    );
  }

  /** Path part for operation `findAllReturnedBooks()` */
  static readonly FindAllReturnedBooksPath = '/books/returned';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllReturnedBooks()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllReturnedBooks$Response(params?: FindAllReturnedBooks$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBookTransactionHistoryResponse>> {
    return findAllReturnedBooks(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllReturnedBooks$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllReturnedBooks(params?: FindAllReturnedBooks$Params, context?: HttpContext): Observable<PageResponseBookTransactionHistoryResponse> {
    return this.findAllReturnedBooks$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseBookTransactionHistoryResponse>): PageResponseBookTransactionHistoryResponse => r.body)
    );
  }

  /** Path part for operation `findAllBooksByOwner()` */
  static readonly FindAllBooksByOwnerPath = '/books/owner';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllBooksByOwner()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllBooksByOwner$Response(params?: FindAllBooksByOwner$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBookResponse>> {
    return findAllBooksByOwner(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllBooksByOwner$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllBooksByOwner(params?: FindAllBooksByOwner$Params, context?: HttpContext): Observable<PageResponseBookResponse> {
    return this.findAllBooksByOwner$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseBookResponse>): PageResponseBookResponse => r.body)
    );
  }

  /** Path part for operation `findAllBorrowedBooks()` */
  static readonly FindAllBorrowedBooksPath = '/books/borrowed';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllBorrowedBooks()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllBorrowedBooks$Response(params?: FindAllBorrowedBooks$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBookTransactionHistoryResponse>> {
    return findAllBorrowedBooks(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllBorrowedBooks$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllBorrowedBooks(params?: FindAllBorrowedBooks$Params, context?: HttpContext): Observable<PageResponseBookTransactionHistoryResponse> {
    return this.findAllBorrowedBooks$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseBookTransactionHistoryResponse>): PageResponseBookTransactionHistoryResponse => r.body)
    );
  }

}
