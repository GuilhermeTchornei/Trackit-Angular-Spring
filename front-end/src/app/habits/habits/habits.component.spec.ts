import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HabitsComponent } from './habits.component';

describe('HabitsComponent', () => {
  let component: HabitsComponent;
  let fixture: ComponentFixture<HabitsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HabitsComponent]
    });
    fixture = TestBed.createComponent(HabitsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
