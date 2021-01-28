import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Game } from "../model/game";
import { UserPlayer } from "../model/user-player";

@Injectable()
export class GameFormService{

    constructor(private readonly http: HttpClient){

    }

    public getNewPlayer(): Observable<UserPlayer> {
        return this.http.get<UserPlayer>("http://localhost:8080/getNewPlayer") as Observable<UserPlayer>;
    }

    public playRandomRound(game: Game): Observable<Game> {
        return this.http.post<Game>("http://localhost:8080/playRandomRound", game) as Observable<Game>;
    }

    public playRockRound(game: Game): Observable<Game> {
        return this.http.post<Game>("http://localhost:8080/playRockRound", game) as Observable<Game>;
    }

    public setNewGame(player : UserPlayer): Observable<Game> {
        return this.http.post<Game>("http://localhost:8080/getNewGame", player) as Observable<Game>;
    }

}