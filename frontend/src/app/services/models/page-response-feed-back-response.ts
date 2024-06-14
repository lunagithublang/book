/* tslint:disable */
/* eslint-disable */
import { FeedBackResponse } from '../models/feed-back-response';
export interface PageResponseFeedBackResponse {
  data?: Array<FeedBackResponse>;
  nextPageUrl?: string;
  pageNumber?: number;
  pageSize?: number;
  previousUrl?: string;
  totalPages?: number;
}
