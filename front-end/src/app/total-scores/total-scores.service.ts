import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Game } from "../model/game";

@Injectable()
export class TotalScoresService {

    constructor(private readonly http: HttpClient){
        
    }

    public getAllGames() : Observable<Game[]> {
        return this.http.get<Game[]>("http://localhost:8080/getAllScores");
    }

}