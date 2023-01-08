<script lang="ts">
  export let id: String
  export let href: String
  export let label: String

  let isActive = false

  $: activeClass = isActive ? 'bg-gray-100' : ''
</script>

<!--
   on:mouseenter={isActive = true}
   on:mouseleave={isActive = false}
-->

<a {href} {id}
   class="block px-4 py-2 text-sm text-gray-700" role="menuitem" tabindex="-1"
   on:focusin={() => isActive = true} on:focusout={() => isActive = false}
>
  {label}
</a>