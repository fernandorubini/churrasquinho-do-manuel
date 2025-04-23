# 🧭 *Estrutura do Projeto* - *Grupo Churrasquinho do Manuel* 🍢

Este **documento** define a **estrutura de pacotes organizada** por **responsabilidades** e features.

## 🗂️ Estrutura Sugerida

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

di/
  AppModule.kt

features/
  brand/
    presentation/
      BrandScreen.kt
      BrandViewModel.kt
      CustomerViewModel.kt
      UnitScreen.kt
      UnitViewModel.kt

  customer/
    model/
      CartItem.kt
      Customer.kt
    presentation/
      CartViewModel.kt
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

## ✅ Padrões

- **Arquivos de dados e modelo compartilhado:** ficam em `core/` e `model/`.
- **Telas e Lógica por feature:** dentro de `features/<nomeDaFeature>`.
- **Separação por camadas dentro da feature:** `ui`, `presentation`, `model`.
- **Navegação central:** arquivos em `navigation/`.
