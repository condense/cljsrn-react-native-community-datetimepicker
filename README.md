
# Troubleshooting

## Assert fails testing module/default

Set `{:language-out :es6}` so default isn't treated as a reserved word.  Without this the generated JS includes a mangled name.

