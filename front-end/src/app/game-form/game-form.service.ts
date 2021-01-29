import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable, Subject } from "rxjs";
import { Game } from "../model/game";
import { UserPlayer } from "../model/user-player";

@Injectable()
export class GameFormService{

    constructor(private readonly http: HttpClient){
        this.loadNewPlayer();
    }

    currentGame: Game = {} as Game;
    currentPlayer: UserPlayer = {} as UserPlayer;
    public currentGame$: Subject<Game> = new Subject();

    private loadNewPlayer() {
        this.http.get<UserPlayer>("http://localhost:8080/getNewPlayer").subscribe(
            response => {
                this.currentPlayer = response;
                this.loadNewGame(this.currentPlayer);
            }
        );
    }

    private loadNewGame(player: UserPlayer) {
        this.http.post<Game>("http://localhost:8080/getNewGame", player).subscribe(
            response => {
                this.currentGame = response;
                this.announceNewGame();
            }
        );
    }

    private playRandomRound(game: Game) {
        this.http.post<Game>("http://localhost:8080/playRandomRound", game).subscribe(
            response => {
                this.currentGame = response;
                this.announceNewGame();
            }
        );
    }

    private playRockRound(game: Game) {
        this.http.post<Game>("http://localhost:8080/playRockRound", game).subscribe(
            response => {
                this.currentGame = response;
                this.announceNewGame();
            }
        );
    }

    private announceNewGame() {
        this.currentGame$.next(this.currentGame);
    }

    public getGame() : Observable<Game> {
        return this.currentGame$.asObservable();
    }

    public requestNewGame(player: UserPlayer) {
        this.loadNewGame(player);
    }

    public requestRandomRound(game: Game){
        this.playRandomRound(game);
    }

    public requestRockRound(game: Game){
        this.playRockRound(game);
    }

}