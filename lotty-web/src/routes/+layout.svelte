<script>
  import "../app.postcss";
  import Input from '$lib/components/form/Input.svelte'

  /** @type {import('./$types').LayoutServerLoad} */
  export let data
</script>

<div class="mb-5 p-5 bg-amber-100 flex flex-row justify-between leading-8">
  <nav>
    <a class="font-bold px-2" href="/">Home</a>
    <a class="font-bold px-2" href="/lottery/list">Lotteries</a>
    <a class="font-bold px-2" href="/lottery/first">First</a>
    <a class="font-bold px-2" href="/lottery/second">Second</a>
  </nav>

  {#if data.user}
    <div class="flex flex-row">
      <div class="shadow-md rounded-md px-2 bg-slate-300 mr-2">
        {data.user.name}
      </div>
      <a class="shadow-md rounded-md px-2 bg-slate-300" href="http://localhost:10000/logout">Log Out</a>
    </div>
  {/if}
</div>

<div class="px-4">
  <slot />
</div>

