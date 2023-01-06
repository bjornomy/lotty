<script lang="ts">
  import Card from '$lib/components/Card.svelte'
  import Input from '$lib/components/form/Input.svelte'

  /** @type {import('./$types').PageLoad} */
    //export let data
  let lotteryName: String

  function createLottery() {
    console.log(lotteryName)
  }
</script>

<Card>
  <form on:submit={createLottery}>
    <Input
      extraClasses="mb-4"
      id="txt-lottery-name"
      type="text"
      placeholder="Name"
      bind:value={lotteryName}
    />
    <br />
    <Input
      id="btn-lottery-create"
      type="submit"
      value="Create"
    />
  </form>
</Card>