/* tslint:disable */
/* eslint-disable */
import { FeedBackBookResponse } from '../models/feed-back-book-response';
export interface FeedBackResponse {
  book?: FeedBackBookResponse;
  comment?: string;
  createdAt?: string;
  createdBy?: string;
  id?: string;
  note?: number;
  ownFeedback?: boolean;
  updatedAt?: string;
  updatedBy?: string;
}
