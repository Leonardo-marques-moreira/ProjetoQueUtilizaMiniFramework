import br.unipar.framework.miniframework.Dispatcher;
import br.unipar.framework.miniframework.Request;
import br.unipar.framework.miniframework.Response;
import br.unipar.frameworks.commands.BhaskaraCommand;
import br.unipar.frameworks.commands.HelloCommand;
import br.unipar.frameworks.commands.JurosCompostosCommand;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Dispatcher dispatcher = new Dispatcher();
        registerCommands(dispatcher);

        Scanner scanner = new Scanner(System.in);

        System.out.println("App iniciado! Digite um comando, 'ajuda' para ver as opcoes ou 'sair' para encerrar.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input == null || input.isBlank()) {
                continue;
            }

            if ("sair".equalsIgnoreCase(input.trim())) {
                System.out.println("Encerrando a aplicacao. Ate logo!");
                break;
            }

            Request request = Request.fromInput(input);
            Response response = dispatcher.dispatch(request);
            System.out.println(response);
        }

        scanner.close();
    }

    private static void registerCommands(Dispatcher dispatcher) {
        dispatcher.register("ajuda", request -> Response.ok(buildHelpText()));
        dispatcher.register("help", request -> Response.ok(buildHelpText()));
        dispatcher.register("hello", new HelloCommand());
        dispatcher.register("bhaskara", new BhaskaraCommand());
        dispatcher.register("juroscompostos", new JurosCompostosCommand());
    }

    private static String buildHelpText() {
        return """
                Comandos disponiveis:
                - hello [nome]
                - bhaskara <a> <b> <c>
                - juroscompostos <capital> <taxa_percentual> <periodos>
                """.strip();
    }
}
