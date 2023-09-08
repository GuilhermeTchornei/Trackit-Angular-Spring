import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';

@Component({
  selector: 'app-primary-input',
  standalone: true,
  templateUrl: './primary-input.component.html',
  styleUrls: ['./primary-input.component.css'],
  imports: [MatInputModule, FormsModule],
})
export class PrimaryInputComponent {
  @Input() label!: string;
  @Input() type!: string;
  @Input() value!: string;
  @Output() valueChange = new EventEmitter<string>();

  onChange(){
    this.valueChange.emit(this.value);
  }
}
