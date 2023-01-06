<script lang="ts">
  import Table, {Row, Cell} from '$lib/components/table'

  import type {Lottery} from "$lib/types/lottery";
  import type {Participant, ParticipantId} from "$lib/types/participant";

  export let lottery: Lottery
  let drawingHeaders = ['Winner', 'Price', 'Winnings']

  function getParticipant(participants: Array<Participant>, identifier: ParticipantId): Participant {
    return participants.find(p => p.identifier === identifier)
  }
</script>

<div>
  <table class="mb-2">
    <tr>
      <td>Drawings:</td>
      <td>{lottery.drawResults.length}</td>
    </tr>
  </table>

  <Table headers={drawingHeaders}>
    {#each lottery.drawResults as drawing (drawing.winner)}
      <Row>
        <Cell>{getParticipant(lottery.participants, drawing.winner).name}</Cell>
        <Cell>{drawing.price.price}</Cell>
        <Cell>{drawing.price.description}</Cell>
      </Row>
    {/each}
  </Table>
</div>

