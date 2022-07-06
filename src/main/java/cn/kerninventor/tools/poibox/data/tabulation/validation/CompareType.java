package cn.kerninventor.tools.poibox.data.tabulation.validation;

import org.apache.poi.ss.usermodel.DataValidationConstraint;

/**
 * @author Kern
 * @version 1.0
 */
public enum CompareType {

    BET(" <> ", DataValidationConstraint.OperatorType.BETWEEN, true),
    NOBET(" >< ",DataValidationConstraint.OperatorType.NOT_BETWEEN, true),
    ET(" == ",DataValidationConstraint.OperatorType.EQUAL, false),
    NOET(" != ",DataValidationConstraint.OperatorType.NOT_BETWEEN, false),
    GT(" > ", DataValidationConstraint.OperatorType.GREATER_THAN, false),
    LT(" < ",DataValidationConstraint.OperatorType.LESS_THAN, false),
    GTE(" >= ",DataValidationConstraint.OperatorType.GREATER_OR_EQUAL, false),
    LTE(" <= ",DataValidationConstraint.OperatorType.LESS_OR_EQUAL, false),
    ;

    private String expression;
    private int code;
    private boolean optionalValueValidity;

    CompareType(String expression, int code, boolean optionalValueValidity) {
        this.expression = expression;
        this.code = code;
        this.optionalValueValidity = optionalValueValidity;
    }

    public boolean isOptionalValueValidity() {
        return optionalValueValidity;
    }

    public int getCode() {
        return code;
    }

    public String getExpression() {
        return expression;
    }

}
