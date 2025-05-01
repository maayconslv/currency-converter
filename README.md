<h1>
  <img src="./Badge-Conversor.png" alt="Logo" width="40" style="vertical-align: middle; margin-right: 10px;">
  Currency Converter (Conversor de Moedas)
</h1>

> Projeto simples de conversão de moedas via API externa.

## 📖 Descrição

Este é um conversor de moedas em linha de comando desenvolvido em Java. O usuário informa:

1. **Moeda base** (ex.: BRL, USD)
2. **Valor** a ser convertido
3. **Moeda de destino** (ex.: USD, EUR)

O programa consulta a ExchangeRate-API para obter a taxa de câmbio mais recente e exibe o resultado ao usuário.

---

## 🚀 Como Executar

Siga os passos abaixo para rodar o projeto localmente:

1. **Pré-requisitos**:
    - Java 14 (ou superior) instalado na máquina
    - Git instalado
2. **Clone o repositório**:
   ```bash
   git clone https://github.com/maayconslv/currency-converter.git
   cd currency-converter
   ```
3. **Configure a API Key**:
    - Crie uma conta no [ExchangeRate-API](https://www.exchangerate-api.com/).
    - No painel, gere sua **Secret Key**.
    - Crie um arquivo `.env` na raiz do projeto e adicione:
      ```env
      EXCHANGE_RATE_API_BASE_URL=https://v6.exchangerate-api.com/v6
      EXCHANGE_RATE_API_KEY=SUA_SECRET_KEY_AQUI
      ```
4. **Compile e rode**:
   ```bash
   javac -cp "libs/*" -d out $(find src -name "*.java")
   ```
5. **Execute o projeto**:
   ```bash
   java -cp "out:libs/*" Main
   ```
   > Se estiver usando Windows, use ; no lugar de `:`:
   ```bash
   java -cp "out;libs/*" Main
   ```

---

## 🛠️ Tecnologias Utilizadas

- **Java** – Linguagem principal do projeto
- **Gson** – Biblioteca para serialização e desserialização de JSON
- **Dotenv** - Biblioteca para configurar variaveis ambiente

---

## 🎓 Desafio

Este projeto foi desenvolvido como parte de um desafio da __Next One Education__, em parceria com a Oracle e a Alura.

---

> **Nota**: Sinta-se à vontade para contribuir, reportar issues ou sugerir melhorias!

