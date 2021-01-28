import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GameFormComponent } from './game-form/game-form.component';
import { UserScoresComponent } from './user-scores/user-scores.component';
import { TotalScoresComponent } from './total-scores/total-scores.component';
import { GameFormService } from './game-form/game-form.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    GameFormComponent,
    UserScoresComponent,
    TotalScoresComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [GameFormService],
  bootstrap: [AppComponent]
})
export class AppModule { }
