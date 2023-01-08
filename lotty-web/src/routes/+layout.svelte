<script>
  import '../app.postcss'
  import UserDropdownMenu from '$lib/components/user-dropdown/UserDropdownMenu.svelte'
  import {NavButtonType} from '$lib/components/nav-button/nav-button-type'
  import NavButton from '$lib/components/nav-button/NavButton.svelte'

  /** @type {import('./$types').LayoutServerLoad} */
  export let data

  let userDropDownOpen = false

</script>

<!-- md:hidden hides the element at sizes equal or bigger than the configured size for `md` -->

<!-- main container -->
<div class="min-h-full">

  <!-- App nav bar -->
  <nav class="bg-gray-800">
    <div class="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
      <div class="flex h-16 items-center justify-between">

        <!-- logo and main nav -->
        <div class="flex items-center">

          <!-- Logo -->
          <div class="flex-shrink-0">
            <img class="h-8 w-8" src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=500"
                 alt="Your Company">
          </div>

          <!-- Page 'buttons' in the nav -->
          <div class="hidden md:block">
            <div class="ml-10 flex items-baseline space-x-4">
              {#each data.navs.page as link (link.href)}
                <NavButton current={data.route === link.href} {...link} type={NavButtonType.Header} />
              {/each}
            </div>
          </div>
        </div>

        <!-- User info and picture with drop down menu -->
        {#if data.user}
        <div class="hidden md:block">
          <div class="ml-4 flex items-center md:ml-6">

            <!-- Profile dropdown -->
            <div class="relative ml-3">
              <div>
                <button type="button"
                        class="flex max-w-xs items-center rounded-full bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800"
                        id="user-menu-button"
                        aria-haspopup="true"
                        aria-expanded={userDropDownOpen.toString()}
                        on:click={() => userDropDownOpen = !userDropDownOpen}
                        on:focusout={() => userDropDownOpen = false}
                >
                  <span class="sr-only">Open user menu</span>
                  <img class="h-8 w-8 rounded-full" src={data.user.picture} alt="">
                </button>
              </div>

              <UserDropdownMenu show={userDropDownOpen} links={data.navs.user} />

            </div>
          </div>
        </div>

        <!-- Button to open/close the mobile menu -->
        <div class="-mr-2 flex md:hidden">
          <button type="button"
                  class="inline-flex items-center justify-center rounded-md bg-gray-800 p-2 text-gray-400 hover:bg-gray-700 hover:text-white focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800"
                  aria-controls="mobile-menu"
                  aria-expanded={userDropDownOpen.toString()}
                  on:click={userDropDownOpen = !userDropDownOpen}
          >
            <span class="sr-only">Open user menu</span>
            {#if userDropDownOpen}
              <svg class="block h-6 w-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                   stroke-width="1.5" stroke="currentColor" aria-hidden="true">
                <path stroke-linecap="round" stroke-linejoin="round" d="M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5" />
              </svg>
            {:else}
              <svg class="hidden h-6 w-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                   stroke-width="1.5" stroke="currentColor" aria-hidden="true">
                <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
              </svg>
            {/if}
          </button>
        </div>
        {/if}

      </div>
    </div>


    <!-- Mobile menu, show/hide based on menu state. -->
    {#if userDropDownOpen}
      <div class="md:hidden" id="mobile-menu">

        <!-- Page Nav -->
        <div class="space-y-1 px-2 pt-2 pb-3 sm:px-3">
          {#each data.navs.page as page (page.href)}
            {#if data.route === page.href}
              <a href={page.href} class="bg-gray-900 text-white block px-3 py-2 rounded-md text-base font-medium"
                 aria-current="page">{page.label}</a>
            {:else }
              <a href={page.href}
                 class="text-gray-300 hover:bg-gray-700 hover:text-white block px-3 py-2 rounded-md text-base font-medium">{page.label}</a>
            {/if}
          {/each}
        </div>

        <!-- User info and picture with drop down menu -->
        <div class="border-t border-gray-700 pt-4 pb-3">
          <div class="flex items-center px-5">
            <div class="flex-shrink-0">
              <img class="h-10 w-10 rounded-full" src={data.user.picture} alt="">
            </div>
            <div class="ml-3">
              <div class="text-base font-medium leading-none text-white">{data.user.name}</div>
              <div class="text-sm font-medium leading-none text-gray-400">{data.user.email}</div>
            </div>
          </div>

          <!-- User Menu Drop Down -->
          <div class="mt-3 space-y-1 px-2">
            {#each data.navs.user as page (page.href)}
              <a href={page.href}
                 class="block rounded-md px-3 py-2 text-base font-medium text-gray-400 hover:bg-gray-700 hover:text-white">{page.label}</a>
            {/each}
          </div>
        </div>

      </div>
    {/if}
  </nav>

  <!-- Title of the content -->
  <header class="bg-white shadow">
    <div class="mx-auto max-w-7xl py-4 px-4 sm:px-6 lg:px-8">
      <h1 class="text-3xl font-bold tracking-tight text-gray-900">{data.pageTitle}</h1>
    </div>
  </header>

  <!-- Actual content -->
  <main>
    <div class="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
      <slot />
    </div>
  </main>

</div>