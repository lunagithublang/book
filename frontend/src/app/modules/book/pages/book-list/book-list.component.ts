import { Component, OnInit } from '@angular/core';
import { BookService } from '../../../../services/services';
import { Router } from '@angular/router';
import { BookResponse, PageResponseBookResponse } from '../../../../services/models';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrl: './book-list.component.scss'
})
export class BookListComponent implements OnInit{
  
  bookResponse: PageResponseBookResponse = {}
  
  message: string = ""
  messageStatus: string = "";

  private pageNumber = 0;
  private pageSize = 4;

  constructor(
    private bookService: BookService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.findAllBooks()
  }

  private findAllBooks() {
    this.bookService.displayAllBooks({
      number: this.pageNumber,
      size: this.pageSize
    }).subscribe({
      next:(books: PageResponseBookResponse) => {
        this.bookResponse = books;
      },
      error: (err: any) => {
        console.log(err)
      }
    })
  }

  onPageChange(pageNumber: number) {
    this.pageNumber = pageNumber;
    this.findAllBooks();
  }

  borrowBook(book: BookResponse) {
    this.message = ""
    this.bookService.borrowBook({
      'resourceId': book.id as string
    }).subscribe({
      next:() => {
        this.messageStatus = "success"
        this.message = "The Book " + book.title + " was successfully added to you list"
      },
      error: (err: any) => {
        this.messageStatus = "error"
        this.message = err.error.errors.message  
      }
    })
  }

}
