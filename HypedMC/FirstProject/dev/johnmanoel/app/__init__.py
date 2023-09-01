valorprato = 0.0
listapratos = "Escolha um desses pratos para adquirir:\n1. Massa Carbonara\n2. Sushi\n3. Lasanha"
author = "Author: João Manoel (D4RKK)"
print("Olá usuário, seja bem-vindo ao meu primeiro programa em Python!")
print("Por favor, digite o seu nome.")

nome = input()
print("Agora digite seu número de telefone: ")
telefone = input()
print("Tudo certo! Seja bem-vindo(a) ao Restaurante Virtual!")
print(listapratos)
escolhaprato = int(input())

if escolhaprato == 1:
    print("Prato Massa Carbonara adicionado.")
    valorprato += 10.00
    print("Deseja pedir algo mais?")
    simounao = input().lower()
elif escolhaprato == 2:
    print("Prato Sushi adicionado.")
    valorprato += 30.00
    print("Deseja pedir algo mais?")
    simounao = input().lower()
else:
    print("Prato Lasanha adicionado.")
    valorprato += 15.00
    print("Deseja pedir algo mais?")
    simounao = input().lower()

while simounao in ['sim', 's']:
    print(listapratos)
    escolhaprato = int(input())

    if escolhaprato == 1:
        print("Prato Massa Carbonara adicionado.")
        valorprato += 10.00
    elif escolhaprato == 2:
        print("Prato Sushi adicionado.")
        valorprato += 30.00
    else:
        print("Prato Lasanha adicionado.")
        valorprato += 15.00

    print("Deseja pedir algo mais?")
    simounao = input().lower()

if simounao in ['não', 'n']:
    print("Simulação de nota fiscal:")
    print("Nome: ", nome)
    print("Telefone: ", telefone)
    print("Total: R$", valorprato)
    print("Obrigado por adquirir seu alimento conosco!")
    print(author)