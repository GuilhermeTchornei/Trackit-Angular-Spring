import { TestBed } from '@angular/core/testing';

import { TodaysProgressionService } from './todays-progression.service';

describe('TodaysProgressionService', () => {
  let service: TodaysProgressionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TodaysProgressionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
