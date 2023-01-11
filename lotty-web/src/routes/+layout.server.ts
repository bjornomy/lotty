import {redirect} from '@sveltejs/kit';

/** @type {import('./$types').LayoutServerLoad} */
export function load({locals, route, params}: any) {

  if (!locals.user && route.id !== '/login') {
    throw redirect(307, '/login');
  }

  if (route.id === '/') {
    throw redirect(307, '/home')
  }

  // TODO: implement join link after login
  // if (url.searchParams.has('redirectTo')) {
  //   throw redirect(303, url.searchParams.get('redirectTo'));
  // }

  const mapRouteToTitle = () => {
    switch (route.id) {
      case '/login':
        return 'Sign In / Register'
      case '/home':
        return 'Home'
      case '/profile':
        return 'Your Profile'
      case '/lotteries/list':
        return 'All Lotteries'
      case '/lotteries/create':
        return 'Create New Lottery'
      case '/lotteries/[lottery]':
        return params.lottery
      case '/schedules/create':
        return 'Create New Schedule'
      default:
        return `No title mapped for route: ${route.id}`
    }
  }

  return {
    user: locals.user,
    route: route.id,
    pageTitle: mapRouteToTitle(),
    navs: {
      page: [
        {href: "/home", label: "Home"},
        {href: "/lotteries/list", label: "Lotteries"}
      ],
      user: [
        {href: "/profile", label: "Your Profile"},
        {href: "http://localhost:10000/logout", label: "Sign Out"}
      ]
    }
  }
}