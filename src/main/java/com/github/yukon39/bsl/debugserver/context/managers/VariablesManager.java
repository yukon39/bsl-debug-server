package com.github.yukon39.bsl.debugserver.context.managers;

import com.github.yukon39.bsl.debug.data.DebugValueTypeCode;
import com.github.yukon39.bsl.debug.debugger.debugCalculations.*;
import com.github.yukon39.bsl.debugserver.context.data.VariablesContext;
import org.eclipse.lsp4j.debug.Variable;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VariablesManager {

    private final Map<Integer, VariablesContext> list = new HashMap<>();
    private final Map<UUID, VariablesContext> resultList = new HashMap<>();

    public VariablesContext createVariablesContext() {

        var variablesContext = new VariablesContext();
        list.put(variablesContext.getReference(), variablesContext);
        return variablesContext;

    }

    public void setCalculationData(VariablesContext variablesContext, CalculationResultBaseData calculation) {

        var calculationResults = calculation.getCalculationResult();

        var variables = variablesContext.getVariables();
        var stackFrameContext = variablesContext.getStackFrameContext();

        calculationResults
                .getValueOfContextPropInfo()
                .forEach(calculationResult -> {
                    var propertyInfo = calculationResult.getPropInfo();
                    var propertyValue = calculationResult.getValueInfo();

                    var variable = new Variable();
                    variable.setName(propertyInfo.getPropName());
                    variable.setType(propertyValue.getTypeName());

                    if (propertyValue.getTypeCode().equals(DebugValueTypeCode.UNDEFINED.getTypeCode())) {
                        variable.setValue("Undefined"); // TODO: Localize this!
                    } else {
                        variable.setValue(propertyValue.getPresentation());
                    }

                    if (propertyValue.isSupportIEnumValue() || propertyValue.isIIndexedCollectionRO()) {
                        var contextVariable = createVariablesContext();
                        contextVariable.setExpandable(true);
                        contextVariable.setVariableName(propertyInfo.getPropName());
                        contextVariable.setContextVariable(propertyValue.isSupportIContext());
                        contextVariable.setEnumVariable(propertyValue.isSupportIEnumValue());
                        contextVariable.setCollection(propertyValue.isIIndexedCollectionRO());
                        contextVariable.setStackFrameContext(stackFrameContext);

                        variable.setVariablesReference(contextVariable.getReference());
//                        variable.setIndexedVariables();
///                        variable.setNamedVariables();
                    }

                    variables.add(variable);
                });

        variablesContext.setReceived(true);
    }

    public CalculationSourceDataStorage getCalculationSource(VariablesContext variablesContext) {

        var calculationInfo = new SourceCalculationDataInfo();
        calculationInfo.setExpressionID(variablesContext.getResultId()); //"00000000-0000-0000-0000-000000000000"
        calculationInfo.setExpressionResultID(variablesContext.getResultId());

        var interfaces = calculationInfo.getInterfaces();

        if (variablesContext.isContextVariable()) {
            interfaces.add(ViewInterface.CONTEXT);

            var variableName = variablesContext.getVariableName();
            if (!variableName.isEmpty()) {
                var calcItem = new SourceCalculationDataItem();
                calcItem.setItemType(SourceCalculationDataItemType.EXPRESSION);
                calcItem.setExpression(variableName);
                calculationInfo.getCalcItem().add(calcItem);
            }
        }

        if (variablesContext.isEnumVariable()) {
            interfaces.add(ViewInterface.ENUM);
        }

        if (variablesContext.isCollection()) {
            interfaces.add(ViewInterface.COLLECTION);
        }

        var expression = new CalculationSourceDataStorage();
        expression.setStackLevel(variablesContext.getStackFrameContext().getStackLevel());
        expression.setSrcCalcInfo(calculationInfo);
        expression.setPresOptions(DbgPresentationOptionsOfStringValue.defaultOptions());

        return expression;
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
