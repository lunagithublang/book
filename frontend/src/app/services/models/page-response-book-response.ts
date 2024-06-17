/* tslint:disable */
/* eslint-disable */
import { BookResponse } from '../models/book-response';
export interface PageResponseBookResponse {
  data?: Array<BookResponse>;
  nextPageUrl?: string;
  pageNumber?: number;
  pageSize?: number;
  previousUrl?: string;
  totalPages?: number;
}
