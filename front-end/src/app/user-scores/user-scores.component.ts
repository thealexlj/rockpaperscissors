import { Component, OnInit } from '@angular/core';
import { GameFormService } from '../game-form/game-form.service';
import { Game } from '../model/game';
import { Round } from '../model/round';
import { UserPlayer } from '../model/user-player';

@Component({
  selector: 'app-user-scores',
  templateUrl: './user-scores.component.html',
  styleUrls: ['./user-scores.component.css']
})
export class UserScoresComponent implements OnInit {

  game: Game = {} as Game;
  player: UserPlayer = {} as UserPlayer;

  constructor(private gameFormService : GameFormService) { }
  
  ngOnInit(): void {
    this.getGame();
  }

  private getGame(){
    this.gameFormService.getGame().subscribe(g => {
      this.game = g;
      this.player = g.player1;
    });
  }

  public getRounds(): Round[] {
    return this.game.rounds;
  }

}
