import { NO_ERRORS_SCHEMA } from "@angular/core";
import { async, TestBed } from "@angular/core/testing";
import { GameFormService } from "./game-form.service";

describe('GameFormService', () => {
    beforeEach(async(() => {
      TestBed.configureTestingModule({
        declarations: [GameFormService],
        schemas: [ NO_ERRORS_SCHEMA ]
      }).compileComponents();
    }));
  
    it('should create the app', () => {
      
    });
  });
  