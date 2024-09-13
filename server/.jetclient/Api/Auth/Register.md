```toml
name = 'Register'
method = 'POST'
url = '{{url}}/register'
sortWeight = 1000000
id = '8d8f3a0b-e96c-45eb-b78a-07365475904e'

[body]
type = 'JSON'
raw = '''
{
  "email": "joe.mama@mail.com",
  "password": "Pa$$w0rd!",
  "confirmPassword": "Pa$$w0rd!",
}'''
```

#### Post-response Script

```js
jc.test("Tokens to be there", () => {
    jc.expect(jc.response.code).to.eq(200)
    const body = jc.response.json()
    const accessToken = body.accessToken
    const refreshToken = body.refreshToken
    if (accessToken) jc.globals.set("access_token", accessToken)
    if (refreshToken) jc.globals.set("refresh_token", refreshToken)
})
```
