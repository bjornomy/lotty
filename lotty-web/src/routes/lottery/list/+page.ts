import {lotteries} from '$lib/api/data'

/** @type {import('./$types').PageLoad} */
export async function load() {

  //const lots = await fetch(`/api/lotteries`)

  return {
    lotteries: lotteries
  }
}