// fetch login alternatives from backend

/** @type {import('./$types').PageLoad} */
export async function load() {
  return {
    providers: [
      'GitHub',
      'Google'
    ]
  }
}