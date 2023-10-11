import { TodaysProgressionService } from './../../../services/todays-progression.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
  imageUrl = localStorage.getItem("photo");
  progression!: number;

  constructor(private router: Router, private todaysProgression: TodaysProgressionService) { }

  ngOnInit(): void {
    this.todaysProgression.updateProgression();
    this.todaysProgression.progression$.subscribe((data) => this.progression = data)
  }

  goToHoje() {
    this.router.navigate(['hoje']);
  }

}
