import { Component, Input } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';

@Component({
  selector: 'app-primary-button',
  standalone: true,
  templateUrl: './primary-button.component.html',
  styleUrls: ['./primary-button.component.css'],
  imports: [MatButtonModule]
})
export class PrimaryButtonComponent {
  @Input() text!: string;
}
