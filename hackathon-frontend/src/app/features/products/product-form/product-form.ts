import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { ProductService } from '../../../core/services/product';
import { Product } from '../../../core/models/product';

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './product-form.html',
  styleUrl: './product-form.css'
})
export class ProductFormComponent implements OnInit {

  product: Product = {
    productName: '',
    unitPrice: 0,
    stock: 0
  };

  id = 0;

  constructor(
    private service: ProductService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {

    this.id = Number(this.route.snapshot.paramMap.get('id'));

    if(this.id){

      this.service.getById(this.id).subscribe(data=>{
        this.product = data;
      });

    }

  }

  save(){

    if(this.id){

      this.service.update(this.id,this.product).subscribe(()=>{
        this.router.navigate(['/products']);
      });

    }else{

      this.service.create(this.product).subscribe(()=>{
        this.router.navigate(['/products']);
      });

    }

  }

}