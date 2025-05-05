# 📱 FireApp – CRUD de Mensagens com Firebase

Um aplicativo Android moderno desenvolvido com **Jetpack Compose**, que permite **criar, visualizar e excluir mensagens** armazenadas no **Firebase Firestore**, tudo isso com uma interface escura estilosa, sem usar XML.

---

## ✨ Tecnologias Utilizadas

🚀 Kotlin  
🎨 Jetpack Compose  
☁️ Firebase Firestore  
🛠️ Android Studio

---

## ⚙️ Funcionalidades

✅ Inserir mensagens no banco de dados  
📋 Listar todas as mensagens salvas  
❌ Remover mensagens da lista  
✏️ Edição de mensagens (em breve)

---

## 🛠️ Como o Projeto Foi Construído

### 🔹 1. Criação no Android Studio
- Projeto iniciado com suporte ao **Jetpack Compose**
- Nome do projeto: `FireApp`

### 🔹 2. Conectando com Firebase
- No Android Studio: `Tools > Firebase > Cloud Firestore > Set up Firestore`
- Criação do projeto no site do Firebase
- Inserção do `google-services.json` na pasta `/app`
- Ajustes no `build.gradle`:

```groovy
// build.gradle (Project)
classpath 'com.google.gms:google-services:4.3.15'

// build.gradle (Module)
plugins {
    id 'com.google.gms.google-services'
}

implementation 'com.google.firebase:firebase-firestore-ktx:24.4.5'
