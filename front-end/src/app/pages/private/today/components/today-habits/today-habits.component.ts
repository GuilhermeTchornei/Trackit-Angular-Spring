import { Component, Input, OnInit } from '@angular/core';
import IHabits from '../../../habits/components/habit/interfaces/habit.interface';
import { ApiService } from 'src/app/config/api.service';
import IHistory from './interface/history.interface';
import { TodaysProgressionService } from 'src/app/services/todays-progression.service';
import { lastValueFrom } from 'rxjs';

@Component({
  selector: 'app-today-habits',
  templateUrl: './today-habits.component.html',
  styleUrls: ['./today-habits.component.css']
})
export class TodayHabitsComponent implements OnInit {
  @Input() habit!: IHabits;
  isClicked = false;

  constructor(private api: ApiService, private TodaysProgression: TodaysProgressionService) { }

  ngOnInit(): void {
    this.api.get<boolean>(`history/${this.habit.id}`).subscribe(response => this.isClicked = response);
  }

  async selectHabit() {
    this.isClicked = !this.isClicked;
    this.habit = await lastValueFrom(this.api.post<IHistory, IHabits>(`history/${this.habit.id}`, { done: this.isClicked }));
    this.TodaysProgression.updateProgression()
  }
}
