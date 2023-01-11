export default class AuthProvider {
  name: String
  customButton: boolean

  constructor(name: String, customButton: boolean) {
    this.name = name;
    this.customButton = customButton;
  }
}

export const providers = {
  github: {
    name: 'GitHub',
    customButton: true,
    loginUrl: '/oauth/login/github'
  },
  google: {
    name: 'Google',
    customButton: true,
    loginUrl: '/oauth/login/github'
  },
  microsoft: {
    name: 'Microsoft',
    customButton: false,
    loginUrl: '/oauth/login/microsoft'
  }
}