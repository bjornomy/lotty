<script lang="ts">
  import LotteryList from '$lib/components/LotteryList.svelte'

  import {Card, Button} from 'flowbite-svelte'

  export let data
</script>

<Card size="xl">
  <div>
    <Button href="/lotteries/create" color="dark">Create New Lottery</Button>
    <Button href="/schedules/create" color="dark">Create New Schedule</Button>
    <Button href="/lotteries/list" color="dark">Lotteries you've attended</Button>
  </div>

  <LotteryList {...data} class="mt-4" />
</Card>