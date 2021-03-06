package org.jruby.compiler.ir.instructions.calladapter;

import org.jruby.compiler.ir.operands.Operand;
import org.jruby.runtime.Block;
import org.jruby.runtime.CallSite;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

/**
 *
 */
public class TwoArgBlockOperandCallAdapter extends ClosureCallAdapter {
    private Operand arg1;
    private Operand arg2;
    
    public TwoArgBlockOperandCallAdapter(CallSite callSite, Operand[] args, Operand closure) {
        super(callSite, closure);
        
        assert args.length == 2;
        
        arg1 = args[0];
        arg2 = args[1];
    }

    @Override
    public Object call(ThreadContext context, IRubyObject self, IRubyObject receiver, Object[] temp) {
        IRubyObject value1 = (IRubyObject) arg1.retrieve(context, self, temp);
        IRubyObject value2 = (IRubyObject) arg2.retrieve(context, self, temp);
        Block block = prepareBlock(context, self, temp);
        return callSite.call(context, self, receiver, value1, value2, block);
    }    
}
