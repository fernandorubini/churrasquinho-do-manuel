package com.example.grupochurrasquinhodomanuel.core.constants

object Strings {

    // ---------------------------------------------------------------------
    // Rótulos (textos de UI)
    // ---------------------------------------------------------------------
    object Labels {
        const val APP_NAME = "Grupo Churrasquinho do Manuel"

        // Home
        const val HOME = "Home"

        // Autenticação / cadastro
        const val LOGIN = "Login"
        const val REGISTER = "Cadastrar"
        const val EMAIL = "E-mail"
        const val PASSWORD = "Senha"
        const val CONFIRM_PASSWORD = "Confirmar Senha"
        const val ALREADY_HAVE_ACCOUNT = "Já tem uma conta? Entrar"
        const val SUCCESS_REGISTER = "Cadastro realizado com sucesso"

        // Usuários / papéis
        const val CUSTOMER = "Cliente"
        const val EMPLOYEE = "Funcionário"
        const val MANAGER = "Gestor"

        // Navegação/gerais
        const val WELCOME = "Bem-vindo"
        const val LOGOUT = "Sair"
        const val BACK = "Voltar"
        const val NAME = "Nome"

        // Cliente
        const val FAVORITES = "Favoritos"
        const val ORDER_HISTORY = "Histórico de Pedidos"
        const val ORDER = "Fazer Pedido"

        // Carrinho
        const val ADD_TO_CART = "Adicionar ao Carrinho"
        const val REMOVE_FROM_CART = "Remover do Carrinho"

        // Pedido / rastreamento / detalhes
        const val ORDER_TRACKING_TITLE = "Rastreamento de Pedido"
        const val ORDER_DETAILS = "Detalhes do Pedido"
        const val COMPLETED_ORDERS = "Pedidos Concluídos"
        const val STATUS = "Status"
        const val TOTAL = "Total"
        const val ORDERS = "Pedidos"
        const val ADVANCE_STATUS_BUTTON = "Avançar Status"

        // Produtos
        const val PRODUCT_CHURRASCO_MISTO = "Churrasco Misto"
        const val PRODUCT_CHURRASCO_MISTO_DESC = "Promoção Churrasco Misto"
        const val PRODUCT_GUARANA = "Guaraná"
        const val PRODUCT_GUARANA_DESC = "Guaraná sem açúcar"
        const val PRODUCT_SANDWICHS = "Sanduíches"
        const val PRODUCT_SANDWICHS_DESC = "Promoção de Sanduíches"
        const val QUANTITY = "Quantidade"

        // Lojas/Marcas
        const val STORE_CHURRASQUINHO = "Churrasquinho"
        const val STORE_AI_OLI = "Ai Oli"
        const val STORE_BUFFALOS_RED = "Buffalos Red"

        // Gestão
        const val EMPLOYEE_MANAGEMENT_TITLE = "Gestão de Colaboradores"
        const val ADD_EMPLOYEE = "Adicionar Colaborador"

        // Vendas
        const val TOTAL_DAILY_SALES = "Total de vendas diárias"

        // Reclamações
        const val COMPLAINTS_RECEIVED = "Reclamações recebidas"

        // ---------- Staff: seleção de restaurante ----------
        const val STAFF_PICKER_TITLE = "Escolha o restaurante"
        const val BRAND = "Marca"
        const val UNIT = "Unidade"
        const val ENTER = "Entrar"
        const val CHANGE_RESTAURANT = "Trocar restaurante"
        const val SELECT_BRAND_FIRST = "Selecione uma marca primeiro"
    }

