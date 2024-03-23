import java.util.PriorityQueue;
import java.util.Queue;

public class Teste {
    public static void main(String[] args) {
        Operador raiz = new Operador(null, "*");
        Queue<Node> queue = new PriorityQueue<Node>();
        queue.add(raiz);

        System.out.println("Raiz: " + raiz.getClass());
        System.out.println(queue.peek() instanceof Operador);

    }

}
