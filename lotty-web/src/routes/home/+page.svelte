<script lang="ts">
  import LotteryList from '$lib/components/LotteryList.svelte'

  import {Card, Button} from 'flowbite-svelte'

  export let data
</script>

<Card size="xl">
  <Button href="/lotteries/new" color="dark" class="w-1/5">Create New Lottery</Button>

  <LotteryList {...data} class="mt-4" />
</Card>