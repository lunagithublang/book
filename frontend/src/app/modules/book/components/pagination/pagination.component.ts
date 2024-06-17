import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.scss'
})
export class PaginationComponent {

  @Input() pageNumber?: number = 0;
  @Input() pageSize?: number = 0;
  @Input() totalPages?: number = 0;
  @Input() nextPageUrl?: string;
  @Input() previousUrl?: string;
  @Output() pageChange: EventEmitter<number> = new EventEmitter<number>();

  gotToFirstPage() {
    if(this.pageNumber !== 0) {
      this.pageChange.emit(0)
    }
  }

  gotToPreviousPage() {
    const previousPage = this.pageNumber as number - 1;
    if (previousPage >= 0) {
      this.pageChange.emit(previousPage);
    }
  }

  goToPage(pageIndex: number) {
    this.pageChange.emit(pageIndex)
  }

  gotToNextPage() {
    const nextPage = this.pageNumber as number + 1;
    const totalPages = this.totalPages as number;
    if (nextPage < totalPages) {
      this.pageChange.emit(nextPage);
    }
  }

  gotToLastPage() {
    const lastPage = this.totalPages as number - 1;
    if (this.pageNumber !== lastPage) {
      this.pageChange.emit(lastPage);
    }
  }

  isLastPage(): boolean {
    return this.pageNumber == this.totalPages as number - 1;
  }

  get pages(): number[] {
    const total = this.totalPages as number;
    return Array.from({ length: total }, (_value, index) => index);
  }

}
