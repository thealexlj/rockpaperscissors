import { NO_ERRORS_SCHEMA } from '@angular/core';
import { TestBed, async } from '@angular/core/testing';
import { UserScoresComponent } from './user-scores.component';

describe('UserScoresComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [UserScoresComponent],
      schemas: [ NO_ERRORS_SCHEMA ]
    }).compileComponents();
  }));

  it('should create', () => {
    const fixture = TestBed.createComponent(UserScoresComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });
});
