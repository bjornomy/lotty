<script lang="ts">
  import {NavButtonType} from "$lib/components/nav-button";

  export let href: String
  export let label: String
  export let current: boolean = false
  export let type: NavButtonType = NavButtonType.Button

  $: typeColors = getTypeColors()

  const getTypeColors = () => {
    switch (type) {
      case NavButtonType.Header: return 'text-gray-300 hover:bg-gray-700 hover:text-white'
      case NavButtonType.Button: return 'bg-gray-700 text-gray-100 hover:bg-gray-900 text-white'
    }
  }
</script>

{#if current && type === NavButtonType.Header}
  <a href={href} class="bg-gray-900 text-white px-3 py-2 rounded-md text-sm font-medium" aria-current="page">{label}</a>
{:else }
  <a href={href} class="{typeColors} px-3 py-2 rounded-md text-sm font-medium">{label}</a>
{/if}