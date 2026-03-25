package br.unipar.frameworks.commands;

import br.unipar.framework.miniframework.Request;
import br.unipar.framework.miniframework.Response;

public class BhaskaraCommand extends AbstractMathCommand {
    @Override
    public Response handle(Request request) {
        Response validation = validateExactArgs(request, 3, "bhaskara <a> <b> <c>");
        if (validation != null) {
            return validation;
        }

        try {
            double a = parseNumber(request.getArgs().get(0));
            double b = parseNumber(request.getArgs().get(1));
            double c = parseNumber(request.getArgs().get(2));

            if (a == 0) {
                return Response.badRequest("O coeficiente 'a' deve ser diferente de zero.");
            }

            double delta = (b * b) - (4 * a * c);
            if (delta < 0) {
                return Response.badRequest("A equacao nao possui raizes reais.");
            }

            double raizDelta = Math.sqrt(delta);
            double x1 = (-b + raizDelta) / (2 * a);
            double x2 = (-b - raizDelta) / (2 * a);

            return Response.ok(
                    "Delta: " + formatNumber(delta) +
                            " | x1: " + formatNumber(x1) +
                            " | x2: " + formatNumber(x2)
            );
        } catch (NumberFormatException e) {
            return invalidNumberResponse();
        }
    }
}
