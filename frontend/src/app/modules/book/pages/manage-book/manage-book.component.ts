import { Component, OnInit } from '@angular/core';
import { BookRequest, BookResponse } from '../../../../services/models';
import { BookService } from '../../../../services/services';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-manage-book',
  templateUrl: './manage-book.component.html',
  styleUrl: './manage-book.component.scss'
})
export class ManageBookComponent implements OnInit{

  errorMessage: Array<String> = [];
  selectedPicture: string | undefined;
  selectedBookCover: any;

  bookRequest: BookRequest = {
    authorName: '',
    isbn: '',
    synopsis: '',
    title: '',
    isShareable: false
  };

  constructor(
    private bookService: BookService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ){}

  ngOnInit(): void {
    this.bookDetails()
  }

  private bookDetails() {
    // Check if there's a parameter bookId
    const bookId = this.activatedRoute.snapshot.params['bookId']
    if (bookId) {
      this.bookService.show({
        resourceId: bookId
      }).subscribe({
       next: (book: BookResponse) => {
        this.bookRequest = {
          authorName: book.authorName as string,
          isbn: book.isbn as string,
          synopsis: book.synopsis as string,
          title: book.title as string,
          isShareable: book.shareable
        }
        this.selectedPicture='data:image/jpg;base64,' + book.cover;
       }
      })
    }
  }

  onFileSelected(file: any) {
    this.selectedBookCover = file.target.files[0];
    if (this.selectedBookCover) {
      const reader: FileReader = new FileReader();
      reader.onload = () => {
        this.selectedPicture = reader.result as string
      }
      reader.readAsDataURL(this.selectedBookCover)
    }
  }

  onSaveBook() {

    const bookId = this.activatedRoute.snapshot.params['bookId']
    /// Do the update here
    if (bookId) {
      // todo update book in the backend
    } else {
      this.bookService.createBook({
        body: this.bookRequest
      }).subscribe({
         next: (book: BookResponse) => {
  
          this.bookService.uploadBookCover({
            resourceId: book.id as string,
            body: {
              file: this.selectedBookCover
            }
          }).subscribe({
            next: () => {
              this.router.navigate(['/books/my-books'])
            }
          })
  
         },
         error: (err: any) => {
          this.errorMessage = [];
          if (err.error.errors) {
            for (const [key, value] of Object.entries(err.error.errors)) {
              this.errorMessage.push(`${key}: ${value}`);
            }
          } else {
            this.errorMessage.push('An unexpected error occurred.');
          }
         }
      })
    }
  }
//10:21:41
}
