import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Game } from "../model/game";
import { UserPlayer } from "../model/user-player";

@Injectable()
export class UserScoreService {

    constructor(private readonly http: HttpClient){

    }

    public getAllScores(): Observable<UserPlayer> {
        return this.http.get<UserPlayer>("http://localhost:8080/getAllScores") as Observable<UserPlayer>;
    }

}