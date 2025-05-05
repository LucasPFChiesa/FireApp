# FireApp 📲🔥  
Aplicativo Android desenvolvido com **Jetpack Compose**, focado em operações de **CRUD (Create, Read, Update, Delete)** integradas ao **Firebase Firestore**, sem utilizar arquivos XML.

---

## 📌 Tecnologias Utilizadas

- ✅ **Kotlin** – Linguagem moderna, concisa e segura para Android  
- 🎨 **Jetpack Compose** – Framework declarativo para construção de UI nativa  
- ☁️ **Firebase Firestore** – Banco de dados NoSQL em nuvem da Google  
- 🧰 **Android Studio** – Ambiente de desenvolvimento oficial para apps Android  

---

## 🎯 Objetivo do Projeto

O propósito principal do FireApp é oferecer uma experiência prática no desenvolvimento de um app com:
- Integração com banco de dados na nuvem
- Interface moderna com Compose
- Operações básicas de leitura, escrita, atualização e remoção de dados

---

## ✍️ Funcionalidades Implementadas

✔️ Adicionar novas mensagens ao Firestore  
📜 Listar todas as mensagens salvas no banco  
🗑️ Excluir mensagens individuais  
✏️ Edição de mensagens (em breve)  
💾 Salvamento automático com retorno visual  
🌙 Interface dark moderna com cores vibrantes

---

## 🛠️ Etapas para Criar este Projeto

### 1️⃣ Criar o Projeto no Android Studio

- Crie um novo projeto com suporte a **Jetpack Compose**
- Nome sugerido: `FireApp`
- Linguagem: `Kotlin`
- API mínima: 21 (ou superior)

---

### 2️⃣ Conectar com o Firebase Firestore

- No Android Studio: `Tools > Firebase > Firestore`
- Crie um projeto no console do Firebase
- Ative o **Cloud Firestore** no modo de teste
- Baixe o arquivo `google-services.json` e adicione em: `/app/`

#### build.gradle (Project):

```groovy
classpath 'com.google.gms:google-services:4.3.15' // ou mais atual
