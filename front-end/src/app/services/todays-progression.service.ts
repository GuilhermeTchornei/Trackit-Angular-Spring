import { Injectable } from '@angular/core';
import { Subject, lastValueFrom } from 'rxjs';
import { ApiService } from '../config/api.service';
import IProgress from '../interfaces/progress.interface';

@Injectable()
export class TodaysProgressionService {
  private progressionSubject = new Subject<number>();
  progression$ = this.progressionSubject.asObservable();
  private progression: number = 0;

  constructor(private api: ApiService) { }

  async updateProgression() {
    const progress: IProgress = await lastValueFrom(this.api.get<IProgress>("progress"));
    this.progression = (progress.doneHabits / progress.totalHabits) * 100;
    this.progressionSubject.next(this.progression);
  }

  getProgression() {
    return this.progression;
  }
}
