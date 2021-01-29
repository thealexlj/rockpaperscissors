import { HttpClient, HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { TestBed, async } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { GameFormService } from '../game-form/game-form.service';
import { UserScoresComponent } from './user-scores.component';

describe('UserScoresComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [UserScoresComponent],
      providers: [GameFormService, HttpClient],
      imports: [FormsModule, ReactiveFormsModule, HttpClientModule, HttpClientTestingModule],
      schemas: [ NO_ERRORS_SCHEMA ]
    }).compileComponents();
  }));

  it('should create', () => {
    const fixture = TestBed.createComponent(UserScoresComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });
});
