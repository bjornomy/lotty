<script lang="ts">
  import {Button, Card, Label, Select} from 'flowbite-svelte'

  import {ScheduleFrequency} from '$lib/types/lotty-enums'
  import type {CreateSchedule} from '$lib/types/lotty';

  /** @type {import('./$types').PageLoad} */
  export let data

  let frequency = data.defaultFrequencyOption.value
  let dayOfWeek = data.defaultDayOfWeekOption.value
  let dayOfMonth = data.defaultDayOfMonthOption.value

  $: selectedFrequency = data.frequencyOptions.find(fo => fo.value === frequency)
  $: selectedDayOfWeek = data.dayOfWeekOptions.find(dow => dow.value === dayOfWeek)
  $: selectedDayOfMonth = data.dayOfMonthOptions.find(dom => dom.value === dayOfMonth)

  const getTarget = () => {
    switch (selectedFrequency.name) {
      case ScheduleFrequency.Monthly: {
        console.log('??:', dayOfMonth)
        return selectedDayOfMonth.name
      }
      default: return selectedDayOfWeek.name
    }
  }

  $: schedule = {
    scheduleFrequency: selectedFrequency.name,
    target: getTarget()
  } as CreateSchedule

</script>

<Card size="xl">
  <form on:submit={data.createSchedule(schedule)} class="flex-col w-2/5 mx-auto">
    <Label for="slc-frequency">Frequency</Label>
    <Select id="slc-frequency" class="mb-4" items={data.frequencyOptions} bind:value={frequency} />

    {#if selectedFrequency.name === ScheduleFrequency.Monthly}
      <Label for="slc-frequency">Day Of Month</Label>
      <Select id="slc-day-of-month" items={data.dayOfMonthOptions} bind:value={dayOfMonth} />
    {:else}
      <Label for="slc-frequency">Day Of Week</Label>
      <Select id="slc-day-of-week" items={data.dayOfWeekOptions} bind:value={dayOfWeek} />
    {/if}

    <div class="flex justify-center">
      <Button type="submit" class="mt-4 w-2/5" color="dark">Create</Button>
    </div>

  </form>
</Card>