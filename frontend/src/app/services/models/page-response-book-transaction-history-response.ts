/* tslint:disable */
/* eslint-disable */
import { BookTransactionHistoryResponse } from '../models/book-transaction-history-response';
export interface PageResponseBookTransactionHistoryResponse {
  data?: Array<BookTransactionHistoryResponse>;
  nextPageUrl?: string;
  pageNumber?: number;
  pageSize?: number;
  previousUrl?: string;
  totalPages?: number;
}
