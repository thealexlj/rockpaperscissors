import { Component, OnInit } from '@angular/core';
import { Game } from '../model/game';
import { PlayType } from '../model/play-type'
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
  plays : PlayType[] = [{value: 'Rock', numValue: 0}, {value: 'Paper', numValue: 1}, {value: 'Scissors', numValue: 2}];
  selectedPlay : number = -1;
  ngOnInit(): void {
    
  this.getNewPlayer();

  }

  submitPlayRandom(value: number){
    console.log(value);
    console.log(this.game);
    this.game.player1.playType = "ROCK";
    this.gameFormService.playRandomRound(this.game).subscribe(response => {
      console.log(response);
      this.game = response;
    });
  }

  submitPlayRock(value: number){
    console.log(value);
    this.game.player1.playType = "ROCK";
    this.gameFormService.playRockRound(this.game).subscribe(response => {
      console.log(response);
      this.game = response;
    });
  }

  reset(){
    this.gameFormService.setNewGame(this.game.player1).subscribe(response => {
      this.game = response;
    })
  }

  private getNewPlayer(){
    this.gameFormService.getNewPlayer().subscribe(response => {
      this.player = response;
      this.getNewGame();
    });
  }

  private getNewGame(){
    this.gameFormService.setNewGame(this.player).subscribe(response => {
      this.game = response;
      console.log(this.game);
    });
  }

}
