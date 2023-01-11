<script lang="ts">
  import Table, {Cell, Row} from '$lib/components/table'
  import Card from '$lib/components/Card.svelte'
  import LotteryStats from '$lib/components/LotteryStats.svelte';

  /** @type {import('./$types').PageLoad} */
  export let data

  let participantTableHeaders = ['Name', 'Wins', 'Participations', 'Participated Since'] as Array<String>;
</script>

<Card title={data.name}>
  <div class="grid grid-cols-3">
    <Card title="Stats">
      <LotteryStats lottery={data} />
    </Card>
    <Card title="Participants" extraClasses="col-span-2">
      <Table headers={participantTableHeaders}>
        {#each data.participants as participant (participant.name)}
          <Row>
            <Cell width="w-full">{participant.name}</Cell>
            <Cell>{participant.wins}</Cell>
            <Cell>{participant.participations}</Cell>
            <Cell>{participant.participatedSince.toISOString()}</Cell>
          </Row>
        {/each}
      </Table>
    </Card>
  </div>
</Card>