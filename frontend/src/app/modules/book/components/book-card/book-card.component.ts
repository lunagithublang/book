import { Component, EventEmitter, Input, Output } from '@angular/core';
import { BookResponse } from '../../../../services/models';

@Component({
  selector: 'app-book-card',
  templateUrl: './book-card.component.html',
  styleUrl: './book-card.component.scss'
})
export class BookCardComponent {

  private _book: BookResponse = {};
  private _bookCover: String | undefined;
  private _manage: boolean = false;

  get book(): BookResponse {
    return this._book;
  }

  @Input()
  set book(value: BookResponse) {
    this._book = value;
  }

  get bookCover():  String | undefined{
    if (this.book.cover) {
       return "data:image/jpg;base64, " + this.book.cover;
    }
    return 'none';
  }

  @Input()
  set bookCover(value: String) {
     this._bookCover = value;
  }

  get manage() : boolean{
    return this._manage;
  }

  @Input()
  set manage(value: boolean){
    this._manage = value
  }

  @Output() private showDetails: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  @Output() private borrow: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  @Output() private addToWaitingList: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  @Output() private edit: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  @Output() private share: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  @Output() private archive: EventEmitter<BookResponse> = new EventEmitter<BookResponse>();
  

  onShowDetails () {
    this.showDetails.emit(this._book)
  }

  onBorrow () {
    this.borrow.emit(this._book)
  }

  onAddToWaitingList () {
    this.addToWaitingList.emit(this._book)
  }

  onEdit () {
    this.edit.emit(this._book)
  }

  onShare () {
    this.share.emit(this._book)
  }

  onArchive () {
    this.archive.emit(this._book)
  }

} 
