import type {JwtPayload} from 'jwt-decode';
import jwt_decode from 'jwt-decode';

type JwtPayloadWithRoles = JwtPayload
  & { roles: Array<String> }
  & { locale: String, name: String, given_name: String, picture: String, email: String }

const parseJwt = (jwt: string | undefined) => {
  if (!jwt) {
    return undefined
  }

  let decoded: JwtPayloadWithRoles = jwt_decode(jwt);
  //console.log(decoded)
  if (decoded?.sub && decoded?.roles) {

    let displayName = decoded?.given_name || decoded.sub

    return {
      identity: decoded.sub,
      email: decoded.email,
      name: displayName,
      roles: decoded.roles
    }
  } else {
    console.error('Failed to read JWT token')
    return undefined
  }
}

/** @type {import('@sveltejs/kit').Handle} */
export async function handle({event, resolve}: any) {
  event.locals.user = parseJwt(event.cookies.get('JWT'))

  return await resolve(event);
}