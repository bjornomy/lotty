import {createSchedule} from '$lib/api/schedule-api';
import type {CreateSchedule} from '$lib/types/lotty';
import {DayOfWeek, ScheduleFrequency} from "../../../lib/types/lotty-enums";

/** @type {import('./$types').PageLoad} */
export async function load({fetch}: any) {

  const frequencyOptions = Object.keys(ScheduleFrequency).map((sf, i) => {
    return {value: i, name: sf}
  })

  const dayOfWeekOptions = Object.keys(DayOfWeek).map((dow, i) => {
    return {value: i, name: dow}
  })

  const dayOfMonthOptions = [...Array(31).keys()].map(i => {
    return {value: i, name: i + 1}
  })


  return {
    createSchedule: (schedule: CreateSchedule) => createSchedule(fetch, schedule),
    frequencyOptions: frequencyOptions,
    defaultFrequencyOption: frequencyOptions.find(fo => fo.name === ScheduleFrequency.Weekly),
    dayOfWeekOptions: dayOfWeekOptions,
    defaultDayOfWeekOption: dayOfWeekOptions.find(dow => dow.name.toLowerCase() === (DayOfWeek.Monday).toLowerCase()),
    dayOfMonthOptions: dayOfMonthOptions,
    defaultDayOfMonthOption: dayOfMonthOptions.find(dom => dom.name === 1)
  }
}