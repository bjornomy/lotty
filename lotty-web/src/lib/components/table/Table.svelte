<script lang="ts">
  import Header from '$lib/components/table/Header.svelte'

  export let headers: Array<String>
  export let extraClasses: String = ''
  export let title: String | undefined = undefined
</script>

{#if title}
<div class="p-2 mb-2 font-bold text-lg">{title}</div>
{/if}
<table class="w-full {extraClasses}">
  <Header {headers} />
  <tbody>
    <slot/>
  </tbody>
</table>