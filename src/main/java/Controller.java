import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.FuzzyRuleSet;

import java.util.HashMap;
import java.util.Map;

public class Controller {

    private FuzzyRuleSet ruleSet;
    private String outputVariableName;

    public Controller(String controller_path, String outputVariableName){
        this.ruleSet = FIS.load(controller_path, false).getFuzzyRuleSet();
        this.outputVariableName = outputVariableName;
    }

    public double evaluate(HashMap<String, Double> variables){
        for(Map.Entry<String, Double> keyValPair : variables.entrySet()){
            this.ruleSet.setVariable(keyValPair.getKey(), keyValPair.getValue());
        }
        this.ruleSet.evaluate();
        return this.ruleSet.getVariable(this.outputVariableName).defuzzify();
    }



}
