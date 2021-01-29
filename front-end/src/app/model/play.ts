import { PlayType } from "./play-type";
import { UserPlayer } from "./user-player";

export interface Play {
    player: UserPlayer;
    playType: String;
}