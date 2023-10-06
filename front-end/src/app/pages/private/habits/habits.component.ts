import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ApiService } from 'src/app/config/api.service';
import IHabits from './components/habit/interfaces/habit.interface';

@Component({
  selector: 'app-habits',
  templateUrl: './habits.component.html',
  styleUrls: ['./habits.component.css']
})
export class HabitsComponent implements OnInit {
  showNewHabit = false;
  habits!: IHabits[];

  constructor(private api: ApiService, private cd: ChangeDetectorRef) { }

  ngOnInit() {
    if (!this.habits)
      this.getHabits();
  }

  getHabits() {
    this.api.get<IHabits[]>("habits").subscribe(response => {
      this.habits = response;
      this.cd.detectChanges();
    });
  }
}
