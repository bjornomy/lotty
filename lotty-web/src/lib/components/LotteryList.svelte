<script lang="ts">
  import { Table, TableBody, TableBodyCell, TableBodyRow, TableHead, TableHeadCell } from 'flowbite-svelte';

  import type {Lottery} from '$lib/types/lottery.ts'

  export let lotteries: Array<Lottery>
  const lotteryListHeaders = ['Name', 'Num Participants']
</script>

<Table striped={true} class={$$props.class}>
  <caption class="p-5 text-lg font-semibold text-left text-gray-900 bg-white dark:text-white dark:bg-gray-800">
    Your Recent Lotteries
  </caption>
  <TableHead>
    {#each lotteryListHeaders as header}
      <TableHeadCell>{header}</TableHeadCell>
    {/each}
  </TableHead>

  <TableBody>
    {#each lotteries as lottery (lottery.id)}
      <TableBodyRow>
        <TableBodyCell>
          <a href="/lotteries/{lottery.id}">{lottery.name}</a>
        </TableBodyCell>
        <TableBodyCell>
          {lottery.participants.length}
        </TableBodyCell>
      </TableBodyRow>
    {/each}
  </TableBody>
</Table>