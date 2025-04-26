# 🧭 Estrutura do Projeto - Grupo Churrasquinho do Manuel 🍢

![Koylin](https://img.shields.io/badge/Java-17-blue?logo=java)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow)
![Atualizado](https://img.shields.io/badge/Atualizado-2025-brightgreen)


Este **documento** define a **estrutura de pacotes organizada** por **responsabilidades** e features.

## 🗂️ Estrutura Atual

```text
core/
  model/
    Brand.kt
    CustomerMenuItem.kt
    MenuItem.kt
    Unit.kt
    User.kt

data/
  AppDatabase.kt
  BrandDao.kt
  BrandRepository.kt
  ReviewDao.kt
  ReviewRepository.kt
  UnitDao.kt
  UnitRepository.kt
  CustomerDao.kt
  CustomerRepository.kt

di/
  AppModule.kt

features/
  brand/
    presentation/
      BrandScreen.kt
      BrandViewModel.kt
      UnitScreen.kt
      UnitViewModel.kt

  customer/
    model/
      CartItem.kt
      Customer.kt
    presentation/
      CustomerViewModel.kt
    ui/
      cart/
        CartScreen.kt
      menu/
        MenuScreen.kt
        MenuItem.kt
      order/
        OrderConfirmationScreen.kt
      store/
        CustomerStoreScreen.kt
        CustomerHomeScreen.kt

  employees/
    Employee.kt
    ui/
      EmployeeHomeScreen.kt

  login/
    LoginScreen.kt
    LoginState.kt
    LoginViewModel.kt

  management/
    ManagementHomeScreen.kt
    presentation/
      ManagementHomeScreenContent.kt
      EmployeeHomeScreen.kt
      ManagementScreen.kt
      Presentation.kt

  register/
    CustomerRegisterScreen.kt
    EmployeeRegisterScreen.kt
    RegisterScreen.kt

model/
  Order.kt
  OrderTracking.kt
  Product.kt
  Review.kt

navigation/
  AppNavigation.kt
  MainNavigation.kt

ui/
  theme/
    Color.kt
    Theme.kt
    Type.kt
```

## ✅ Padrões Adotados

- **Camadas separadas por responsabilidade:** `model`, `ui`, `presentation`.
- **ViewModels injetados com Koin.**
- **Composables organizados por recurso dentro de `ui/` em cada feature.**
- **Modelos compartilhados globais no pacote `core/`.**
- **Navegação declarativa com `NavHost` e rotas nomeadas.**

Este README serve como guia de arquitetura e referência para novos colaboradores e manutenção futura do projeto.
