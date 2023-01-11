<script lang="ts">
  import {Button, Card, Input, Select} from 'flowbite-svelte'

  /** @type {import('./$types').PageLoad} */
  export let data

  let lotteryName: String
  let schedule = 1

  $: scheduleOptions = data.schedules.map(s => {
    return {value: s.id, name: s.description}
  })
</script>

<Card size="xl">
  <form class="flex flex-col w-3/5 mx-auto">
    <Input placeholder="Name" class="mb-6" bind:value={lotteryName} />
    <div class="flex mb-6">
      <Select class="mr-4" items={scheduleOptions} bind:value={schedule} />
      <Button class="!p-2" pill={true} color="dark">
        <svg xmlns="http://www.w3.org/2000/svg"  class="w-6 h-6" fill="none" viewBox="0 0 24 24" stroke-width={1.5} stroke="currentColor">
          <path stroke-linecap="round" stroke-linejoin="round" d="M12 4.5v15m7.5-7.5h-15" />
        </svg>
      </Button>
    </div>
    <Button type="submit" color="dark" class="w-2/5 mx-auto">Create</Button>
  </form>
</Card>