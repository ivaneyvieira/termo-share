#!/bin/bash

rsync -av  ../termo-consentimento/src ./

rm -rf src/main/kotlin/br/com/astrosoft/termos/view/TermoLayout.kt
rm -rf src/main/kotlin/br/com/astrosoft/termos/view/termos
rm -rf src/main/kotlin/br/com/astrosoft/termos/view/UsuarioView.kt