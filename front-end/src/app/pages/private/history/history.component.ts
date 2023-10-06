import { ApiService } from 'src/app/config/api.service';
import { Component, OnInit, ChangeDetectorRef, ViewChild } from '@angular/core';
import { MatCalendar, MatCalendarCellClassFunction } from '@angular/material/datepicker';
import { lastValueFrom } from 'rxjs';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  @ViewChild(MatCalendar) calendar!: MatCalendar<Date>;
  selected!: Date | null;
  year!: number;
  month!: number;
  monthProgress = new Map<string, number>();

  dateClass: MatCalendarCellClassFunction<Date> = (cellDate, view) => {
    if (view !== "month") return '';
    if (this.year !== cellDate.getFullYear() || this.month !== cellDate.getMonth() + 1) this.onChangeMonth(cellDate);

    const date = cellDate.getDate();
    if (!this.monthProgress) return '';
    if (date === new Date().getDate()) return 'today';

    return this.monthProgress.get(cellDate.toISOString().split('T')[0]) ? 'all_habits' : '';
  };

  constructor(private api: ApiService) { }

  ngOnInit(): void {
    this.onChangeMonth(new Date);
  }

  async getMonthProgress() {
    const date = `${this.year}-${this.month.toLocaleString('pt-br', { minimumIntegerDigits: 2 })}`;
    const response: { date: Date, doneHabits: number, totalHabits: number }[] = await lastValueFrom(this.api.get(`progress/${date}`));
    response.forEach(month => this.monthProgress.set(month.date.toString(), month.doneHabits / month.totalHabits));
  }

  async onChangeMonth(date: Date) {
    this.year = date.getFullYear();
    this.month = date.getMonth() + 1;

    await this.getMonthProgress();
    this.calendar && this.calendar.updateTodaysDate();
  }
}
