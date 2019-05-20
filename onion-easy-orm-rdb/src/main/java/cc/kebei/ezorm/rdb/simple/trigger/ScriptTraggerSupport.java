package cc.kebei.ezorm.rdb.simple.trigger;

import cc.kebei.ezorm.rdb.exception.TriggerException;
import cc.kebei.expands.script.engine.DynamicScriptEngine;
import cc.kebei.expands.script.engine.ExecuteResult;
import cc.kebei.ezorm.core.Trigger;

import java.util.Map;

public class ScriptTraggerSupport implements Trigger {
    private String scriptId;

    private DynamicScriptEngine engine;

    public ScriptTraggerSupport(DynamicScriptEngine engine, String scriptId) {
        this.engine = engine;
        this.scriptId = scriptId;
    }

    @Override
    public Object execute(Map<String, Object> context) throws TriggerException {
        boolean scriptCompiled = engine.compiled(scriptId);
        if (!scriptCompiled) {
            throw new TriggerException("动态脚本 [" + scriptId + "] 未编译!");
        }
        ExecuteResult result = engine.execute(scriptId, context);
        if (result.isSuccess()) {
            return result.get();
        } else {
            Throwable throwable = result.getException();
            while (throwable != null && (throwable = throwable.getCause()) != null) {
                if (throwable instanceof RuntimeException) {
                    throw (RuntimeException) throwable;
                }
            }
            throw new TriggerException(result.getMessage(), result.getException());
        }
    }
}
