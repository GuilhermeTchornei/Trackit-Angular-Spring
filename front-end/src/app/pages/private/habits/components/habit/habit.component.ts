import { Component, EventEmitter, Input, Output, OnChanges, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-habit',
  templateUrl: './habit.component.html',
  styleUrls: ['./habit.component.css']
})
export class HabitComponent {
  @Input() title!: string;
  @Input() days!: number[];

  weekButtons: string[] = [
    'D', 'S', 'T', 'Q', 'Q', 'S', 'S'
  ];
}
