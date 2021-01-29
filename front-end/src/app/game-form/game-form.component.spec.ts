import { HttpClient, HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { TestBed, async } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { GameFormComponent } from './game-form.component';
import { GameFormService } from './game-form.service';

describe('GameFormComponent', () => {

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GameFormComponent],
      providers: [GameFormService, HttpClient],
      imports: [FormsModule, ReactiveFormsModule, HttpClientModule, HttpClientTestingModule],
      schemas: [ NO_ERRORS_SCHEMA ]
    }).compileComponents();
  });

  it('should create', () => {
    const fixture = TestBed.createComponent(GameFormComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });
});
