<script lang="ts">
  import type {LinkData} from '$lib/types/link-data';
  import {elasticIn, elasticOut} from 'svelte/easing';

  import UserDropdownLink from '$lib/components/user-dropdown/UserDropdownLink.svelte'

  export let show: boolean = false
  export let links: Array<LinkData>

  /**
   * Entering: "transition ease-out duration-100"
   *   From: "transform opacity-0 scale-95"
   *   To: "transform opacity-100 scale-100"
   */
  function easeIn(node, params) {
    const existingTransform = getComputedStyle(node).transform.replace('none', '');

    return {
      duration: 100,
      easing: elasticIn,
      css: (t, u) => `transform: ${existingTransform} scale(${(t * 0.05) + 0.95}) opacity(${t * 100})`
    };
  }

  /**
   * Leaving: "transition ease-in duration-75"
   *   From: "transform opacity-100 scale-100"
   *   To: "transform opacity-0 scale-95"
   */
  function easeOut(node, params) {
    const existingTransform = getComputedStyle(node).transform.replace('none', '');

    return {
      duration: 75,
      easing: elasticOut,
      css: (t, u) => `transform: ${existingTransform} scale(${(u * 0.05) + 0.95}) opacity(${u * 100})`
    };
  }

</script>

{#if show}
  <div
    class="absolute right-0 z-10 mt-2 w-48 origin-top-right rounded-md bg-white py-1 shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none"
    role="menu" aria-orientation="vertical" aria-labelledby="user-menu-button" tabindex="-1"
    in:easeIn out:easeOut
  >
    {#each links as linkData, i (linkData.href)}
      <UserDropdownLink {...linkData} id="user-menu-item-{i}" />
    {/each}
  </div>
{/if}