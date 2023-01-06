import type {Participant} from '$lib/types/participant'
import type {Drawing} from '$lib/types/drawing'

export class Lottery {
    name: String
    id: String
    participants: Array<Participant>
    drawResults: Array<Drawing>


    constructor(name: String, id: String, participants: Array<Participant>, drawResults: Array<Drawing>) {
        this.name = name;
        this.id = id;
        this.participants = participants;
        this.drawResults = drawResults;
    }
}