import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrimaryInputComponent } from './primary-input.component';

describe('PrimaryInputComponent', () => {
  let component: PrimaryInputComponent;
  let fixture: ComponentFixture<PrimaryInputComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PrimaryInputComponent]
    });
    fixture = TestBed.createComponent(PrimaryInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
