import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ProductService } from '../../../core/services/product';
import { Product } from '../../../core/models/product';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product-list.html',
  styleUrl: './product-list.css'
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];

  constructor(
    private service: ProductService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts() {
    this.service.getAll().subscribe(data => {
      this.products = data;
    });
  }

  delete(id: number) {
    this.service.delete(id).subscribe(() => {
      this.loadProducts();
    });
  }

  newProduct() {
    this.router.navigate(['/products/new']);
  }

  edit(id: number) {
    this.router.navigate(['/products/edit', id]);
  }

}