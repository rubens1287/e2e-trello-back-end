# language: pt
# charset: UTF-8

Funcionalidade: Trello
   Eu como usuário da ferramenta gostaria de manipular um projeto desde da criação,
   utilização e no final deletar.

   Cenario: CT001 - Trello - Realizar fluxo manipulação de board
    Dado eu crio um quadro
     |QUADRO  |
     |DBServer|
    E crio as colunas
     |COLUNAS                              |
     |Backlog,ToDo,In Progress,Testing,Done|
    E crio um cartao
     |CARD |
     |Teste|
    E altero o nome do cartao
     |CARD          |
     |Teste DBServer|
    E movo o cartao para "ToDo"
    E movo o cartao para "In Progress"
    E movo o cartao para "Testing"
    E movo o cartao para "Done"
    Entao eu excluo o quadro