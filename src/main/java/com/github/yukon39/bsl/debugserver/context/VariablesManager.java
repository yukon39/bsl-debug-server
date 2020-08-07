package com.github.yukon39.bsl.debugserver.context;

import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.CalculationResultBaseData;
import org.eclipse.lsp4j.debug.Variable;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VariablesManager {

    private final Map<Integer, VariablesContext> list = new HashMap<>();

    public VariablesContext createVariablesContext() {

        var variablesContext = new VariablesContext();
        list.put(variablesContext.getReference(), variablesContext);
        return variablesContext;

    }

    public void setCalculationData(VariablesContext variablesContext, CalculationResultBaseData calculation) {

        var calculationResults = calculation.getCalculationResult();

        var variables = variablesContext.getVariables();

        calculationResults
                .getValueOfContextPropInfo()
                .forEach(calculationResult -> {
                    var propertyInfo = calculationResult.getPropInfo();
                    var propertyValue = calculationResult.getValueInfo();

                    var variable = new Variable();
                    variable.setName(propertyInfo.getPropName());
                    variable.setValue(propertyValue.getPresentation());
                    variable.setType(propertyValue.getTypeName());

                    variables.add(variable);
                });

        variablesContext.setReceived(true);
    }

    public @Nullable VariablesContext getVariablesContext(Integer reference) {
        return list.get(reference);
    }

    public @Nullable VariablesContext getVariablesContext(UUID resultId) {

        return list
                .entrySet()
                .stream()
                .filter(e -> e.getValue().getResultId().equals(resultId))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(null);
    }
}
