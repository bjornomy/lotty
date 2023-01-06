<script>
  import "../app.postcss";
</script>

<nav class="mb-5 p-5 bg-amber-100">
  <a class="font-bold px-2" href="/">Home</a>
  <a class="font-bold px-2" href="/lottery/list">Lotteries</a>
  <a class="font-bold px-2" href="/lottery/first">First</a>
  <a class="font-bold px-2" href="/lottery/second">Second</a>
</nav>

<div class="px-4">
  <slot />
</div>

