import { Play } from "./play";
import { RoundResult } from "./round-result";

export interface Round {
    play1: Play;
    play2: Play;
    roundResult: RoundResult;
}
