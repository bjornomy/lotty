import jwt_decode from 'jwt-decode';

type LottyJwtPayload = {
  iss?: string,
  sub?: string,
  aud?: string[] | string,
  exp?: number,
  nbf?: number,
  iat?: number,
  jti?: string,
  roles: Array<String>
  locale: String,
  name: String,
  picture: String,
  email: String
}

const parseJwt = (jwt: string | undefined) => {
  if (!jwt) {
    return undefined
  }

  let decoded: LottyJwtPayload = jwt_decode(jwt);
  // console.log(decoded)
  if (decoded?.sub && decoded?.roles) {

    let displayName = decoded?.name || decoded.sub

    return {
      identity: decoded.sub,
      email: decoded.email,
      name: displayName,
      roles: decoded.roles,
      picture: decoded.picture
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