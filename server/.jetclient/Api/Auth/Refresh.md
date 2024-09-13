```toml
name = 'Refresh'
method = 'POST'
url = '{{url}}/refresh'
sortWeight = 3000000
id = '9e4d29d3-be6a-4933-9400-96bbc0de8b06'

[body]
type = 'JSON'
raw = '''
{
  "refreshToken": "{{refresh_token}}"
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
