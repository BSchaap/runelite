package net.runelite.asm.attributes.code.instructions;

import net.runelite.asm.attributes.code.Instruction;
import net.runelite.asm.attributes.code.InstructionType;
import net.runelite.asm.attributes.code.Instructions;
import net.runelite.asm.attributes.code.instruction.types.LVTInstruction;
import net.runelite.asm.execution.Frame;
import net.runelite.asm.execution.InstructionContext;
import net.runelite.asm.execution.Stack;
import net.runelite.asm.execution.StackContext;
import net.runelite.asm.execution.Type;
import net.runelite.asm.execution.VariableContext;
import net.runelite.asm.execution.Variables;


public class DLoad_2 extends Instruction implements LVTInstruction
{
	public DLoad_2(Instructions instructions, InstructionType type, int pc)
	{
		super(instructions, type, pc);
	}

	public DLoad_2(Instructions instructions)
	{
		super(instructions, InstructionType.DLOAD_2, -1);
	}

	@Override
	public InstructionContext execute(Frame frame)
	{
		InstructionContext ins = new InstructionContext(this, frame);
		Stack stack = frame.getStack();
		Variables variables = frame.getVariables();
		
		VariableContext vctx = variables.get(2);
		assert vctx.getType().equals(new Type(double.class.getName()));
		ins.read(vctx);
		
		StackContext ctx = new StackContext(ins, vctx);
		stack.push(ctx);
		
		ins.push(ctx);
		
		return ins;
	}
	
	@Override
	public int getVariableIndex()
	{
		return 2;
	}
	
	@Override
	public Instruction setVariableIndex(int idx)
	{
		return new DLoad(this.getInstructions(), idx);
	}

	@Override
	public boolean store()
	{
		return false;
	}
	
	@Override
	public Instruction makeGeneric()
	{
		return new DLoad(this.getInstructions(), 2);
	}
}