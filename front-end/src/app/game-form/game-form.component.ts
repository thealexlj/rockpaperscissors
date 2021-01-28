import { Component, OnInit } from '@angular/core';
import { Game } from '../model/game';
import { PlayType } from '../model/play-type'
import { RoundResult } from '../model/round-result';
import { UserPlayer } from '../model/user-player';
import { GameFormService } from './game-form.service';

@Component({
  selector: 'app-game-form',
  templateUrl: './game-form.component.html',
  styleUrls: ['./game-form.component.css']
})
export class GameFormComponent implements OnInit {

  constructor(private gameFormService : GameFormService) { }

  game: Game = {} as Game;
  player: UserPlayer = {} as UserPlayer;
  plays : PlayType[] = [{value: 'ROCK'}, {value: 'PAPER'}, {value: 'SCISSORS'}];
  selectedPlay : string = '';

  ngOnInit(): void {
    this.getGame();
  }

  submitPlayRandom(value: string){
    this.game.player1.playType = value;
    this.gameFormService.requestRandomRound(this.game);
  }

  submitPlayRock(value: string){
    this.game.player1.playType = value;
    this.gameFormService.requestRockRound(this.game);
  }

  reset(){
    this.gameFormService.requestNewGame(this.player);
  }

  private getGame(){
    this.gameFormService.getGame().subscribe(g => {
      this.game = g;
      this.player = g.player1;
    });
  }

  public getRoundResult(): string {
    let roundResult = {} as RoundResult;
    let message = '';
    
    if(this.game.rounds && this.game.rounds.length > 0){
      roundResult = this.game.rounds[this.game.rounds.length-1].roundResult;
      if(roundResult == RoundResult.PLAYER1WINS) {
        message = 'YOU WON!!';
      } else if (roundResult == RoundResult.DRAW) {
        message = 'DRAW!!';
      } else {
        message = 'YOU LOSE!!';
      }
    }
      
    return message;
  }

  public getOpponentPlay(): string {
    let roundResult = '';

    if(this.game.rounds && this.game.rounds.length > 0) {
      roundResult = 'Received: ' + this.game.rounds[this.game.rounds.length-1].play2.playType;
    }

    return roundResult;
  }

}
