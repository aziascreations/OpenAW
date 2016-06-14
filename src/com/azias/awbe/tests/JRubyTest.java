package com.azias.awbe.tests;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.azias.awbe.Utils;

public class JRubyTest {
	public static ScriptEngine rubyScriptEngine;
	
	public static void main(String[] args) throws IOException, ScriptException {
		//ScriptEngineManager manager = new ScriptEngineManager();
        //ScriptEngine engine = manager.getEngineByName("jruby");
        //engine.eval(Utils.fileToString("./assets/awbe/scripts/test.rb"));
        //engine.eval("myStr = String.new(\"THIS IS TEST\")\n puts myStr");
        //engine.eval("puts myStr");
        
        rubyScriptEngine = new ScriptEngineManager().getEngineByName("jruby");
        rubyScriptEngine.eval(Utils.fileToString("./assets/awbe/scripts/test.rb"));
        rubyScriptEngine.eval("myStr = String.new(\"THIS IS TEST\")\n puts myStr");
        rubyScriptEngine.eval("puts myStr");
        
		//ScriptingContainer rubyContainer = new ScriptingContainer();
		//runtime.
		//rubyContainer.runScriptlet(Utils.fileToString("./assets/awbe/scripts/test.rb"));
		//rubyContainer.runScriptlet("myStr = String.new(\"THIS IS TEST\")\n puts myStr");
		//rubyContainer.runScriptlet("puts myStr");
	}
}
