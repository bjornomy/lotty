import type {ParticipantId} from '$lib/types/participant'
import type {Price} from '$lib/types/price'

export class Drawing {
  winner: ParticipantId
  price: Price
  drawn: Date

  constructor(winner: ParticipantId, price: Price, drawn: Date) {
    this.winner = winner;
    this.price = price;
    this.drawn = drawn;
  }

}