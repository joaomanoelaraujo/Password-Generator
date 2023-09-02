import random
import string
import tkinter as tk
from tkinter import messagebox

def generator(comprimento):
    caracteres = string.ascii_letters + string.digits + string.ascii_lowercase + string.ascii_uppercase + string.punctuation
    caracteres_permitidos = ''.join(c for c in caracteres if c not in '(){};[]?-+/|"",''~=<>:%`´\\\'')
    password = ''.join(random.choice(caracteres_permitidos) for _ in range(comprimento))
    return password

def gerar_senha():
    comprimento_str = comprimento_entry.get()
    if comprimento_str:
        comprimento_desejado = int(comprimento_str)
    else:
        comprimento_desejado = 15  # Valor padrão
    nova_senha = generator(comprimento_desejado)
    senha_text.config(state=tk.NORMAL)
    senha_text.delete("1.0", tk.END)
    senha_text.insert(tk.END, nova_senha)
    senha_text.config(state=tk.DISABLED)

def copiar_senha():
    senha = senha_text.get("1.0", tk.END)
    janela.clipboard_clear()
    janela.clipboard_append(senha.strip())
    janela.update()

# Criar janela
janela = tk.Tk()
janela.title("Gerador de Senhas")

# Criar widgets
comprimento_label = tk.Label(janela, text="Comprimento da senha:")
comprimento_label.pack()

comprimento_entry = tk.Entry(janela)
comprimento_entry.pack()

gerar_botao = tk.Button(janela, text="Clique para gerar", command=gerar_senha)
gerar_botao.pack()

senha_text = tk.Text(janela, height=10, width=80)
senha_text.pack()
senha_text.config(state=tk.DISABLED)

copiar_botao = tk.Button(janela, text="Copiar senha", command=copiar_senha)
copiar_botao.pack()

# Iniciar loop da interface gráfica
janela.mainloop()