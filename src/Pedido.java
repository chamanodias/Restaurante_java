import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<ItemDoPedido> itens;

    public Pedido() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemDoPedido item) {
        itens.add(item);
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemDoPedido item : itens) {
            total += item.getPreco() * item.getQuantidade();
        }
        return total;
    }
}