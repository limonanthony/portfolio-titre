```toml
name = 'Login'
method = 'POST'
url = '{{url}}/login'
sortWeight = 2000000
id = 'c50332f0-ad20-45f0-bfdd-f4e36d4cda38'

[body]
type = 'JSON'
raw = '''
{
  "email": "joe.mama@mail.com",
  "password": "Pa$$w0rd!",
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
