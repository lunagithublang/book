import { Component, OnInit } from '@angular/core';
import { BookTransactionHistoryResponse, FeedBackRequest, PageResponseBookTransactionHistoryResponse } from '../../../../services/models';
import { BookService, FeedbackService } from '../../../../services/services';
import { Router } from '@angular/router';

@Component({
  selector: 'app-borrowed',
  templateUrl: './borrowed.component.html',
  styleUrl: './borrowed.component.scss'
})
export class BorrowedComponent implements OnInit{
  
  borrowedBooks: PageResponseBookTransactionHistoryResponse = {};
  selectedBook: BookTransactionHistoryResponse | undefined = undefined;
  feedbackRequest: FeedBackRequest = {
    bookId: '',
    comment: ''
  };

  private pageNumber: number = 0;
  private pageSize: number = 5;
  
  constructor(
    private bookService: BookService,
    private feedBackService: FeedbackService,
    private route: Router
  ) {
  }

  ngOnInit(): void {
    this.findAllBorrowedBooks();
  }

  private findAllBorrowedBooks() {
    this.bookService.findAllBorrowedBooks({
      number: this.pageNumber,
      size: this.pageSize
    }).subscribe({
      next: (borrowedBooksReponse: PageResponseBookTransactionHistoryResponse) => {
        this.borrowedBooks = borrowedBooksReponse;
      }
    })
  }

  returnBorrowedBook(book: BookTransactionHistoryResponse) {
    this.selectedBook = book;
    this.feedbackRequest.bookId = this.selectedBook?.bookId as string
  }

  onPageChange(pageNumber: number) {
    this.pageNumber = pageNumber;
    this.findAllBorrowedBooks();
  }

  returnBook (withFeedback: boolean) {
    this.bookService.returnBorrowedBook({
      resourceId: this.selectedBook?.bookId as string
    }).subscribe({
      next: () => {
        if (withFeedback) {
          this.giveFeedback()
        }
        this.feedbackRequest.comment = '';
        this.selectedBook = undefined
        this.findAllBorrowedBooks();
      }
    })
  }

  private giveFeedback() {
    this.feedBackService.createFeedBack({
      body: this.feedbackRequest
    }).subscribe({
      next:() => {
      }
    })
  }
}

// 9:15