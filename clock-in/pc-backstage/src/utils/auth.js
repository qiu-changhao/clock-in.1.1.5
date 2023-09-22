import Cookies from 'js-cookie'

const TokenKey = 'token'

const typeKey = 'type'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function getType() {
  return Cookies.get(typeKey)
}

export function setType(type) {
  return Cookies.set(typeKey, type)
}

export function removeTYpe() {
  return Cookies.remove(typeKey)
}
