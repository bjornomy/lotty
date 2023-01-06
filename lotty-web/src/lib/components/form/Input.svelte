<script lang="ts">
  export let type: String
  export let id: String

  export let extraClasses: String = ''
  export let label: String = null
  export let value: String = null
  export let placeholder: String = null

  $: inputClasses = `p-2 h-8 rounded-md shadow focus:outline-slate-300 focus:outline-1 leading-3 ${extraClasses}`

</script>

{#if label !== null}
  <label for={id} class="mr-2">{label}</label>
{/if}
{#if type === 'submit' || type === 'button'}
  <input
    class="cursor-pointer {inputClasses}"
    {id}
    {type}
    {placeholder}
    {value}
  />
{:else if type === 'text'}
  <input
    class={inputClasses}
    type="text"
    bind:value
    {id}
    {placeholder}
  />
{/if}
