<script lang="ts">
  import Table, {Cell, Row} from '$lib/components/table'
  import Card from '$lib/components/Card.svelte'

  /** @type {import('./$types').PageLoad} */
  export let data
  let headers = ['Name', 'Num Participants']
</script>

<Card>
  <Table headers={headers}>
    {#each data.lotteries as lottery (lottery.id)}
      <Row>
        <Cell>
          <a href="/lottery/{lottery.id}">{lottery.name}</a>
        </Cell>
        <Cell>
          {lottery.participants.length}
        </Cell>
      </Row>
    {/each}
  </Table>
</Card>