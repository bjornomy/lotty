import {redirect} from '@sveltejs/kit';

/** @type {import('./$types').LayoutServerLoad} */
export function load({locals, route}: any) {
  if (!locals.user && route.id !== '/login') {
    throw redirect(307, '/login');
  }

  return {
    user: locals.user
  }
}