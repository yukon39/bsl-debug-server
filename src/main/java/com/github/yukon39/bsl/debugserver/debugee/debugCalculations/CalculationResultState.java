package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

public enum CalculationResultState {

    undefined ("undefined"),
    correctly ("correctly"),
    withErrors ("withErrors");

    private final String literal;

    CalculationResultState(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }
}
