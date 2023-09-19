import { Component, EventEmitter, Input, Output } from '@angular/core';
import NewHabit from './interfaces/new-habit.interface';
import { ApiService } from 'src/app/config/api.service';

@Component({
  selector: 'app-new-habit',
  templateUrl: './new-habit.component.html',
  styleUrls: ['./new-habit.component.css']
})
export class NewHabitComponent {
  @Input() showNewHabit!: boolean;
  @Output() showNewHabitChange = new EventEmitter<boolean>();
  weekButtons: string[] = [
    'D', 'S', 'T', 'Q', 'Q', 'S', 'S'
  ];

  formData: NewHabit = {
    name: '',
    weekDays: []
  };

  constructor(public api: ApiService) { }

  hideComponent() {
    this.showNewHabitChange.emit(false);
  }

  onSubmit(days: number[]) {
    this.formData.weekDays = days;

    this.api.post('/habits', this.formData).subscribe(() => {
      this.hideComponent();
    })
  }

}
