<script lang="ts">
  import Login from '$lib/components/Login.svelte'

  /** @type {import('./$types').ServerPageLoad} */
  export let data
</script>

<Login {...data} />
