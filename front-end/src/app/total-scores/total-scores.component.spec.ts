import { HttpClient, HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { TestBed, async } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TotalScoresComponent } from './total-scores.component';
import { TotalScoresService } from './total-scores.service';

describe('TotalScoresComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TotalScoresComponent],
      providers: [TotalScoresService, HttpClient],
      imports: [FormsModule, ReactiveFormsModule, HttpClientModule, HttpClientTestingModule],
      schemas: [ NO_ERRORS_SCHEMA ]
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(TotalScoresComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });
});
