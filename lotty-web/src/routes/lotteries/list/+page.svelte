<script lang="ts">
  import LotteryList from '$lib/components/LotteryList.svelte'

  /** @type {import('./$types').PageServerLoad} */
  export let data
</script>

<LotteryList {...data} />