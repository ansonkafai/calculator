package com.anson.exercise.calculator;

import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

/**
 * Command console entry point of this calculator app.
 * 
 * @author Anson
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int accumulatorResult = 0;

		// config tinylog
		Configurator.currentConfig().formatPattern("{date}: {level}: {class}.{method}(): {message}").activate();
		Configurator.currentConfig().level(Level.DEBUG).activate();
		
		try {
			//accumulatorResult = StringAccumulator.add("1\n2,3"); <== 6
			//accumulatorResult = StringAccumulator.add("1\n2,3,"); <== 6
			//accumulatorResult = StringAccumulator.add("//***|$$$|##|()|;\n1;2"); <== 3
			//accumulatorResult = StringAccumulator.add("//***|$$$|##|()|;\n-11;-32;-8;67"); <== "negatives not allowed [-11, -32, -8]."
			//accumulatorResult = StringAccumulator.add("//***|$$$|##|()|;\n11;32;8;67"); <== 118
			//accumulatorResult = StringAccumulator.add("//***|$$$|##|()|;\n11***32$$$8##67()90;78"); <== 286
			//accumulatorResult = StringAccumulator.add("//***|$$$|##|()|;\n11***32$$$8##67()1021()90;78"); <== 286
			//accumulatorResult = StringAccumulator.add("//***|$$$|##|()|;\n11***32$$$$$$8####67()1021()90;78"); <== 286
			//accumulatorResult = StringAccumulator.add("1\n2,3,");
			accumulatorResult = StringAccumulator.add("//***|$$$|##|()|;\n11***32$$$$$$8####67()1021()90;78");
			Logger.info(accumulatorResult);
			
		} catch (Exception e) {
			Logger.error(e.getMessage());
			e.printStackTrace();
		}		
	}
}
