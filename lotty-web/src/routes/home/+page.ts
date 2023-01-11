import {getLastLotteries} from '$lib/api/lottery-api'

/** @type {import('./$types').PageLoad} */
export async function load({fetch}: any) {

  return {
    lotteries: getLastLotteries({fetch})
  }
}