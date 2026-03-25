package br.unipar.frameworks.commands;

import br.unipar.framework.miniframework.Request;
import br.unipar.framework.miniframework.Response;

public class JurosCompostosCommand extends AbstractMathCommand {
    @Override
    public Response handle(Request request) {
        Response validation = validateExactArgs(
                request,
                3,
                "juroscompostos <capital> <taxa_percentual> <periodos>"
        );
        if (validation != null) {
            return validation;
        }

        try {
            double capital = parseNumber(request.getArgs().get(0));
            double taxaPercentual = parseNumber(request.getArgs().get(1));
            int periodos = Integer.parseInt(request.getArgs().get(2));

            if (capital < 0) {
                return Response.badRequest("O capital inicial nao pode ser negativo.");
            }
            if (taxaPercentual < 0) {
                return Response.badRequest("A taxa percentual nao pode ser negativa.");
            }
            if (periodos < 0) {
                return Response.badRequest("O numero de periodos nao pode ser negativo.");
            }

            double taxa = taxaPercentual / 100;
            double montante = capital * Math.pow(1 + taxa, periodos);
            double juros = montante - capital;

            return Response.ok(
                    "Montante: " + formatNumber(montante) +
                            " | Juros: " + formatNumber(juros)
            );
        } catch (NumberFormatException e) {
            return Response.badRequest("Use numeros validos e informe um numero inteiro para os periodos.");
        }
    }
}
