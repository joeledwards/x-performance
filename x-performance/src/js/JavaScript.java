package js;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import util.PrintUtil;

public class JavaScript
{
	public static Object execute(String script) throws ScriptException
	{
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("nashorn");
		Object result = engine.eval(script);
		
		return result;
	}
	
	public static void main(String[] args) throws Exception
	{
		Object result = JavaScript.execute("1 + 2 + 3 + 'rob'");
		PrintUtil.printTime("Result [type: " + result.getClass().getName() + "] = " + String.valueOf(result));
		
		result = JavaScript.execute("'rob' + 1 + 2 + 3");
		PrintUtil.printTime("Result [type: " + result.getClass().getName() + "] = " + String.valueOf(result));
	}
}
