import { NO_ERRORS_SCHEMA } from '@angular/core';
import { TestBed, async } from '@angular/core/testing';
import { TotalScoresComponent } from './total-scores.component';

describe('TotalScoresComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TotalScoresComponent],
      schemas: [ NO_ERRORS_SCHEMA ]
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(TotalScoresComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });
});
