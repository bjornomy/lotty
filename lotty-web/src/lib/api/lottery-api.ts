import {lotteries} from '$lib/api/data'

export const getLastLotteries = async ({fetch}: any) => {
  console.log(fetch)
  const pr = await fetch(`api/lotteries`)
  return await pr.json() || lotteries
}