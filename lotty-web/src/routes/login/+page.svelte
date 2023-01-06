<script lang="ts">
  import Login from '$lib/components/Login.svelte'

  export let data
</script>

<Login providers={data.providers} />


