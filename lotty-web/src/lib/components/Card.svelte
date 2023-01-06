<script lang="ts">
  export let extraClasses: String = ''
  export let title: String | undefined = undefined
</script>

<div class="mx-2 p-2 shadow rounded {extraClasses}">
  {#if title}
    <div class="p-2 mb-2 font-bold text-lg">{title}</div>
  {/if}
  <slot/>
</div>