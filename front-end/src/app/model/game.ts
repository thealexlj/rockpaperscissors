import { UserPlayer } from "./user-player";
import { Round } from "./round";

export interface Game {
    id: string;
    player1: UserPlayer;
    player2: UserPlayer;
    rounds: Round[];
}