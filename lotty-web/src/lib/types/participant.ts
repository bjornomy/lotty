
export type ParticipantId = String

export class Participant {
    name: String
    identifier: ParticipantId
    wins: Number
    participations: Number
    participatedSince: Date


    constructor(name: String, identifier: ParticipantId, wins: Number, participations: Number, participatedSince: Date) {
        this.name = name;
        this.identifier = identifier;
        this.wins = wins;
        this.participations = participations;
        this.participatedSince = participatedSince;
    }
}