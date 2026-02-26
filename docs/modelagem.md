# 📐 Modelagem de Domínio – Loja de Cupcakes

## 1. Objetivo da Modelagem

Definir a estrutura do domínio do sistema antes da implementação, garantindo clareza das entidades e seus relacionamentos.

---

## 2. Entidades Principais

### Usuario

Representa clientes e administradores do sistema.

### Cupcake

Representa os produtos disponíveis para venda.

### Pedido

Representa a compra realizada pelo cliente.

### ItemPedido

Representa os itens individuais dentro de um pedido.

---

## 3. Relacionamentos

- Um Usuario pode possuir vários Pedidos.
- Um Pedido possui um ou mais ItemPedido.
- Cada ItemPedido referencia um Cupcake.

---

## 4. Status do Pedido

O sistema utilizará um enum para controlar o estado do pedido:

- ABERTO
- FINALIZADO
- CANCELADO

---

## 5. Decisões de Modelagem

- O carrinho será representado como um Pedido com status ABERTO.
- O valor total do pedido será calculado com base nos subtotais dos itens.
- O estoque será atualizado no momento da finalização do pedido.
