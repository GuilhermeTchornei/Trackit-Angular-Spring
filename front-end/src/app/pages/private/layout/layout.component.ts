import { TodaysProgressionService } from './../../../services/todays-progression.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
  imageUrl = "https://images.squarespace-cdn.com/content/v1/606d159a953867291018f801/1619987722169-VV6ZASHHZNRBJW9X0PLK/Key_Art_02_layeredjpg.jpg?format=1500w";
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
