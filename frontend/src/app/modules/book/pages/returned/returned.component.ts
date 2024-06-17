import { Component, OnInit } from '@angular/core';
import { PageResponseBookTransactionHistoryResponse } from '../../../../services/models/page-response-book-transaction-history-response';
import { BookService } from '../../../../services/services';
import { BookTransactionHistoryResponse } from '../../../../services/models/book-transaction-history-response';

@Component({
  selector: 'app-returned',
  templateUrl: './returned.component.html',
  styleUrl: './returned.component.scss'
})
export class ReturnedComponent implements OnInit{
 
  returnedBooks: PageResponseBookTransactionHistoryResponse = {}
  private pageNumber: number = 0;
  private pageSize: number = 5;
  level : 'success' | 'error' = 'success'
  message: string = '';

  constructor(private bookService: BookService){}

  ngOnInit(): void {
    this.findAllReturnedBooks();
  }

  private findAllReturnedBooks () {
    this.bookService.findAllReturnedBooks({
      number: this.pageNumber,
      size: this.pageSize
    }).subscribe({
      next: (returnedBooks: PageResponseBookTransactionHistoryResponse) => {
        this.returnedBooks = returnedBooks
      }
    })
  }

  approveBookReturn (returnedBook: BookTransactionHistoryResponse) {
    if (!returnedBook.returned) {
      return;
    }

    this.bookService.approveReturnBorrowedBook({
      resourceId: returnedBook.bookId as string
    }).subscribe({
      next:() => {
        this.level = 'success'
        this.message = 'Book return approved';
        this.findAllReturnedBooks();
      }
    })
  }

  onPageChange(pageNumber: number) {
    this.pageNumber = pageNumber;
    this.findAllReturnedBooks();
  }


}
