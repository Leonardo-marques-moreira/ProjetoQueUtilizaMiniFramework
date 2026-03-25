package br.unipar.frameworks.commands;

import br.unipar.framework.miniframework.CommandHandler;
import br.unipar.framework.miniframework.Request;
import br.unipar.framework.miniframework.Response;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

abstract class AbstractMathCommand implements CommandHandler {
    protected Response validateMinimumArgs(Request request, int minimumArgs, String usage) {
        if (request.getArgs().size() < minimumArgs) {
            return Response.badRequest("Uso: " + usage);
        }
        return null;
    }

    protected Response validateExactArgs(Request request, int expectedArgs, String usage) {
        if (request.getArgs().size() != expectedArgs) {
            return Response.badRequest("Uso: " + usage);
        }
        return null;
    }

    protected double parseNumber(String rawValue) {
        double value = Double.parseDouble(rawValue.replace(',', '.'));
        if (!Double.isFinite(value)) {
            throw new NumberFormatException("Numero invalido");
        }
        return value;
    }

    protected List<Double> parseNumbers(List<String> rawValues) {
        List<Double> numbers = new ArrayList<>(rawValues.size());
        for (String rawValue : rawValues) {
            numbers.add(parseNumber(rawValue));
        }
        return numbers;
    }

    protected Response invalidNumberResponse() {
        return Response.badRequest("Os argumentos devem ser numeros validos.");
    }

    protected String formatNumber(double value) {
        if (!Double.isFinite(value)) {
            return String.valueOf(value);
        }
        return BigDecimal.valueOf(value)
                .setScale(10, RoundingMode.HALF_UP)
                .stripTrailingZeros()
                .toPlainString();
    }
}
