import type {Lottery} from '$lib/types/lottery';
import type {Drawing} from '$lib/types/drawing';
import type {Participant} from '$lib/types/participant';
import {Price, Prices} from '$lib/types/price';

export const lotteries = [
  {
    name: 'First Lottery',
    id: 'first',
    participants: [
      {
        name: 'Petter',
        identifier: '1',
        participations: 10,
        wins: 1,
        participatedSince: new Date()
      }
    ] as Array<Participant>,
    drawResults: [
      {
        winner: '1',
        price: {
          price: Prices.First,
          description: 'Wine'
        } as Price,
        drawn: new Date()
      }
    ] as Array<Drawing>
  },
  {
    name: 'Second Lottery',
    id: 'second',
    participants: [
      {
        name: 'Arne',
        identifier: '2',
        participations: 8,
        wins: 1,
        participatedSince: new Date()
      }
    ] as Array<Participant>,
    drawResults: [
      {
        winner: '1',
        price: {
          price: Prices.First,
          description: 'Wine'
        } as Price,
        drawn: new Date()
      }
    ] as Array<Drawing>
  }
] as Array<Lottery>