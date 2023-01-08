<script lang="ts">
  export let headers: Array<String>
</script>

<thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
<tr>
  {#each headers as header}
    <th scope="col" class="px-6 py-3">{header}</th>
  {/each}
</tr>
</thead>