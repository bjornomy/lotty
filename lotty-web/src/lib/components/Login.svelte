<script lang="ts">
  import Card from '$lib/components/Card.svelte'

  export let providers: Array<String>
</script>

<div class="grid grid-cols-5">
  <div />
  <Card title="Login / Register" extraClasses="col-span-3">
    <div class="flex flex-col place-items-center">
      {#each providers as provider}
        <a
          class="mb-3 p-1 rounded-md shadow bg-slate-300 inline-block w-3/4 text-center"
          href="http://localhost:10000/oauth/login/{provider.toLowerCase()}">
          {provider}
        </a>
      {/each}
    </div>
  </Card>
  <div />
</div>
