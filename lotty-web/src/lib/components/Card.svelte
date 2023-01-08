<script lang="ts">
  export let extraClasses: String = ''
  export let title: String | undefined = undefined

</script>

<div class="p-4 bg-white border border-gray-200 rounded-lg shadow-md dark:bg-gray-800 dark:border-gray-700 {extraClasses}">
  {#if title}
  <div class="px-4 border-b">
    <h3 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">{title}</h3>
  </div>
  {/if}
  <slot />
</div>
