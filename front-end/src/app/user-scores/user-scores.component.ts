import { Component, OnInit } from '@angular/core';
import { GameFormService } from '../game-form/game-form.service';
import { Game } from '../model/game';
import { RoundResult } from '../model/round-result';
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

  public getRounds(): any[] {
    let rows: any[] = [];
    this.game.rounds.forEach(round =>{
      rows.push(
        {
          play1 : round.play1.playType, 
          play2: round.play2.playType,
          result: round.roundResult == RoundResult.PLAYER1WINS? 'Player 1 wins' : round.roundResult == RoundResult.DRAW? 'Draw' : 'Player 2 wins'
        });
    });
    return rows;
  }

}
