import {schedules} from '$lib/api/data'
import type {CreateSchedule} from '$lib/types/lotty';

export const getMostUsedSchedules = async (fetch: any) => {
  const pr = await fetch(`/api/schedules`)
  return await pr.json() || schedules
}

export const createSchedule = async (fetch: any, schedule: CreateSchedule) => {
  console.log(schedule)
  const pr = await fetch(`/api/schedules`, {method: 'POST', body: JSON.stringify(schedule)})
  console.log(await pr.text())
  return await pr.json() || schedules
}