<script lang="ts">
  import Header from '$lib/components/table/Header.svelte'

  export let headers: Array<String>
  export let title: String | undefined = undefined
</script>

<div class="relative overflow-x-auto shadow-md sm:rounded-lg">
  <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
    {#if title}
      <caption class="p-5 text-lg font-semibold text-left text-gray-900 bg-white dark:text-white dark:bg-gray-800">
        {title}
      </caption>
    {/if}

    <Header {headers} />

    <tbody>
     <slot />
    </tbody>
  </table>
</div>