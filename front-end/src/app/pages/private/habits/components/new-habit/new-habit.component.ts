import { Component, EventEmitter, Input, Output, ChangeDetectorRef } from '@angular/core';
import NewHabit from './interfaces/new-habit.interface';
import { ApiService } from 'src/app/config/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-habit',
  templateUrl: './new-habit.component.html',
  styleUrls: ['./new-habit.component.css']
})
export class NewHabitComponent {
  @Input() showNewHabit!: boolean;
  @Output() showNewHabitChange = new EventEmitter<boolean>();
  @Output() getHabits = new EventEmitter();
  weekButtons: string[] = [
    'D', 'S', 'T', 'Q', 'Q', 'S', 'S'
  ];

  formData: NewHabit = {
    name: '',
    days: []
  };

  constructor(public api: ApiService, private cd: ChangeDetectorRef) { }

  hideComponent() {
    this.showNewHabitChange.emit(false);
  }

  onSubmit(days: number[]) {
    this.formData.days = days;

    this.api.post('habits', this.formData).subscribe(() => {
      this.hideComponent();
      this.getHabits.emit();
    })
  }

}
