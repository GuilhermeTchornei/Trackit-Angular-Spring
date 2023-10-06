import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodayHabitsComponent } from './today-habits.component';

describe('TodayHabitsComponent', () => {
  let component: TodayHabitsComponent;
  let fixture: ComponentFixture<TodayHabitsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TodayHabitsComponent]
    });
    fixture = TestBed.createComponent(TodayHabitsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
