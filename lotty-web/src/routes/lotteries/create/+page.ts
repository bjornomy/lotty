import {getMostUsedSchedules} from '$lib/api/schedule-api'

/** @type {import('./$types').PageLoad} */
export async function load({fetch}: any) {

  return {
    schedules: getMostUsedSchedules(fetch)
  }
}