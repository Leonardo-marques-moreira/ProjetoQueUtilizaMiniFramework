import br.unipar.framework.miniframework.Dispatcher;
import br.unipar.framework.miniframework.Request;
import br.unipar.framework.miniframework.Response;
import br.unipar.frameworks.commands.HelloCommand;
import br.unipar.frameworks.commands.MultiplicacaoCommand;
import br.unipar.frameworks.commands.SomaCommand;
import br.unipar.frameworks.commands.SubtracaoCommand;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Dispatcher dispatcher = new Dispatcher();

        dispatcher.register("hello", new HelloCommand());
        dispatcher.register("soma", new SomaCommand());
        dispatcher.register("subtracao", new SubtracaoCommand());
        dispatcher.register("multiplicacao", new MultiplicacaoCommand());

        Scanner scanner = new Scanner(System.in);

        System.out.println("App iniciado! Digite um comando:" +
                " (Ex: 'hello', 'soma', 'subtracao' ou 'multiplicacao') ou 'sair' para sair");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if ("sair".equalsIgnoreCase(input)) {
                System.out.println("Encerrando a aplicação. Até logo!");
                break;
            }

            Request request = Request.fromInput(input);
            Response response = dispatcher.dispatch(request);

            System.out.println(response);
        }

        scanner.close();
    }
}
