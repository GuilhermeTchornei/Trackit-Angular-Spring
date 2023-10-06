import { formatDate } from '@angular/common';
import { Component } from '@angular/core';
import { ApiService } from 'src/app/config/api.service';
import IHabits from '../habits/components/habit/interfaces/habit.interface';
import { TodaysProgressionService } from 'src/app/services/todays-progression.service';

@Component({
  selector: 'app-today',
  templateUrl: './today.component.html',
  styleUrls: ['./today.component.css']
})
export class TodayComponent {
  habits!: IHabits[];
  currentDate: string = new Date().toISOString();
  daysOfWeek: string = formatDate(this.currentDate, 'EEEE', 'pt-BR').split('-')[0];
  date: string = this.daysOfWeek.charAt(0).toUpperCase() +
    this.daysOfWeek.slice(1) + ', ' +
    formatDate(this.currentDate, 'dd/MM', 'pt-BR');

  constructor(private api: ApiService, private todaysProgression: TodaysProgressionService) { }

  ngOnInit() {
    this.getHabits();
  }

  isAnyHabitDone() {
    return this.todaysProgression.getProgression() > 0;
  }

  getHabits() {
    this.api.get<IHabits[]>("habits/today").subscribe(response => {
      this.habits = response;
    });
  }
}
