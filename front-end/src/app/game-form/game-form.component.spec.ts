import { NO_ERRORS_SCHEMA } from '@angular/core';
import { TestBed, async } from '@angular/core/testing';
import { GameFormComponent } from './game-form.component';
import { GameFormService } from './game-form.service';

describe('GameFormComponent', () => {

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [GameFormComponent, GameFormService],
      schemas: [ NO_ERRORS_SCHEMA ]
    }).compileComponents();
  }));

  it('should create', () => {
    const fixture = TestBed.createComponent(GameFormComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });
});
