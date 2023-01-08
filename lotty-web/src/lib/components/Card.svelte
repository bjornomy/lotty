<script lang="ts">
  export let extraClasses: String = ''
  export let title: String | undefined = undefined

</script>

<div class="p-2 max-w-sm w-full lg:max-w-full shadow-lg rounded-md bg-white {extraClasses}">
  {#if title}
    <h1 class="p-2 mb-2 font-bold text-lg">{title}</h1>
  {/if}
  <slot/>
</div>