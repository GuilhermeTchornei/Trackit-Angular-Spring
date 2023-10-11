import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ApiService } from 'src/app/config/api.service';
import IHabits from './components/habit/interfaces/habit.interface';
import { lastValueFrom } from 'rxjs';

@Component({
  selector: 'app-habits',
  templateUrl: './habits.component.html',
  styleUrls: ['./habits.component.css']
})
export class HabitsComponent implements OnInit {
  showNewHabit = false;
  habits!: IHabits[];

  constructor(private api: ApiService, private cd: ChangeDetectorRef) { }

  async ngOnInit() {
    await this.getHabits();
  }

  async getHabits() {
    const response = await lastValueFrom(this.api.get<IHabits[]>("habits"));
    this.habits = response;
    this.cd.detectChanges();
  }
}
