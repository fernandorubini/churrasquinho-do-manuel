# Churrasquinho do Manuel 🍢  
![status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)

Aplicativo Android desenvolvido para uma rede de restaurantes com múltiplas unidades e marcas. O app possui três módulos principais: Cliente (B2C), Colaboradores Internos e Gestão Administrativa. Cada módulo foi pensado para otimizar a operação, melhorar a experiência do usuário e permitir escalabilidade na gestão.

---

## 🧩 Funcionalidades por Módulo

### 👤 Módulo Cliente (B2C)
- Pedido de delivery ou retirada
- Rastreamento em tempo real
- Integração com gateways de pagamento (cartão, carteira digital)
- Cardápio dinâmico por loja/marca
- Sistema de fidelidade (cashback, cupons, push notifications)

### 👨‍🍳 Módulo Interno (Colaboradores)
- Escala de turnos com troca de horários
- Checklists de rotina (abertura, limpeza, estoque)
- Treinamentos com vídeos e quizzes
- Integração com cozinha/backoffice
- Chat interno (atendimento + cozinha)

### 📊 Módulo de Gestão (Administrativo)
- Dashboard com KPIs em tempo real
- Controle de estoque por unidade
- Auditorias e vistorias com fotos e comentários
- Login multiempresa e controle centralizado

---

## 🛠️ Tecnologias Utilizadas

| Categoria        | Tecnologias                                                                 |
|------------------|------------------------------------------------------------------------------|
| **Linguagens**   | Kotlin, Java                                                                |
| **Arquitetura**  | MVVM, Clean Architecture, SOLID                                             |
| **UI**           | Jetpack Compose                                                             |
| **Persistência** | Room (SQLite)                                                               |
| **API**          | Retrofit (REST)                                                             |
| **Firebase**     | Auth, Firestore, FCM (Push), Analytics                                      |
| **Injeção de Dependência** | Koin                                                             |
| **CI/CD**        | GitHub Actions                                                              |
| **Outros**       | Google Analytics 4, Dynatrace, Git, GitHub                                  |

---

## 🚧 Status do Projeto

Este projeto está **em desenvolvimento ativo**.  
Algumas funcionalidades já foram implementadas e outras estão em progresso.  
O código está sendo modularizado para facilitar manutenções e atualizações futuras.

---

## 🏁 Como executar

```bash
# Clone o repositório
git clone https://github.com/fernandorubini/churrasquinho-do-manuel.git

# Abra o projeto no Android Studio
# Aguarde o Gradle Sync e rode o app em um emulador ou dispositivo físico

