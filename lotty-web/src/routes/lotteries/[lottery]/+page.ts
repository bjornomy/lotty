import {lotteries} from '$lib/api/data'

/** @type {import('./$types').PageLoad} */
export function load({ params }: any) {
    return lotteries.find(element => element.id === params.lottery)
}