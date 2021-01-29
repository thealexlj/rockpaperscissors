import { Component, OnInit } from '@angular/core';
import { Game } from '../model/game';
import { RoundResult } from '../model/round-result';
import { TotalScoresService } from './total-scores.service';

@Component({
  selector: 'app-total-scores',
  templateUrl: './total-scores.component.html',
  styleUrls: ['./total-scores.component.css']
})
export class TotalScoresComponent implements OnInit {

  games: Game[] = [];
  constructor(private totalScoresService: TotalScoresService) { }

  ngOnInit(): void {
    this.loadScores();
  }

  private loadScores(){
    this.totalScoresService.getAllGames().subscribe(result => {
      this.games = result;
    });
  }

  public getTotalRounds(): number{
    let result: number = 0;
    if(this.games){
      this.games.forEach(game => {
        result += game.rounds.length;
      })
    }
    return result;
  }

  public getTotalWinsPlayer1(){
    let result: number = 0;
    if(this.games){
      this.games.forEach(game => {
        if(game.rounds){
          game.rounds.forEach(round => {
            if(round.roundResult == RoundResult.PLAYER1WINS)
              result++;
          });
        }
      })
    }
    return result;
  }

  public getTotalWinsPlayer2(){
    let result: number = 0;
    if(this.games){
      this.games.forEach(game => {
        if(game.rounds){
          game.rounds.forEach(round => {
            if(round.roundResult == RoundResult.PLAYER2WINS)
              result++;
          });
        }
      })
    }
    return result;
  }

  public getTotalDraws(){
    let result: number = 0;
    if(this.games){
      this.games.forEach(game => {
        if(game.rounds){
          game.rounds.forEach(round => {
            if(round.roundResult == RoundResult.DRAW)
              result++;
          });
        }
      })
    }
    return result;
  }

  public reload(){
    this.loadScores();
  }

}
