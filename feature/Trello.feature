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
    E alterar o nome do cartao
     |CARD          |
     |Teste DBServer|
    E mover o cartao para "ToDo"
    E mover o cartao para "In Progress"
    E mover o cartao para "Testing"
    E mover o cartao para "Done"
    Entao eu excluo o quadro