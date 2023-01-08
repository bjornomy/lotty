import {providers} from '$lib/types/auth-provider'

/** @type {import('./$types').PageServerLoad} */
export async function load({fetch}: any) {

  const defaultProviderNames = Object.keys(providers).map(
    // @ts-ignore
    k => providers[k].name.toLowerCase()
  )

  const authPromise = await fetch('http://localhost:10000/api/config/oauth-providers')
  const availableProviders = await authPromise.json() || defaultProviderNames
  const actualProviders = Object.keys(providers)
    .filter(
      (p: String) => availableProviders.some((ap: String) => ap.toLowerCase() === p.toLowerCase())
    )
    .map(p => {
      // @ts-ignore
      return providers[p]
    })

  return {
    overrideHost: "http://localhost:10000",
    providers: actualProviders
  }
}