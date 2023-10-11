import { ApiService } from 'src/app/config/api.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatCalendar, MatCalendarCellClassFunction } from '@angular/material/datepicker';
import { lastValueFrom } from 'rxjs';
import IMonthProgress from './interfaces/monthProgress.interface';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  @ViewChild(MatCalendar) calendar!: MatCalendar<Date>;
  year!: number;
  month!: number;
  monthProgress = new Map<string, number>();

  dateClass: MatCalendarCellClassFunction<Date> = (cellDate, view) => {
    if (view !== "month") return '';
    if (this.year !== cellDate.getFullYear() || this.month !== cellDate.getMonth() + 1) this.onChangeMonth(cellDate);

    if (cellDate.getDate() === new Date().getDate()) return 'today';

    const progress = this.monthProgress.get(cellDate.toISOString().split('T')[0]);

    return progress === undefined ? '' : progress === 1 ? 'all_habits' : progress >= .5 ? 'half_habits' : 'none_habits';
  };

  constructor(private api: ApiService) { }

  ngOnInit(): void {
    this.onChangeMonth(new Date);
  }

  async getMonthProgress() {
    const date = `${this.year}-${this.month.toLocaleString('pt-br', { minimumIntegerDigits: 2 })}`;
    const response: IMonthProgress[] = await lastValueFrom(this.api.get(`progress/${date}`));
    response.forEach(day => this.monthProgress.set(day.date.toString(), day.doneHabits / day.totalHabits));
  }

  async onChangeMonth(date: Date) {
    this.year = date.getFullYear();
    this.month = date.getMonth() + 1;

    await this.getMonthProgress();
    this.calendar && this.calendar.updateTodaysDate();
  }
}
