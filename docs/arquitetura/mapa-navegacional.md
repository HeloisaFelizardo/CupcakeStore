# Mapa Navegacional - Loja de Cupcakes

```mermaid
graph TB
    A[Home] --> B[Catálogo de Cupcakes]
    B --> C[Detalhes do Cupcake]
    C --> D[Adicionar ao Carrinho]
    D --> E[Carrinho]
    E --> F[Finalizar Pedido]
    F --> G[Confirmação de Pedido]

    %% Login
    A --> H[Login]
    H --> I[Cadastro de Cliente]
    H --> B
    D -->|Se não estiver logado| H

    %% Admin
    J[Admin Dashboard] --> K[Cadastrar Cupcake]
    J --> L[Editar Cupcake]
    J --> M[Remover Cupcake]
    J --> N[Visualizar Pedidos]
    J --> O[Atualizar Estoque]

    %% Fluxos extras
    E -->|Carrinho Vazio| E
```
