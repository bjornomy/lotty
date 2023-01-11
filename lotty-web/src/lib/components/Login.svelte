<script lang="ts">
  import {Button, Card} from 'flowbite-svelte'
  import GithubButton from '$lib/components/login/GithubButton.svelte'
  import GoogleButton from '$lib/components/login/GoogleButton.svelte'

  import AuthProvider, {providers as authProviders} from '$lib/types/auth-provider';

  export let providers: Array<AuthProvider>
  export let overrideHost: String = ''
</script>

<Card size="xl">
  <div class="flex flex-col place-items-center">
    {#each providers as provider}
      {#if !provider.customButton}
        <Button href={overrideHost}{provider.loginUrl} class="w-1/6">{provider.name}</Button>
      {:else}
        {#if provider.name === authProviders.github.name}
          <GithubButton loginUrl="{overrideHost}{provider.loginUrl}" />
        {:else if provider.name === authProviders.google.name}
          <GoogleButton loginUrl="{overrideHost}{provider.loginUrl}" />
        {/if}
      {/if}
    {/each}
  </div>
</Card>
