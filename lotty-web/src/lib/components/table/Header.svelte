<script lang="ts">
  export let headers: Array<String>
</script>

<thead>
<tr>
  {#each headers as header}
    <th class="px-2">{header}</th>
  {/each}
</tr>
</thead>