    // ---------------------------------------------------------------------
    // Mensagens / toasts / erros
    // ---------------------------------------------------------------------
    object Messages {
        const val INVALID_EMAIL = "E-mail inválido"
        const val PASSWORD_MISMATCH = "As senhas não conferem."
        const val NO_EMPLOYEES = "Nenhum colaborador cadastrado."
        const val USER_TYPE_NOT_FOUND = "Tipo de usuário não encontrado"
        const val ERROR_REGISTERING = "Erro ao registrar"
        const val ERROR_LOGGING = "Erro ao entrar"
        const val FILL_ALL_FIELDS = "Preencha todos os campos"
        const val NAME_TOO_SHORT = "Nome muito curto (mínimo %d caracteres)"
        const val ROLE_TOO_SHORT = "Função muito curta (mínimo %d caracteres)"
        const val DEPARTMENT_REQUIRED = "Departamento é obrigatório"
        const val INVALID_CREDENTIALS = "Credenciais inválidas"

        // Staff picker
        const val VENUE_REQUIRED = "Selecione uma marca e uma unidade"
    }

    // ---------------------------------------------------------------------
    // Rotas de navegação (ids estáveis, sem espaços)
    // ---------------------------------------------------------------------
    object Routes {
        // Comuns
        const val SPLASH = "splash"
        const val LOGIN = "login"
        const val REGISTER = "register"
        const val WELCOME = "welcome"                 // (antes estava com texto)

        // Seleção do tipo de usuário
        const val USER_TYPE_SELECTION = "userTypeSelection"
        const val EMPLOYEE_REGISTER = "employeeRegister"

        // Homes
        const val CUSTOMER_HOME = "customerHome"
        const val EMPLOYEE_HOME = "employeeHome"
        const val MANAGEMENT_HOME = "managementHome"

        // ---------- Gates para staff (checagem de restaurante escolhido) ----------
        const val EMPLOYEE_ENTRY = "employeeEntry"
        const val MANAGEMENT_ENTRY = "managementEntry"

        // ---------- Picker de restaurante (marca/unidade) ----------
        const val STAFF_PICKER = "staffPicker"
        const val STAFF_PICKER_TARGET_ARG = "target"
        const val STAFF_PICKER_WITH_ARG = "$STAFF_PICKER?$STAFF_PICKER_TARGET_ARG={$STAFF_PICKER_TARGET_ARG}"
        const val TARGET_EMPLOYEE = "employee"
        const val TARGET_MANAGER  = "manager"

        // Pedido / Rastreamento
        const val ORDER_TRACKING_ROUTE = "orderTracking"
        const val ORDER_TRACKING_PATH = "$ORDER_TRACKING_ROUTE/{orderId}"

        // Detalhes de pedido (funcionário) – id estável
        const val EMPLOYEE_ORDER_DETAIL = "employeeOrderDetail"

        // Gestão
        const val MANAGEMENT_METRICS_DETAIL = "managementMetricsDetail"
        const val MANAGEMENT_REGISTER = "managementRegister"

        // (mantido por compatibilidade; id estável)
        const val FAVORITES_ROUTE = "favorites"
        const val EMPLOYEE_TYPE_SELECTION = "employeeTypeSelection"

        // Opcional: tela de pedidos do funcionário
        const val EMPLOYEE_ORDERS = "employeeOrders"
    }

    // ---------------------------------------------------------------------
    // Botões
    // ---------------------------------------------------------------------
    object Buttons {
        const val EDIT = "Editar"
        const val DELETE = "Excluir"
        const val LOGIN = "LOGIN"
        const val REGISTER = "REGISTRO"
        const val SAVE = "Salvar"
        const val CANCEL = "Cancelar"
        const val NEXT_STATUS = "Avançar Status"

        // Mantido (algum código pode estar usando como rota por engano)
        const val MANAGEMENT_METRICS_DETAIL = "managementMetricsDetail"
    }

    // ---------------------------------------------------------------------
    // Rótulos para status de pedido
    // ---------------------------------------------------------------------
    object OrderStatusLabels {
        const val RECEBIDO = "Recebido"
        const val PREPARANDO = "Preparando"
        const val A_CAMINHO = "A caminho"
        const val ENTREGUE = "Entregue"
    }
}
