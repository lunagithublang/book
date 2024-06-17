import { Component, OnInit } from '@angular/core';
import { BookResponse, PageResponseBookResponse } from '../../../../services/models';
import { BookService } from '../../../../services/services';
import { Router } from '@angular/router';

@Component({
  selector: 'app-my-books',
  templateUrl: './my-books.component.html',
  styleUrl: './my-books.component.scss'
})
export class MyBooksComponent implements OnInit {

  bookResponse: PageResponseBookResponse = {}
  private pageNumber = 0;
  private pageSize = 4;

  ngOnInit(): void {
    this.findAllBooks()
  }
  
  constructor(
    private bookService: BookService,
    private router: Router
  ) {
  }
  
  private findAllBooks() {
    this.bookService.findAllBooksByOwner({
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

  archiveBook(book: BookResponse) {
    this.bookService.updateArchiveStatus({
      resourceId: book.id as string

    }).subscribe({
      next: () => {
        book.archived = !book.archived;
      },
      error: (err: any) => {
        console.log(err)
      }
    })
  }

  shareBook(book: BookResponse) {
   this.bookService.updateShareableStatus({
    resourceId: book.id as string
   }).subscribe({
      next: () => {
        book.shareable = !book.shareable;
      }
   })
  }

  editBook(book: BookResponse) {
    this.router.navigate(['books', 'manage', book.id])
  }

} 
